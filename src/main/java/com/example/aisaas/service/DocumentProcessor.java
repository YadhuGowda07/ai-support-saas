package com.example.aisaas.service;

import com.example.aisaas.entity.Document;
import com.example.aisaas.entity.DocumentChunk;
import com.example.aisaas.repository.DocumentChunkRepository;
import com.example.aisaas.util.PdfTextExtractor;
import com.example.aisaas.util.TokenTextSplitter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentProcessor {

    private final PdfTextExtractor pdfTextExtractor;
    private final TokenTextSplitter tokenTextSplitter;
    private final DocumentChunkRepository documentChunkRepository;

    public void processDocument(Document document, MultipartFile file) throws IOException {
        String text = pdfTextExtractor.extractText(file);
        List<String> chunks = tokenTextSplitter.split(text);

        for (int i = 0; i < chunks.size(); i++) {
            DocumentChunk chunk = DocumentChunk.builder()
                    .content(chunks.get(i))
                    .chunkIndex(i)
                    .document(document)
                    .build();
            documentChunkRepository.save(chunk);
        }
    }
}
