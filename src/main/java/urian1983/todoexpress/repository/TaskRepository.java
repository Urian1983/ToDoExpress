package urian1983.todoexpress.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import urian1983.todoexpress.model.Task;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByDescriptionContainingIgnoreCase(String description);


}
