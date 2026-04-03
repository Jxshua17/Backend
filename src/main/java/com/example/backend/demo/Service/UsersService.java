package com.example.backend.demo.Service;

import com.example.backend.demo.Model.Users;
import com.example.backend.demo.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private UsersRepo usersRepo;
    @Autowired
    public UsersService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    private AuthenticationManager authManager;
    @Autowired
    public void setAuthManager(AuthenticationManager authManager){
        this.authManager = authManager;
    }

    public boolean isUserPresent(Users user){

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return auth.isAuthenticated();
    }
}
