package com.example.aisaas.controller;

import com.example.aisaas.service.AiChatService;
import com.example.aisaas.util.JwtUtil; // ✅ IMPORT THIS
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiChatController {

    private final AiChatService aiChatService;
    private final JwtUtil jwtUtil; // ✅ FIXED (uppercase J)

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(
            @RequestBody Map<String, String> request,
            @RequestHeader("Authorization") String authHeader) {

        // ✅ Get message from frontend
        String question = request.get("message");

        // ✅ Extract token from header
        String token = authHeader.substring(7); // remove "Bearer "

        // ✅ Extract tenantId from JWT
        Long tenantId = jwtUtil.extractTenantId(token);

        // ✅ Call service
        return ResponseEntity.ok(aiChatService.chat(question, tenantId));
    }
}