package urian1983.todoexpress.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.ErrorResponseDTO;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.service.TaskService;

import java.util.List;

@RequestMapping("/api")
@RestController
@Tag(name = "Tareas (Tasks)", description = "Controlador para la gestión del ciclo de vida de las tareas")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear una nueva tarea", description = "Recibe los datos de una tarea y la almacena en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest){
        return service.createTask(taskRequest);
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Obtener una tarea por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea encontrada"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Tarea no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)) // <-- Swagger mostrará el JSON de error estructurado aquí
            )
    })
    public TaskResponse getTasks(@PathVariable Long id)
    {
        return service.getTaskById(id);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Listar todas las tareas", description = "Retorna una lista completa de todas las tareas registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida correctamente")
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks();
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Actualizar una tarea existente", description = "Modifica los campos de una tarea identificada por su ID con la información provista.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarea actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada para actualizar"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos")
    })
    public TaskResponse updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id){
        return service.updateTask(id, taskRequest);
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar una tarea", description = "Borra físicamente o lógicamente una tarea del sistema usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarea eliminada con éxito (sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    })
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }
}
