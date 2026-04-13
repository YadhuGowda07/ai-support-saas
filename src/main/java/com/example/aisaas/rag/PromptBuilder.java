package com.example.aisaas.rag;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String build(String question, List<String> contextChunks) {
        StringBuilder context = new StringBuilder();
        for (String chunk : contextChunks) {
            context.append(chunk).append("\n\n");
        }

        return "You are a friendly and helpful customer support assistant for TechCorp Solutions.\n" +
                "Use the context below to answer the question in a clear, concise and conversational tone.\n" +
                "Do NOT copy the context word for word — summarize and rephrase in your own words.\n" +
                "If the answer is not in the context, say \"I don't have enough information to answer that question.\"\n" +
                "Keep your answer focused and to the point — 2 to 4 sentences maximum unless more detail is needed.\n\n" +
                "Context:\n" + context +
                "\nQuestion: " + question +
                "\n\nAnswer:";
    }
}