package com.example.backend.demo.Service;

import com.example.backend.demo.Model.Users;
import com.example.backend.demo.Repo.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private UsersRepo usersRepo;

    public UsersService(UsersRepo usersRepo){
        this.usersRepo = usersRepo;
    }

    public List<Users> getAllUsers(){
        return usersRepo.findAll();
    }

    public boolean isUserPresent(String username, String password){

        /*List<Users> listOfUsers = new ArrayList<>();
        listOfUsers =
        if (username.equals(usersRepo.))*/
        return true;
    }
}
