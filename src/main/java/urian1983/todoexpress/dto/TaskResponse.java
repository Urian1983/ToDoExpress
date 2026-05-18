package urian1983.todoexpress.dto;

import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String description,
        TaskPriority priority,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
