package ru.skillbox.tasktracker.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.skillbox.tasktracker.model.Task;

public interface TaskRepository extends ReactiveCrudRepository<Task, String> {
}
