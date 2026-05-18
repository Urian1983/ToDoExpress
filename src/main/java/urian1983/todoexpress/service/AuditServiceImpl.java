package urian1983.todoexpress.service;


import org.springframework.stereotype.Service;
import urian1983.todoexpress.dto.AuditRequest;
import urian1983.todoexpress.dto.AuditResponse;
import urian1983.todoexpress.exceptions.NotFoundException;
import urian1983.todoexpress.mapper.AuditMapper;
import urian1983.todoexpress.model.Audit;
import urian1983.todoexpress.repository.AuditRepository;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public AuditServiceImpl(AuditRepository auditRepository, AuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.auditMapper = auditMapper;
    }

    @Override
    public AuditResponse createAudit(AuditRequest newAudit) {

        Audit audit = auditMapper.toEntity(newAudit);
        Audit savedAudit = auditRepository.save(audit);
        return auditMapper.toResponse(savedAudit);
    }

    @Override
    public AuditResponse getAuditById(Long id) {
       Audit auditToGet = auditRepository.findById(id)
               .orElseThrow(() ->new NotFoundException("Audit with id " + id + " not found"));

       return auditMapper.toResponse(auditToGet);
    }


    @Override
    public List<AuditResponse> getAllAudits() {
        List<Audit> audits= auditRepository.findAll();
            if(audits.isEmpty()){
            throw new NotFoundException("No audits in the database");
        }

        return auditMapper.toResponseList(audits);
    }
}
