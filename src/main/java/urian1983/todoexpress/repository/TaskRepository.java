package urian1983.todoexpress.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import urian1983.todoexpress.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {


}
