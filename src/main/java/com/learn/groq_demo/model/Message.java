package com.learn.groq_demo.model;

public record Message(
        String role,
        String content
) {}
