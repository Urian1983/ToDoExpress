package urian1983.todoexpress.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import urian1983.todoexpress.model.LogLevel;

import java.time.LocalDateTime;

@Schema(description = "Información completa del registro de auditoría devuelto por el sistema")
public record AuditResponse(
        @Schema(description = "Identificador único de la auditoría", example = "1")
        Long id,
        @Schema(description = "Nivel de severidad del log", example = "INFO")
        LogLevel level,

        @Schema(description = "ID de la tarea relacionada", example = "10")
        Long taskId,
        @Schema(description = "Mensaje descriptivo del log", example = "Se ha actualizado el estado de la tarea a COMPLETADA")
        String message,
        @Schema(description = "Fecha y hora exacta en la que se generó la auditoría", example = "2026-07-12T21:43:08")
        LocalDateTime createdAt) {

}
