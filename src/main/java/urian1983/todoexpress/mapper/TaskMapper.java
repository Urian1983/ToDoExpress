package urian1983.todoexpress.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import urian1983.todoexpress.dto.TaskRequest;
import urian1983.todoexpress.dto.TaskResponse;
import urian1983.todoexpress.model.Task;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequest taskRequest);
    TaskResponse toResponse(Task task);
    List<TaskResponse> toResponseList(List<Task> tasks);
}
