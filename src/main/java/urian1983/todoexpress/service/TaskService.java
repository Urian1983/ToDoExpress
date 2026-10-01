package urian1983.todoexpress.service;


import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest newTask);
    TaskResponse updateTask(Long id, TaskRequest updateTask);
    void deleteTask(Long id);
    TaskResponse getTaskById(Long id);
    TaskResponse getTaskByDescription(String description);
    List<TaskResponse> getAllTasks();
    List<TaskResponse> getTasksByPriority(TaskPriority priority);
    List<TaskResponse> getTasksByStatus(TaskStatus status);

}
