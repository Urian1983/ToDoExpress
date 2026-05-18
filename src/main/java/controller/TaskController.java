package controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.service.TaskService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest){
        return service.createTask(taskRequest);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTasks(@PathVariable Long id)
    {
        return service.getTaskById(id);
    }

    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/tasks/{id}")
    public TaskResponse updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id){
        return service.updateTask(id, taskRequest);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }






}
