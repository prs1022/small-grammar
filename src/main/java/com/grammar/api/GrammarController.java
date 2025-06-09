package com.grammar.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.grammar.core.Semantic;

@RestController
@RequestMapping("/api/v1")
public class GrammarController {
    
    @PostMapping("/match")
    public ResponseEntity<MatchResponse> match(@RequestBody MatchRequest request) {
        // 处理匹配请求
        // 返回意图和词槽结果
        MatchResponse response = new MatchResponse();
        return ResponseEntity.ok(response);
    }
}

// 临时添加请求和响应类
class MatchRequest {
    private String text;
    private String context;
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
}

class MatchResponse {
    private Semantic semantic;
    private long processingTime;
    
    public Semantic getSemantic() { return semantic; }
    public void setSemantic(Semantic semantic) { this.semantic = semantic; }
    public long getProcessingTime() { return processingTime; }
    public void setProcessingTime(long processingTime) { this.processingTime = processingTime; }
}