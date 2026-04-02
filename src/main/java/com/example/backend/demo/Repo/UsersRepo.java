package com.example.backend.demo.Repo;

import com.example.backend.demo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo  extends JpaRepository<Users, Integer> {

}
