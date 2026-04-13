package com.example.aisaas.rag;

import com.example.aisaas.entity.DocumentChunk;
import com.example.aisaas.repository.DocumentChunkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VectorSearchService {

    private final DocumentChunkRepository documentChunkRepository;
    private final EmbeddingService embeddingService;

    public List<String> search(String query, Long tenantId, int topK) {
        double[] queryEmbedding = embeddingService.embedText(query);
        List<DocumentChunk> allChunks = documentChunkRepository
                .findByDocumentTenantId(tenantId);

        return allChunks.stream()
                .filter(chunk -> chunk.getEmbedding() != null)
                .sorted((a, b) -> Double.compare(
                        cosineSimilarity(b.getEmbedding(), queryEmbedding),
                        cosineSimilarity(a.getEmbedding(), queryEmbedding)
                ))
                .limit(topK)
                .map(DocumentChunk::getContent)
                .collect(Collectors.toList());
    }

    private double cosineSimilarity(double[] a, double[] b) {
        double dot = 0, normA = 0, normB = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}