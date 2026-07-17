package urian1983.todoexpress.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urian1983.todoexpress.dto.UserRequest;
import urian1983.todoexpress.dto.UserResponse;
import urian1983.todoexpress.exceptions.NotFoundException;
import urian1983.todoexpress.mapper.UserMapper;
import urian1983.todoexpress.model.User;
import urian1983.todoexpress.repository.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.toDTO(user);
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setUsername(userRequest.username());
        user.setPassword(passwordEncoder.encode(userRequest.password()));

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }
}
