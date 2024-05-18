package ru.skillbox.tasktracker.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.tasktracker.dto.TaskDTO;
import ru.skillbox.tasktracker.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO taskDTO);
}
