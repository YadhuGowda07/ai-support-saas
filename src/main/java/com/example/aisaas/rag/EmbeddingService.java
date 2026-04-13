package com.example.aisaas.rag;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;

    public double[] embedText(String text) {
        float[] floatEmbedding = embeddingModel.embed(text);
        double[] result = new double[floatEmbedding.length];
        for (int i = 0; i < floatEmbedding.length; i++) {
            result[i] = floatEmbedding[i];
        }
        return result;
    }
}