package urian1983.todoexpress.dto;


import urian1983.todoexpress.model.LogLevel;

public record AuditRequest(
         LogLevel level,
         Long taskId,
         String message
) {
}
