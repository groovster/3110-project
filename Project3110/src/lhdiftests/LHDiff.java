package lhdiftests;

import java.util.*;
import java.io.*;

public class LHDiff {
    
    public static LHDiffResult compute(String[] left, String[] right) {
        try {
            // Create temporary directory
            File tempDir = new File("temp_test_" + System.currentTimeMillis());
            tempDir.mkdir();
            
            File leftFile = new File(tempDir, "Old.java");
            File rightFile = new File(tempDir, "New.java");
            
            // Write arrays to files
            writeArrayToFile(leftFile, left);
            writeArrayToFile(rightFile, right);
            
            // Read files as lists
            List<String> leftRaw = readFile(leftFile);
            List<String> rightRaw = readFile(rightFile);
            
            // Clean both files
            LineMapper.CleanFile leftClean = LineMapper.cleanFile(leftRaw);
            LineMapper.CleanFile rightClean = LineMapper.cleanFile(rightRaw);
            
            // Get mapping from LineMapper
            List<LineMapper.Mapping> mappings = LineMapper.mapLinesHybrid(leftClean, rightClean);
            
            // Convert to LHDiffResult
            LHDiffResult result = convertToResult(mappings, left, right);
            
            // Clean up
            leftFile.delete();
            rightFile.delete();
            tempDir.delete();
            
            return result;
            
        } catch (Exception e) {
            System.err.println("Error in LHDiff.compute: " + e.getMessage());
            e.printStackTrace();
            return new LHDiffResult();
        }
    }
    
    private static void writeArrayToFile(File file, String[] lines) throws IOException {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (String line : lines) {
                pw.println(line);
            }
        }
    }
    
    private static List<String> readFile(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    
    private static LHDiffResult convertToResult(List<LineMapper.Mapping> mappings, 
                                                 String[] left, String[] right) {
        LHDiffResult result = new LHDiffResult();
        
        // Track which right lines are used
        Set<Integer> usedRightLines = new HashSet<>();
        
        // Process all mappings
        for (LineMapper.Mapping mapping : mappings) {
            int leftLine = mapping.origLine;
            List<Integer> rightLines = mapping.newLines;
            
            if (leftLine > 0) {
                // This is a mapping from left to right
                result.addMapping(leftLine, rightLines);
                
                if (rightLines.isEmpty()) {
                    result.markDeleted(leftLine);
                } else {
                    // Mark all right lines as used
                    for (int rightLine : rightLines) {
                        usedRightLines.add(rightLine);
                    }
                    
                    // Check if modified (only if 1-to-1 mapping)
                    if (rightLines.size() == 1 && 
                        leftLine <= left.length && 
                        rightLines.get(0) <= right.length) {
                        
                        String leftContent = left[leftLine - 1];
                        String rightContent = right[rightLines.get(0) - 1];
                        
                        if (!leftContent.trim().equals(rightContent.trim())) {
                            result.markModified(leftLine);
                        }
                    }
                }
            } else {
                // This is an inserted line (origLine = -1)
                for (int rightLine : rightLines) {
                    result.markAdded(rightLine);
                    usedRightLines.add(rightLine);
                }
            }
        }
        
        // Mark any unmapped right lines as added
        for (int i = 1; i <= right.length; i++) {
            if (!usedRightLines.contains(i)) {
                result.markAdded(i);
            }
        }
        
        return result;
    }
}