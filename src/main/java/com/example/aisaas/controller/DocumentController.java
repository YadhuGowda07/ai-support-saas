package com.example.aisaas.controller;

import com.example.aisaas.service.DocumentService;
import com.example.aisaas.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final JwtUtil jwtUtil;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader
    ) throws Exception {

        String token = authHeader.substring(7);

        Long tenantId = jwtUtil.extractTenantId(token);

        documentService.uploadDocument(file, tenantId); // ✅ FIXED

        return ResponseEntity.ok("File uploaded successfully");
    }
}