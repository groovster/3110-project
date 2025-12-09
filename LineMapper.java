import java.io.*;
import java.util.*;

public class LineMapper {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java LineMapper4 <folder>");
            return;
        }

        String folderName = args[0];
        File folder = new File(folderName);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory: " + folderName);
            return;
        }

        // Read all Java files in the folder
        File[] javaFiles = folder.listFiles((d, name) -> name.endsWith(".java"));

        if (javaFiles == null || javaFiles.length < 2) {
            System.out.println("Folder must contain at least 2 Java files.");
            return;
        }

        // Sort alphabetically for deterministic pairing
        Arrays.sort(javaFiles);

        File oldFile = javaFiles[0];
        File newFile = javaFiles[1];

        System.out.println("OLD FILE: " + oldFile.getName());
        System.out.println("NEW FILE: " + newFile.getName());

        processFilePair(folderName, oldFile, newFile);
    }

    // ---------------- PER-FILE PROCESSING ----------------

    public static void processFilePair(String folderName, File oldFile, File newFile) throws Exception {

        List<String> oldRaw = readResourceFile(oldFile.getAbsolutePath());
        List<String> newRaw = readResourceFile(newFile.getAbsolutePath());

        // CLEAN BOTH FILES
        CleanFile oldClean = cleanFile(oldRaw);
        CleanFile newClean = cleanFile(newRaw);

        // VERSION 1
        List<Mapping> version1 = identityMappingCleaned(oldClean);

        // VERSION 2
        List<Mapping> version2 = mapLinesHybrid(oldClean, newClean);

        // Build XML output
        List<String> output = new ArrayList<>();
        output.add("<TEST NAME=\"" + folderName + "\" FILE=\"" 
                   + oldFile.getName() + " vs " + newFile.getName() + "\">");
        output.addAll(toXMLFormat(version1, 1));
        output.add("");
        output.addAll(toXMLFormat(version2, 2));
        output.add("</TEST>");

        // Output file
        File out = new File(folderName, "mapping_output_" + folderName + ".txt");
        writeFile(out, output);

        System.out.println("Output written to: " + out.getAbsolutePath());
    }

    // ---------------- FILE READING / WRITING ----------------

    public static List<String> readResourceFile(String filename) throws Exception {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        String line;
        while ((line = br.readLine()) != null) lines.add(line);
        br.close();
        return lines;
    }

    public static void writeFile(File file, List<String> lines) throws Exception {
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        for (String line : lines) pw.println(line);
        pw.close();
    }

    // ---------------- CLEAN FILE STRUCT ----------------

    static class CleanFile {
        List<String> lines;
        List<Integer> map;
    }

    public static CleanFile cleanFile(List<String> raw) {
        CleanFile cf = new CleanFile();
        cf.lines = new ArrayList<>();
        cf.map = new ArrayList<>();

        for (int i = 0; i < raw.size(); i++) {
            String line = raw.get(i);
            String t = line.trim();

            if (t.isEmpty()) continue;
            if (t.equals("{") || t.equals("}")) continue;

            cf.lines.add(line);
            cf.map.add(i + 1);
        }
        return cf;
    }

    // ---------------- MAPPING DATA STRUCTURE ----------------

    static class Mapping {
        int origLine;
        List<Integer> newLines;
        Mapping(int origLine, List<Integer> newLines) {
            this.origLine = origLine;
            this.newLines = newLines;
        }
    }

    // ---------------- VERSION 1 ----------------

    public static List<Mapping> identityMappingCleaned(CleanFile cf) {
        List<Mapping> out = new ArrayList<>();
        for (int i = 0; i < cf.lines.size(); i++) {
            int orig = cf.map.get(i);
            out.add(new Mapping(orig, Arrays.asList(orig)));
        }
        return out;
    }

    // ---------------- METHOD-BLOCK CONTEXT ----------------

    public static String[] buildMethodContext(List<String> file) {
        int n = file.size();
        String[] ctx = new String[n];

        int braceDepth = 0;
        int methodStart = -1;

        for (int i = 0; i < n; i++) {
            String line = file.get(i).trim();

            boolean isMethodSig =
                    line.contains("(") &&
                    line.contains(")") &&
                    line.endsWith("{");

            if (isMethodSig) {
                methodStart = i;
                braceDepth = 1;
                continue;
            }

            if (methodStart != -1) {
                if (line.contains("{")) braceDepth++;
                if (line.contains("}")) braceDepth--;

                if (braceDepth == 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = methodStart; j <= i; j++) {
                        sb.append(file.get(j)).append(" ");
                    }
                    String methodCtx = sb.toString();

                    for (int j = methodStart; j <= i; j++) {
                        ctx[j] = methodCtx;
                    }

                    methodStart = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (ctx[i] == null) ctx[i] = file.get(i);
        }

        return ctx;
    }

    // ---------------- VERSION 2 ----------------

    public static List<Mapping> mapLinesHybrid(CleanFile oldClean, CleanFile newClean) {

        List<String> oldFile = oldClean.lines;
        List<String> newFile = newClean.lines;
        List<Integer> oldMap = oldClean.map;
        List<Integer> newMap = newClean.map;

        int nOld = oldFile.size();
        int nNew = newFile.size();

        int k = 15;
        double threshold = 0.5;

        String[] oldCtx = buildMethodContext(oldFile);
        String[] newCtx = buildMethodContext(newFile);

        long[] oldCH = new long[nOld];
        long[] oldXH = new long[nOld];
        long[] newCH = new long[nNew];
        long[] newXH = new long[nNew];

        for (int i = 0; i < nOld; i++) {
            oldCH[i] = simHash64(oldFile.get(i));
            oldXH[i] = simHash64(oldCtx[i]);
        }
        for (int j = 0; j < nNew; j++) {
            newCH[j] = simHash64(newFile.get(j));
            newXH[j] = simHash64(newCtx[j]);
        }

        List<Mapping> result = new ArrayList<>();
        boolean[] usedNew = new boolean[nNew];

        for (int i = 0; i < nOld; i++) {

            String oldLine = oldFile.get(i);
            String oldContext = oldCtx[i];

            long oc = oldCH[i];
            long ox = oldXH[i];

            List<Candidate> cand = new ArrayList<>();
            for (int j = 0; j < nNew; j++) {
                if (usedNew[j]) continue;

                int hdC = hammingDistance64(oc, newCH[j]);
                int hdX = hammingDistance64(ox, newXH[j]);
                double dist = 0.6 * hdC + 0.4 * hdX;
                cand.add(new Candidate(j, dist));
            }

            cand.sort(Comparator.comparingDouble(c -> c.dist));
            if (cand.size() > k) cand = cand.subList(0, k);

            double bestScore = -1;
            int bestMatch = -1;

            for (Candidate c : cand) {
                int j = c.index;

                double contentSim = contentSimilarity(oldLine, newFile.get(j));
                double contextSim = cosineSimilarity(oldContext, newCtx[j]);
                double tokenSim = tokenSimilarity(oldLine, newFile.get(j));

                if (tokenSim < 0.40 && contentSim < 0.40) continue;

                double score = 0.4 * contentSim + 0.3 * contextSim + 0.3 * tokenSim;

                if (score > bestScore) {
                    bestScore = score;
                    bestMatch = j;
                }
            }

            int origLine = oldMap.get(i);

            if (bestScore < threshold || bestMatch == -1) {
                result.add(new Mapping(origLine, new ArrayList<>()));
                continue;
            }

            int newOriginalLine = newMap.get(bestMatch);
            usedNew[bestMatch] = true;

            result.add(new Mapping(origLine, Arrays.asList(newOriginalLine)));
        }

        // Inserted lines
        for (int j = 0; j < nNew; j++) {
            if (!usedNew[j]) {
                int newOriginalLine = newMap.get(j);
                result.add(new Mapping(-1, Arrays.asList(newOriginalLine)));
            }
        }

        // Sort by NEW line number
        result.sort((a, b) -> {
            int na = a.newLines.isEmpty() ? Integer.MAX_VALUE : a.newLines.get(0);
            int nb = b.newLines.isEmpty() ? Integer.MAX_VALUE : b.newLines.get(0);
            return Integer.compare(na, nb);
        });

        return result;
    }

    static class Candidate {
        int index;
        double dist;
        Candidate(int index, double dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    // ---------------- TOKEN SIMILARITY ----------------

    public static double tokenSimilarity(String a, String b) {
        Set<String> A = new HashSet<>();
        Set<String> B = new HashSet<>();

        for (String t : a.split("\\W+")) {
            if (!t.isEmpty()) A.add(t);
        }
        for (String t : b.split("\\W+")) {
            if (!t.isEmpty()) B.add(t);
        }

        if (A.isEmpty() && B.isEmpty()) return 1.0;
        if (A.isEmpty() || B.isEmpty()) return 0.0;

        Set<String> intersection = new HashSet<>(A);
        intersection.retainAll(B);

        Set<String> union = new HashSet<>(A);
        union.addAll(B);

        return (double) intersection.size() / union.size();
    }

    // ---------------- CONTENT SIMILARITY ----------------

    public static double contentSimilarity(String a, String b) {
        int dist = levenshtein(a, b);
        int maxLen = Math.max(a.length(), b.length());
        if (maxLen == 0) return 1.0;
        return 1.0 - ((double) dist / maxLen);
    }

    public static int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }

    // ---------------- COSINE SIMILARITY ----------------

    public static double cosineSimilarity(String a, String b) {
        Map<String, Integer> freqA = wordFreq(a);
        Map<String, Integer> freqB = wordFreq(b);

        Set<String> allWords = new HashSet<>();
        allWords.addAll(freqA.keySet());
        allWords.addAll(freqB.keySet());

        int dot = 0;
        double magA = 0;
        double magB = 0;

        for (String w : allWords) {
            int x = freqA.getOrDefault(w, 0);
            int y = freqB.getOrDefault(w, 0);
            dot += x * y;
            magA += x * x;
            magB += y * y;
        }

        if (magA == 0 || magB == 0) return 0.0;
        return dot / (Math.sqrt(magA) * Math.sqrt(magB));
    }

    public static Map<String, Integer> wordFreq(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : s.split("\\W+")) {
            if (!w.isEmpty()) map.put(w, map.getOrDefault(w, 0) + 1);
        }
        return map;
    }

    // ---------------- SIMHASH ----------------

    public static long simHash64(String s) {
        if (s == null || s.isEmpty()) return 0L;

        int[] bitVector = new int[64];
        String[] tokens = s.split("\\W+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            long h = murmurLikeHash64(token);

            for (int i = 0; i < 64; i++) {
                long bit = (h >>> i) & 1L;
                if (bit == 1L) bitVector[i] += 1;
                else bitVector[i] -= 1;
            }
        }

        long simhash = 0L;
        for (int i = 0; i < 64; i++) {
            if (bitVector[i] > 0) {
                simhash |= (1L << i);
            }
        }
        return simhash;
    }

    public static long murmurLikeHash64(String key) {
        long h = 1125899906842597L;
        for (char c : key.toCharArray()) h = 31 * h + c;
        return h;
    }

    public static int hammingDistance64(long a, long b) {
        long x = a ^ b;
        int dist = 0;
        while (x != 0) {
            dist += 1;
            x &= (x - 1);
        }
        return dist;
    }

    // ---------------- XML OUTPUT ----------------

    public static List<String> toXMLFormat(List<Mapping> mappings, int versionNumber) {
        List<String> out = new ArrayList<>();

        out.add("<VERSION NUMBER=\"" + versionNumber + "\" CHECKED=\"TRUE\">");

        for (Mapping m : mappings) {
            int orig = m.origLine;

            if (m.newLines == null || m.newLines.isEmpty()) {
                out.add("   <LOCATION ORIG=\"" + orig + "\" NEW=\"-1\" />");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < m.newLines.size(); i++) {
                    if (i > 0) sb.append(" ");
                    sb.append(m.newLines.get(i));
                }
                out.add("   <LOCATION ORIG=\"" + orig + "\" NEW=\"" + sb.toString() + "\" />");
            }
        }

        out.add("</VERSION>");
        return out;
    }
}
