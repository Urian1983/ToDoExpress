package urian1983.todoexpress.webform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

/**
 * Este DTO existe solo para la capa web (formularios Thymeleaf).
 *
 * ¿Por qué no reusamos el record TaskRequest que ya usa la API REST?
 * Porque th:field="*{...}" de Thymeleaf necesita getters y setters
 * convencionales (getDescription/setDescription) para poder leer y
 * escribir el valor del campo en cada submit. Los records solo tienen
 * accesores sin prefijo (description()) y son inmutables, lo que
 * complica el binding de formularios HTML clásicos.
 *
 * Mantener esta clase separada también evita acoplar tu contrato de
 * API (lo que espera el cliente REST) con el detalle de implementación
 * de tu formulario HTML (lo que espera Thymeleaf).
 */
public class TaskFormDTO {

    private Long id;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    @NotNull(message = "Selecciona una prioridad")
    private TaskPriority priority;

    @NotNull(message = "Selecciona un estado")
    private TaskStatus status;

    public TaskFormDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
