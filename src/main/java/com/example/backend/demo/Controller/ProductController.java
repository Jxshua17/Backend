package com.example.backend.demo.Controller;

import com.example.backend.demo.Model.Product;
import com.example.backend.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.DeserializationConfig;
import tools.jackson.databind.DeserializationFeature;

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

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                              @RequestPart MultipartFile imageFile){

        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Source - https://stackoverflow.com/a/79854456
// Posted by dani-vta, modified by community. See post 'Timeline' for change history
// Retrieved 2026-02-26, License - CC BY-SA 4.0

    @Bean
    public JsonMapperBuilderCustomizer customizer() {
        return builder -> builder
                .disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    }


    //creating the mapping for the images
    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageByProdId(@PathVariable int prodId){

        Product product = service.getProductById(prodId);
        byte [] imageFile = product.getImageDate();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    //updating
    /*@PutMapping("/product/{prodId}")
    public ResponseEntity<?> updateProductById(@PathVariable int prodId, @RequestPart Product product,
                                                     @RequestPart MultipartFile imageFile){

        try {
            Product product1 = service.getProductById(prodId);
            product1.setId(product.getId());
            Product product2 = service.updateProduct(product1, imageFile);

            return new ResponseEntity<>(product2, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/

    //deleting
    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<?> deleteProductById(@PathVariable int prodId){

        service.deleteProductById(prodId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
