package com.example.aisaas.service;

import com.example.aisaas.rag.PromptBuilder;
import com.example.aisaas.rag.VectorSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiChatService {

    private final VectorSearchService vectorSearchService;
    private final PromptBuilder promptBuilder;
    private final ChatModel chatModel;
    private final TicketService ticketService;

    public Map<String, String> chat(String question, Long tenantId) {
        List<String> relevantChunks = vectorSearchService.search(question, tenantId, 3);
        String prompt = promptBuilder.build(question, relevantChunks);
        Prompt aiPrompt = new Prompt(new UserMessage(prompt));
        String answer = chatModel.call(aiPrompt).getResult().getOutput().getText();

        if (answer.toLowerCase().contains("i don't have enough information")) {
            ticketService.createTicket(
                    "Unanswered question: " + question,
                    "The AI could not answer this question: " + question,
                    tenantId
            );
            return Map.of(
                    "answer", answer,
                    "ticket", "A support ticket has been created for your question."
            );
        }

        return Map.of("answer", answer);
    }
}
