package urian1983.todoexpress.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Detalle del error devuelto por la API ante solicitudes fallidas")
public record ErrorResponseDTO(
        @Schema(description = "Mensaje explicativo de la razón del fallo", example = "La tarea con el ID provisto no existe.")
        String message
) {
}