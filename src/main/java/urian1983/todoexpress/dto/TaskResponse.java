package urian1983.todoexpress.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;
import java.time.LocalDateTime;

@Schema(description = "Información completa de la tarea devuelta por el sistema")
public record TaskResponse(
        @Schema(description = "Identificador único de la tarea", example = "1")
        Long id,

        @Schema(description = "Descripción de la actividad", example = "Finalizar el reporte financiero del mes")
        String description,

        @Schema(description = "Prioridad de la tarea", example = "HIGH")
        TaskPriority priority,

        @Schema(description = "Estado actual de la tarea", example = "PENDING")
        TaskStatus status,

        @Schema(description = "Fecha y hora de creación del registro", example = "2026-07-12T10:00:00")
        LocalDateTime createdAt,

        @Schema(description = "Fecha y hora de la última actualización", example = "2026-07-12T14:30:00")
        LocalDateTime updatedAt
) {
}
