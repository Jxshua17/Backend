package com.example.backend.demo.Service;

import com.example.backend.demo.Controller.ProductController;
import com.example.backend.demo.Model.Product;
import com.example.backend.demo.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    //i should be using a constructor and not a setter.


    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
}
