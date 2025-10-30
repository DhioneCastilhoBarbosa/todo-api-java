package com.example.todo_api.dto;

import java.time.Instant;
import java.util.UUID;

public record TodoResponse(
    UUID id,
    String title,
    boolean done,
    Instant createdAt
) {}