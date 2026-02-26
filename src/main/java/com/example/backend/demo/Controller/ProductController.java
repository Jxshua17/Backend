package com.example.backend.demo.Controller;

import com.example.backend.demo.Model.Product;
import com.example.backend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @RequestMapping("/welcome")
    public String greet(){
        return "Welcome to my fucking website";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @RequestMapping("/error")
    public String errorMessage(){
        return "please check your url again";
    }

    @RequestMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){
        Product product = service.getProductById(prodId);

        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
