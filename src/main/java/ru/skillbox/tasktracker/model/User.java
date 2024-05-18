package ru.skillbox.tasktracker.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String username;
    private String email;
}