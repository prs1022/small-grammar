package com.grammar.api;

import com.grammar.core.Semantic;
import com.grammar.core.Intent;
import com.grammar.matcher.IntentExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GrammarController.class)
class GrammarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IntentExtractor intentExtractor;

    @Autowired
    private ObjectMapper objectMapper;

    private MatchRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new MatchRequest();
        validRequest.setText("我想订机票");
    }

    @Test
    @DisplayName("测试匹配API - 成功情况")
    void testMatchApi_Success() throws Exception {
        // Given
        String inputText = "我想订机票";
        List<Intent> mockIntents = new ArrayList<>();
        when(intentExtractor.extractIntents(anyString(), anyList())).thenReturn(mockIntents);

        String requestJson = objectMapper.writeValueAsString(validRequest);

        // When & Then
        mockMvc.perform(post("/api/v1/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("测试匹配API - 空文本")
    void testMatchApi_EmptyText() throws Exception {
        // Given
        validRequest.setText("");
        String requestJson = objectMapper.writeValueAsString(validRequest);
        List<Intent> mockIntents = new ArrayList<>();
        when(intentExtractor.extractIntents(anyString(), anyList())).thenReturn(mockIntents);

        // When & Then
        mockMvc.perform(post("/api/v1/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void testMatchEndpoint_NullContext_ReturnsOk() throws Exception {
        validRequest.setContext(null);
        String requestJson = objectMapper.writeValueAsString(validRequest);
        
        List<Intent> mockIntents = new ArrayList<>();
        when(intentExtractor.extractIntents(anyString(), anyList())).thenReturn(mockIntents);

        mockMvc.perform(post("/api/v1/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }
}