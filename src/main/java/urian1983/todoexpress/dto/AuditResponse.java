package urian1983.todoexpress.dto;

import urian1983.todoexpress.model.LogLevel;

import java.time.LocalDateTime;

public record AuditResponse(Long id, LogLevel level,
                            Long taskId,
                            String message,
                            LocalDateTime createdAt) {

}
