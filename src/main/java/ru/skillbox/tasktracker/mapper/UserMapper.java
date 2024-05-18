package ru.skillbox.tasktracker.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.tasktracker.dto.UserDTO;
import ru.skillbox.tasktracker.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}