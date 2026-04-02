package com.example.backend.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
@Component
public class Users {

    @Id
    private int id;
    private String username;
    private String password;
}
