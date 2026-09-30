package urian1983.todoexpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.service.TaskService;

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

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una tarea existente", description = "Elimina una nueva una tarea específica.")

    public void deleteTask(@Valid @PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener una tarea existente", description = "Obtiene una tarea específica que exista.")

    public TaskResponse getTask(@Valid @PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasks")
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
}
