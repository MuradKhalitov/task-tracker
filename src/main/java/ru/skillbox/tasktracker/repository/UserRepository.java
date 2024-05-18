package ru.skillbox.tasktracker.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.skillbox.tasktracker.model.User;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
}