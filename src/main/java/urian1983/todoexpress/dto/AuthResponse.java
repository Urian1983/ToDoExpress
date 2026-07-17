package urian1983.todoexpress.dto;


import lombok.Builder;

@Builder
public record AuthResponse(String token, UserResponse user) {
}
