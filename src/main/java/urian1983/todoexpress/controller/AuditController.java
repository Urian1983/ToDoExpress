package urian1983.todoexpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.AuditRequest;
import urian1983.todoexpress.dto.AuditResponse;
import urian1983.todoexpress.service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Auditoría (Audits)", description = "Controlador para el seguimiento y registro de eventos del sistema")
public class AuditController {

    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping("/audits")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un registro de auditoría", description = "Registra un evento o log asociado a una tarea específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Auditoría registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Petición inválida o error en los datos de entrada")
    })
    public AuditResponse createAudit(@Valid @RequestBody AuditRequest auditRequest){
        return service.createAudit(auditRequest);
    }

    @GetMapping("/audits/{id}")
    @Operation(summary = "Obtener auditoría por ID", description = "Recupera los detalles de un registro de auditoría específico mediante su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro de auditoría encontrado"),
            @ApiResponse(responseCode = "404", description = "Registro de auditoría no encontrado")
    })
    public AuditResponse getAuditsById(@Parameter(description = "ID único de la auditoría a consultar", example = "1") @Valid @PathVariable Long id)
    {
        return service.getAuditById(id);
    }

    @GetMapping("/audits")
    @Operation(summary = "Listar todas las auditorías", description = "Obtiene el historial completo de los eventos de auditoría registrados.")
    @ApiResponse(responseCode = "200", description = "Listado de auditorías obtenido correctamente")
    public List<AuditResponse> getAllAudits() {
        return service.getAllAudits();
    }

}
