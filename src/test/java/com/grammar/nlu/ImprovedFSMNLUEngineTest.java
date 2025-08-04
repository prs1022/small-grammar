package com.grammar.nlu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImprovedFSMNLUEngineTest {

    private ImprovedFSMNLUEngine engine;

    @BeforeEach
    void setUp() {
        engine = new ImprovedFSMNLUEngine();
    }

    @Test
    void testLoadWeatherCLDFile() {
        // Test loading the simple weather.cld file
        assertDoesNotThrow(() -> {
            engine.loadGrammarFile("src/main/resources/simple_weather.cld");
        });

        assertTrue(engine.getFSMs().size() > 0, "Should load at least one FSM from simple_weather.cld");
    }

    @Test
    void testLoadNavigationCLDFile() {
        // Test loading the simple navigation.cld file
        assertDoesNotThrow(() -> {
            engine.loadGrammarFile("src/main/resources/simple_navigation.cld");
        });

        assertTrue(engine.getFSMs().size() > 0, "Should load at least one FSM from simple_navigation.cld");
    }

    @Test
    void testLoadMultipleCLDFiles() {
        // Load both files
        assertDoesNotThrow(() -> {
            engine.loadGrammarFile("src/main/resources/simple_weather.cld");
            engine.loadGrammarFile("src/main/resources/simple_navigation.cld");
        });

        assertTrue(engine.getFSMs().size() >= 2, "Should load FSMs from both files");
    }

    @Test
    void testLoadFromResourcesDirectory() {
        // Test loading all .cld files from resources directory
        assertDoesNotThrow(() -> {
            engine.loadGrammarDirectory("src/main/resources");
        });

        assertTrue(engine.getFSMs().size() > 0, "Should load FSMs from resources directory");
    }

    @Test
    void testParseWithLoadedGrammars() {
        // Load grammars from files
        try {
            engine.loadGrammarFile("src/main/resources/simple_weather.cld");
            engine.loadGrammarFile("src/main/resources/simple_navigation.cld");
        } catch (Exception e) {
            fail("Failed to load grammar files: " + e.getMessage());
        }

        // Test parsing with valid inputs
        NLUResult weatherResult = engine.parseText("today weather");
        assertNotNull(weatherResult);
        assertNotNull(weatherResult.getIntent());
        assertNotNull(weatherResult.getSlots());

        NLUResult navResult = engine.parseText("go park");
        assertNotNull(navResult);
        assertNotNull(navResult.getIntent());
        assertNotNull(navResult.getSlots());

        // Test unknown input
        NLUResult unknownResult = engine.parseText("completely unknown input that should not match");
        assertEquals("unknown", unknownResult.getIntent());
        assertEquals(0, unknownResult.getSlots().size());
    }

    @Test
    void testJSONOutputFormat() {
        // Load grammars
        try {
            engine.loadGrammarFile("src/main/resources/simple_weather.cld");
        } catch (Exception e) {
            fail("Failed to load grammar file: " + e.getMessage());
        }

        // Test JSON output format with valid input
        NLUResult result = engine.parseText("today weather");
        String json = result.toJsonString();

        assertNotNull(json);
        assertTrue(json.contains("\"intent\":"));
        assertTrue(json.contains("\"slot\":"));
    }

    @Test
    void testFileNotFound() {
        // Test loading non-existent file
        assertDoesNotThrow(() -> {
            engine.loadGrammarFile("non-existent-file.cld");
        });

        // Should not crash, but also should not load any FSMs
        // (The current implementation prints error but doesn't throw)
    }

    @Test
    void testDirectoryNotFound() {
        // Test loading from non-existent directory
        assertThrows(IOException.class, () -> {
            engine.loadGrammarDirectory("non-existent-directory");
        });
    }

    @Test
    void testCLDFileContent() {
        // Verify that our test .cld files exist and have content
        assertTrue(Files.exists(Paths.get("src/main/resources/simple_weather.cld")),
                "simple_weather.cld file should exist");
        assertTrue(Files.exists(Paths.get("src/main/resources/simple_navigation.cld")),
                "simple_navigation.cld file should exist");

        try {
            String weatherContent = Files.readString(Paths.get("src/main/resources/simple_weather.cld"));
            String navContent = Files.readString(Paths.get("src/main/resources/simple_navigation.cld"));

            assertFalse(weatherContent.trim().isEmpty(), "simple_weather.cld should not be empty");
            assertFalse(navContent.trim().isEmpty(), "simple_navigation.cld should not be empty");

            // Check for basic grammar structure
            assertTrue(weatherContent.contains("namespace"), "simple_weather.cld should contain namespace");
            assertTrue(navContent.contains("namespace"), "simple_navigation.cld should contain namespace");
            assertTrue(weatherContent.contains("@statement"), "simple_weather.cld should contain @statement");
            assertTrue(navContent.contains("@statement"), "simple_navigation.cld should contain @statement");

        } catch (IOException e) {
            fail("Failed to read .cld files: " + e.getMessage());
        }
    }

    @Test
    void testEngineStatistics() {
        // Load grammars and test statistics
        try {
            engine.loadGrammarFile("src/main/resources/simple_weather.cld");
            engine.loadGrammarFile("src/main/resources/simple_navigation.cld");
        } catch (Exception e) {
            fail("Failed to load grammar files: " + e.getMessage());
        }

        // Test that statistics can be printed without error
        assertDoesNotThrow(() -> {
            engine.printStatistics();
        });

        // Test that we can get FSM information
        assertTrue(engine.getFSMs().size() > 0);

        for (var fsm : engine.getFSMs()) {
            assertNotNull(fsm.getName());
            assertNotNull(fsm.getIntentName());
            assertTrue(fsm.getAllStates().size() > 0);
        }
    }
}