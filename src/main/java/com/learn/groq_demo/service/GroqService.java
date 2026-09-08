package com.learn.groq_demo.service;

import com.learn.groq_demo.model.GroqResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    private final WebClient webClient;
    private final String apiKey;

    public GroqService(WebClient groqWebClient,
                       @Value("${groq.api.key}") String apiKey) {
        this.webClient = groqWebClient;
        this.apiKey = apiKey;
    }

    public Mono<String> ask(String prompt) {

        String model = "openai/gpt-oss-120b";
        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(GroqResponse.class)
                .map(groqResponse ->
                        groqResponse.choices().getFirst().message().content()
                );
    }
}
