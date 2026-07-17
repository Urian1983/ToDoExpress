package urian1983.todoexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import urian1983.todoexpress.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
