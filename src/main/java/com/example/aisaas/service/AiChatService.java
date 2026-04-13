package com.example.aisaas.service;

import com.example.aisaas.rag.PromptBuilder;
import com.example.aisaas.rag.VectorSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiChatService {

    private final VectorSearchService vectorSearchService;
    private final PromptBuilder promptBuilder;
    private final ChatModel chatModel;

    public String chat(String question, Long tenantId) {
        List<String> relevantChunks = vectorSearchService.search(question, tenantId, 3);
        String prompt = promptBuilder.build(question, relevantChunks);
        Prompt aiPrompt = new Prompt(new UserMessage(prompt));
        return chatModel.call(aiPrompt).getResult().getOutput().getText();
    }
}

