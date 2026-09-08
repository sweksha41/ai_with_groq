package com.learn.groq_demo.model;

import java.util.List;

public record GroqResponse(
        List<Choice> choices
) {}

