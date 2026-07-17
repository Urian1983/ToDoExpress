package urian1983.todoexpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.AuthResponse;
import urian1983.todoexpress.dto.ErrorResponseDTO;
import urian1983.todoexpress.dto.LoginRequest;
import urian1983.todoexpress.dto.RegisterRequest;
import urian1983.todoexpress.service.AuthService;

@RequestMapping("/api/auth")
@RestController
@Tag(name = "Autenticación", description = "Registro e inicio de sesión de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Iniciar sesión", description = "Autentica a un usuario existente y devuelve un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login correcto, token generado"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuario o contraseña incorrectos",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Datos de la petición inválidos")
    })
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario con rol USER y devuelve un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El username ya existe",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Las contraseñas no coinciden o los datos son inválidos")
    })
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}