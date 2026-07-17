package urian1983.todoexpress.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "For security repeat password")
        String confirmPassword) {
}
