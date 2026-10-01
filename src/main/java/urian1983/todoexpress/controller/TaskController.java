package urian1983.todoexpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.model.TaskPriority;
import urian1983.todoexpress.model.TaskStatus;
import urian1983.todoexpress.service.TaskService;

import java.util.List;

@RequestMapping("/api/tasks")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una nueva tarea", description = "Registra una nueva una tarea específica.")
    public TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una tarea existente", description = "Elimina una nueva una tarea específica.")

    public void deleteTask(@Valid @PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener una tarea existente", description = "Obtiene una tarea específica que exista.")

    public TaskResponse getTask(@Valid @PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener todas las tareas", description = "Obtiene todas las tareas.")

    public void getAllTask(){
        taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(id, taskRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getTasksByPriority(@RequestParam TaskPriority priority){
        return taskService.getTasksByPriority(priority);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> getTasksByStatus(@RequestParam TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse getTaskByDescription(@RequestParam String description) {
        return taskService.getTaskByDescription(description);
    }
}
