package urian1983.todoexpress.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import urian1983.todoexpress.dto.AuthResponse;
import urian1983.todoexpress.dto.LoginRequest;
import urian1983.todoexpress.dto.RegisterRequest;
import urian1983.todoexpress.dto.UserResponse;
import urian1983.todoexpress.exceptions.NotFoundException;
import urian1983.todoexpress.exceptions.UserAlreadyExistsException;
import urian1983.todoexpress.mapper.UserMapper;
import urian1983.todoexpress.model.Role;
import urian1983.todoexpress.model.User;
import urian1983.todoexpress.repository.UserRepository;
import urian1983.todoexpress.security.JwtService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> {
                    return new NotFoundException("User not found");
                });

        String token = jwtService.generateToken(user.getUsername());
        UserResponse response = userMapper.toDTO(user);
        return new AuthResponse(token, response);

    }



    @Override
    public AuthResponse register(RegisterRequest registerRequest) {

        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = new User(
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                Role.USER
        );

        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        UserResponse response = userMapper.toDTO(user);

        return new AuthResponse(token, response);
    }
}
