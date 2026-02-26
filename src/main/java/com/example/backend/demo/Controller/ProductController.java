package com.example.backend.demo.Controller;

import com.example.backend.demo.Model.Product;
import com.example.backend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @RequestMapping("/")
    public String greet(){
        return "Welcome to my fucking website";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @RequestMapping("/error")
    public String errorMessage(){
        return "please check your url again";
    }

}
