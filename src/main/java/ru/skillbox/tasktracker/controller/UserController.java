package ru.skillbox.tasktracker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.dto.UserDTO;
import ru.skillbox.tasktracker.mapper.UserMapper;
import ru.skillbox.tasktracker.model.User;
import ru.skillbox.tasktracker.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Flux<UserDTO> findAll() {
        return userService.findAll()
                .map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Mono<UserDTO> findById(@PathVariable String id) {
        return userService.findById(id)
                .map(userMapper::toDto);
    }

    @PostMapping("/create")
    public Mono<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userService.create(user)
                .map(userMapper::toDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Mono<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userService.update(id, user)
                .map(userMapper::toDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('MANAGER')")
    public Mono<Void> deleteById(@PathVariable String id) {
        return userService.deleteById(id);
    }
}
