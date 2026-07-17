package urian1983.todoexpress.service;


import urian1983.todoexpress.dto.UserRequest;
import urian1983.todoexpress.dto.UserResponse;

public interface UserService {
    UserResponse getUserById(Long userId);
    UserResponse updateUser(Long userId, UserRequest userRequest);
    void deleteUser(Long userId);
}
