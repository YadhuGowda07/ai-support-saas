package com.example.aisaas.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenTextSplitter {

    private static final int CHUNK_SIZE = 500;
    private static final int OVERLAP = 50;

    public List<String> split(String text) {
        List<String> chunks = new ArrayList<>();
        String[] words = text.split("\\s+");

        int i = 0;
        while (i < words.length) {
            StringBuilder chunk = new StringBuilder();
            int end = Math.min(i + CHUNK_SIZE, words.length);

            for (int j = i; j < end; j++) {
                chunk.append(words[j]).append(" ");
            }

            chunks.add(chunk.toString().trim());
            i += CHUNK_SIZE - OVERLAP;
        }

        return chunks;
    }
}
