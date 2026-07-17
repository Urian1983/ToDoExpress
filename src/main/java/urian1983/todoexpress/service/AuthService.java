package urian1983.todoexpress.service;

import urian1983.todoexpress.dto.AuthResponse;
import urian1983.todoexpress.dto.LoginRequest;
import urian1983.todoexpress.dto.RegisterRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest registerRequest);
}
