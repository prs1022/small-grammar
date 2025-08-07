package com.grammar.compiler;

import com.grammar.fsm.FiniteStateMachine;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

/**
 * Incremental compiler that only recompiles changed grammar files
 */
public class IncrementalCompiler {
    
    private final ImprovedCLDCompiler compiler;
    private final ConcurrentMap<Path, FileInfo> fileInfoMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, List<FiniteStateMachine>> compiledFSMs = new ConcurrentHashMap<>();
    
    public IncrementalCompiler(ImprovedCLDCompiler compiler) {
        this.compiler = compiler;
    }
    
    /**
     * Compile all grammar files in directory with incremental support
     */
    public List<FiniteStateMachine> compileDirectory(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);
        
        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            throw new IOException("Directory does not exist: " + directoryPath);
        }
        
        List<FiniteStateMachine> allFSMs = new ArrayList<>();
        Set<Path> currentFiles = new HashSet<>();
        
        // Scan for .cld files
        try (Stream<Path> paths = Files.walk(dir)) {
            List<Path> cldFiles = paths
                .filter(path -> path.toString().endsWith(".cld"))
                .filter(Files::isRegularFile)
                .collect(java.util.stream.Collectors.toList());
            
            for (Path file : cldFiles) {
                currentFiles.add(file);
                
                if (needsRecompilation(file)) {
                    System.out.println("Recompiling changed file: " + file.getFileName());
                    List<FiniteStateMachine> fsms = compileFile(file);
                    compiledFSMs.put(file.toString(), fsms);
                    updateFileInfo(file);
                } else {
                    System.out.println("Using cached compilation for: " + file.getFileName());
                    List<FiniteStateMachine> cachedFSMs = compiledFSMs.get(file.toString());
                    if (cachedFSMs != null) {
                        allFSMs.addAll(cachedFSMs);
                    }
                }
            }
        }
        
        // Remove deleted files from cache
        Set<String> deletedFiles = new HashSet<>(compiledFSMs.keySet());
        currentFiles.forEach(path -> deletedFiles.remove(path.toString()));
        
        for (String deletedFile : deletedFiles) {
            System.out.println("Removing deleted file from cache: " + Paths.get(deletedFile).getFileName());
            compiledFSMs.remove(deletedFile);
            fileInfoMap.remove(Paths.get(deletedFile));
        }
        
        // Collect all FSMs
        allFSMs.clear();
        compiledFSMs.values().forEach(allFSMs::addAll);
        
        return allFSMs;
    }
    
    /**
     * Compile a single file
     */
    public List<FiniteStateMachine> compileFile(Path filePath) throws IOException {
        String content = Files.readString(filePath);
        List<FiniteStateMachine> fsms = compiler.compile(content);
        
        // Cache the result
        compiledFSMs.put(filePath.toString(), fsms);
        updateFileInfo(filePath);
        
        return fsms;
    }
    
    /**
     * Check if file needs recompilation
     */
    private boolean needsRecompilation(Path file) {
        try {
            FileInfo cachedInfo = fileInfoMap.get(file);
            if (cachedInfo == null) {
                return true; // New file
            }
            
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            
            // Check if file was modified
            if (!attrs.lastModifiedTime().equals(cachedInfo.lastModified)) {
                return true;
            }
            
            // Check if file size changed
            if (attrs.size() != cachedInfo.size) {
                return true;
            }
            
            return false;
            
        } catch (IOException e) {
            // If we can't read file attributes, assume it needs recompilation
            return true;
        }
    }
    
    /**
     * Update file information cache
     */
    private void updateFileInfo(Path file) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            FileInfo info = new FileInfo(
                attrs.lastModifiedTime(),
                attrs.size(),
                System.currentTimeMillis()
            );
            fileInfoMap.put(file, info);
        } catch (IOException e) {
            System.err.println("Failed to update file info for: " + file);
        }
    }
    
    /**
     * Get compilation statistics
     */
    public CompilationStats getStats() {
        int totalFiles = fileInfoMap.size();
        int cachedFiles = compiledFSMs.size();
        int totalFSMs = compiledFSMs.values().stream()
            .mapToInt(List::size)
            .sum();
        
        return new CompilationStats(totalFiles, cachedFiles, totalFSMs);
    }
    
    /**
     * Clear all compilation caches
     */
    public void clearCache() {
        fileInfoMap.clear();
        compiledFSMs.clear();
        compiler.clearCache();
    }
    
    /**
     * File information for change detection
     */
    private static class FileInfo {
        final FileTime lastModified;
        final long size;
        final long compiledAt;
        
        FileInfo(FileTime lastModified, long size, long compiledAt) {
            this.lastModified = lastModified;
            this.size = size;
            this.compiledAt = compiledAt;
        }
    }
    
    /**
     * Compilation statistics
     */
    public static class CompilationStats {
        public final int totalFiles;
        public final int cachedFiles;
        public final int totalFSMs;
        
        CompilationStats(int totalFiles, int cachedFiles, int totalFSMs) {
            this.totalFiles = totalFiles;
            this.cachedFiles = cachedFiles;
            this.totalFSMs = totalFSMs;
        }
        
        @Override
        public String toString() {
            return String.format("CompilationStats{files: %d, cached: %d, FSMs: %d}", 
                totalFiles, cachedFiles, totalFSMs);
        }
    }
}