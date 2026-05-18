package urian1983.todoexpress.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

public record TaskRequest(
        @NotBlank
        String description,
        @NotNull
        TaskPriority priority,
        @NotNull
        TaskStatus status)
        {
}
