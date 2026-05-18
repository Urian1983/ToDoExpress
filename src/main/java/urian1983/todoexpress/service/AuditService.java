package urian1983.todoexpress.service;

import urian1983.todoexpress.dto.AuditRequest;
import urian1983.todoexpress.dto.AuditResponse;

import java.util.List;

public interface AuditService {

    AuditResponse createAudit(AuditRequest newAudit);
    AuditResponse getAuditById(Long id);
    List<AuditResponse> getAllAudits();

}
