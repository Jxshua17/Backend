package com.example.backend.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table(name = "Users")
public class Users {

    @Id
    private int id;
    private String username;
    private String password;
}
