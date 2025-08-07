package com.grammar.cache;

import com.grammar.compiler.SenseGrammarListener;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * Grammar parsing cache to avoid re-parsing unchanged grammar files
 */
public class GrammarCache {
    
    private final ConcurrentMap<String, CacheEntry> parseTreeCache = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, CacheEntry> listenerCache = new ConcurrentHashMap<>();
    
    /**
     * Get cached parse tree for grammar content
     */
    public ParseTree getCachedParseTree(String grammarContent) {
        String hash = computeHash(grammarContent);
        CacheEntry entry = parseTreeCache.get(hash);
        
        if (entry != null && !entry.isExpired()) {
            return entry.parseTree;
        }
        
        return null;
    }
    
    /**
     * Cache parse tree for grammar content
     */
    public void cacheParseTree(String grammarContent, ParseTree parseTree) {
        String hash = computeHash(grammarContent);
        parseTreeCache.put(hash, new CacheEntry(parseTree, null));
    }
    
    /**
     * Get cached grammar listener for grammar content
     */
    public SenseGrammarListener getCachedListener(String grammarContent) {
        String hash = computeHash(grammarContent);
        CacheEntry entry = listenerCache.get(hash);
        
        if (entry != null && !entry.isExpired()) {
            return entry.listener;
        }
        
        return null;
    }
    
    /**
     * Cache grammar listener for grammar content
     */
    public void cacheListener(String grammarContent, SenseGrammarListener listener) {
        String hash = computeHash(grammarContent);
        listenerCache.put(hash, new CacheEntry(null, listener));
    }
    
    /**
     * Clear all caches
     */
    public void clearCache() {
        parseTreeCache.clear();
        listenerCache.clear();
    }
    
    /**
     * Get cache statistics
     */
    public CacheStats getStats() {
        return new CacheStats(
            parseTreeCache.size(),
            listenerCache.size(),
            parseTreeCache.values().stream().mapToInt(e -> e.isExpired() ? 0 : 1).sum(),
            listenerCache.values().stream().mapToInt(e -> e.isExpired() ? 0 : 1).sum()
        );
    }
    
    /**
     * Compute SHA-256 hash of grammar content
     */
    private String computeHash(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Fallback to simple hash
            return String.valueOf(content.hashCode());
        }
    }
    
    /**
     * Cache entry with expiration support
     */
    private static class CacheEntry {
        final ParseTree parseTree;
        final SenseGrammarListener listener;
        final long timestamp;
        final long ttl = 30 * 60 * 1000; // 30 minutes TTL
        
        CacheEntry(ParseTree parseTree, SenseGrammarListener listener) {
            this.parseTree = parseTree;
            this.listener = listener;
            this.timestamp = System.currentTimeMillis();
        }
        
        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > ttl;
        }
    }
    
    /**
     * Cache statistics
     */
    public static class CacheStats {
        public final int parseTreeCacheSize;
        public final int listenerCacheSize;
        public final int validParseTreeEntries;
        public final int validListenerEntries;
        
        CacheStats(int parseTreeCacheSize, int listenerCacheSize, 
                  int validParseTreeEntries, int validListenerEntries) {
            this.parseTreeCacheSize = parseTreeCacheSize;
            this.listenerCacheSize = listenerCacheSize;
            this.validParseTreeEntries = validParseTreeEntries;
            this.validListenerEntries = validListenerEntries;
        }
        
        @Override
        public String toString() {
            return String.format("CacheStats{parseTree: %d/%d, listener: %d/%d}", 
                validParseTreeEntries, parseTreeCacheSize,
                validListenerEntries, listenerCacheSize);
        }
    }
}