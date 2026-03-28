package com.example.aisaas.service;

import com.example.aisaas.entity.Document;
import com.example.aisaas.entity.Tenant;
import com.example.aisaas.repository.DocumentRepository;
import com.example.aisaas.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final TenantRepository tenantRepository;
    private final DocumentProcessor documentProcessor;

    public Document uploadDocument(MultipartFile file, Long tenantId) throws IOException {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        Document document = Document.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .uploadedAt(LocalDateTime.now())
                .tenant(tenant)
                .build();

        documentRepository.save(document);
        documentProcessor.processDocument(document, file);

        return document;
    }
}
