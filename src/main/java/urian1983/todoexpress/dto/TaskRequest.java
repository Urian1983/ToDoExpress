package urian1983.todoexpress.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

@Schema(description = "Datos necesarios para crear o actualizar una tarea")
public record TaskRequest(
        @Schema(description = "Descripción detallada de la actividad por realizar", example = "Finalizar el reporte financiero del mes")
        @NotBlank
        String description,

        @Schema(description = "Prioridad asignada a la tarea", example = "HIGH")
        @NotNull
        TaskPriority priority,

        @Schema(description = "Estado de flujo de la tarea", example = "PENDING")
        @NotNull
        TaskStatus status
) {
}