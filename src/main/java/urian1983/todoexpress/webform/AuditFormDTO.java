package urian1983.todoexpress.webform;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import urian1983.todoexpress.model.LogLevel;

@Hidden
public class AuditFormDTO {

    @NotNull(message = "Selecciona un nivel")
    private LogLevel level;

    @NotNull(message = "Indica el ID de la tarea asociada")
    private Long taskId;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;

    public AuditFormDTO() {
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}