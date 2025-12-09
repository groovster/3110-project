package lhdiftests;

import java.util.*;

public class LHDiffResult {
    private Map<Integer, List<Integer>> mapping = new HashMap<>();
    private Set<Integer> deletedLines = new HashSet<>();
    private Set<Integer> addedLines = new HashSet<>();
    private Set<Integer> modifiedLines = new HashSet<>();
    
    public void addMapping(int leftLine, List<Integer> rightLines) {
        mapping.put(leftLine, rightLines);
    }
    
    public int getMapping(int leftLine) {
        List<Integer> lines = mapping.get(leftLine);
        if (lines == null || lines.isEmpty()) {
            return -1;
        }
        return lines.get(0);
    }
    
    public List<Integer> getAllMappings(int leftLine) {
        return mapping.getOrDefault(leftLine, new ArrayList<>());
    }
    
    public void markDeleted(int leftLine) {
        deletedLines.add(leftLine);
    }
    
    public void markAdded(int rightLine) {
        addedLines.add(rightLine);
    }
    
    public void markModified(int leftLine) {
        modifiedLines.add(leftLine);
    }
    
    public boolean isDeletedLine(int leftLine) {
        return deletedLines.contains(leftLine) || 
               (mapping.containsKey(leftLine) && mapping.get(leftLine).isEmpty());
    }
    
    public boolean isAddedLine(int rightLine) {
        return addedLines.contains(rightLine);
    }
    
    public boolean isModifiedLine(int leftLine) {
        return modifiedLines.contains(leftLine);
    }
    
    public boolean isempty() {
        return mapping.isEmpty();
    }
    
    public Map<Integer, List<Integer>> getFullMapping() {
        return mapping;
    }
}