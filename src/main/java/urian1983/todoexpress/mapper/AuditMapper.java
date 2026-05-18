package urian1983.todoexpress.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import urian1983.todoexpress.dto.AuditRequest;
import urian1983.todoexpress.dto.AuditResponse;
import urian1983.todoexpress.model.Audit;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Audit toEntity(AuditRequest request);
    AuditResponse toResponse (Audit audit);
    List<AuditResponse> toResponseList(List<Audit> audits);
}
