package com.example.backend.demo.Repo;

import com.example.backend.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p where " +
            "LOWER(p.name) like lower(concat('%', :keyword, '%') ) or " +
            "LOWER(p.description) like lower(concat('%', :keyword, '%') ) or " +
            "LOWER(p.brand) like lower(concat('%', :keyword, '%') ) or " +
            "LOWER(p.category) like lower(concat('%', :keyword, '%') ) ")
    List<Product> search(String keyword);
}
