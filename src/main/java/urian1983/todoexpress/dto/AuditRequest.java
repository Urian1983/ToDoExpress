package urian1983.todoexpress.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import urian1983.todoexpress.model.LogLevel;

@Schema(description = "Datos requeridos para registrar una nueva auditoría en el sistema")
public record AuditRequest(
        @Schema(description = "Nivel de severidad del log", example = "INFO", allowableValues = {"INFO", "ERROR"})
        LogLevel level,

        @Schema(description = "ID de la tarea asociada a este evento de auditoría", example = "10")
        Long taskId,

        @Schema(description = "Mensaje detallado del evento acontecido", example = "Se ha actualizado el estado de la tarea a COMPLETADA")
        String message
) {
}
