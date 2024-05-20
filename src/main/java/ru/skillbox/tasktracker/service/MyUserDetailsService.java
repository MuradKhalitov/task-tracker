package ru.skillbox.tasktracker.service;

import reactor.core.publisher.Mono;
import ru.skillbox.tasktracker.config.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skillbox.tasktracker.model.User;
import ru.skillbox.tasktracker.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException(username + " не найден.")))
                .block();

        return new MyUserDetails(user);
    }
}
