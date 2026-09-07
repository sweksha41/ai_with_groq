package com.learn.groq_demo.controller;

import com.learn.groq_demo.service.GroqService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/groq")
public class GroqController {

    private final GroqService groqService;

    public GroqController(GroqService groqService) {
        this.groqService = groqService;
    }

    @GetMapping("/ask")
    public Mono<String> ask(@RequestParam String prompt) {
        return groqService.ask(prompt);
    }
}
