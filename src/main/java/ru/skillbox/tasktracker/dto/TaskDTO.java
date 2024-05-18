package ru.skillbox.tasktracker.dto;

import lombok.Getter;
import lombok.Setter;
import ru.skillbox.tasktracker.model.TaskStatus;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
public class TaskDTO {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private TaskStatus status;
    private String authorId;
    private String assigneeId;
    private Set<String> observerIds;

    private UserDTO author;
    private UserDTO assignee;
    private Set<UserDTO> observers;
}
