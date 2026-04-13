package com.example.aisaas.controller;

import com.example.aisaas.service.AiChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiChatController {

    private final AiChatService aiChatService;

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(
            @RequestBody Map<String, String> request) {

        String question = request.get("question");
        Long tenantId = Long.parseLong(request.get("tenantId"));

        String answer = aiChatService.chat(question, tenantId);
        return ResponseEntity.ok(Map.of("answer", answer));
    }
}