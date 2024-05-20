package ru.skillbox.tasktracker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.dto.TaskDTO;
import ru.skillbox.tasktracker.mapper.TaskMapper;
import ru.skillbox.tasktracker.model.Task;
import ru.skillbox.tasktracker.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Flux<TaskDTO> findAll() {
        return taskService.findAll()
                .map(taskMapper::toDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Mono<TaskDTO> findById(@PathVariable String id) {
        return taskService.findById(id)
                .map(taskMapper::toDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER')")
    public Mono<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        return taskService.create(task)
                .map(taskMapper::toDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Mono<TaskDTO> update(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        return taskService.update(id, task)
                .map(taskMapper::toDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Mono<Void> deleteById(@PathVariable String id) {
        return taskService.deleteById(id);
    }

    @PostMapping("/{id}/observers/{observerId}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Mono<TaskDTO> addObserver(@PathVariable String id, @PathVariable String observerId) {
        return taskService.addObserver(id, observerId)
                .map(taskMapper::toDto);
    }
}
