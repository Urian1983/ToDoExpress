package urian1983.todoexpress.dto;

import urian1983.todoexpress.model.Role;

public record UserResponse(Long id, String username, Role role) {
}
