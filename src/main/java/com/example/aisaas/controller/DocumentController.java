package com.example.aisaas.controller;

import com.example.aisaas.entity.Document;
import com.example.aisaas.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tenantId") Long tenantId) throws IOException {
        Document document = documentService.uploadDocument(file, tenantId);
        return ResponseEntity.ok(document);
    }
}
