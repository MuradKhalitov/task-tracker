package ru.skillbox.tasktracker.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.model.User;
import ru.skillbox.tasktracker.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(String id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
