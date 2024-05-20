package ru.skillbox.tasktracker.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.model.User;

import java.util.Optional;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    Mono<User> findById(String id);
    Mono<User> findByUsername(String username);
}