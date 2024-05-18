package ru.skillbox.tasktracker.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.model.Task;
import ru.skillbox.tasktracker.model.User;
import ru.skillbox.tasktracker.repository.TaskRepository;
import ru.skillbox.tasktracker.repository.UserRepository;

import java.util.Set;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Flux<Task> findAll() {
        return taskRepository.findAll()
                .flatMap(this::populateTaskRelations);
    }

    public Mono<Task> findById(String id) {
        return taskRepository.findById(id)
                .flatMap(this::populateTaskRelations);
    }

    public Mono<Task> create(Task task) {
        return taskRepository.save(task);
    }

    public Mono<Task> update(String id, Task task) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    task.setId(id);
                    return taskRepository.save(task);
                });
    }

    public Mono<Void> deleteById(String id) {
        return taskRepository.deleteById(id);
    }

    public Mono<Task> addObserver(String taskId, String observerId) {
        return taskRepository.findById(taskId)
                .flatMap(task -> {
                    task.getObserverIds().add(observerId);
                    return taskRepository.save(task);
                });
    }

    private Mono<Task> populateTaskRelations(Task task) {
        Mono<User> authorMono = userRepository.findById(task.getAuthorId()).defaultIfEmpty(new User());
        Mono<User> assigneeMono = userRepository.findById(task.getAssigneeId()).defaultIfEmpty(new User());
        Flux<User> observersFlux = Flux.fromIterable(task.getObserverIds())
                .flatMap(userRepository::findById)
                .collectList()
                .flatMapMany(Flux::fromIterable);

        return Mono.zip(authorMono, assigneeMono, observersFlux.collectList())
                .map(tuple -> {
                    task.setAuthor(tuple.getT1());
                    task.setAssignee(tuple.getT2());
                    task.setObservers(Set.copyOf(tuple.getT3()));
                    return task;
                });
    }
}
