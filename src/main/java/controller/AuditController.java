package controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import urian1983.todoexpress.dto.AuditRequest;
import urian1983.todoexpress.dto.AuditResponse;
import urian1983.todoexpress.service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuditController {

    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping("/audits")
    @ResponseStatus(HttpStatus.CREATED)
    public AuditResponse createAudit(@Valid @RequestBody AuditRequest auditRequest){
        return service.createAudit(auditRequest);
    }

    @GetMapping("/audits/{id}")
    public AuditResponse getAuditsById(@Valid @PathVariable Long id)
    {
        return service.getAuditById(id);
    }

    @GetMapping("/audits")
    public List<AuditResponse> getAllAudits() {
        return service.getAllAudits();
    }



}
