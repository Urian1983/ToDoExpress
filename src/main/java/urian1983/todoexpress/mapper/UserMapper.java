package urian1983.todoexpress.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import urian1983.todoexpress.dto.UserRequest;
import urian1983.todoexpress.dto.UserResponse;
import urian1983.todoexpress.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromRequest(UserRequest userRequest, @MappingTarget User user);
}
