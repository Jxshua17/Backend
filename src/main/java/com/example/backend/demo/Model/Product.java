package com.example.backend.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {


    //TODO -> the generated value type "sequence" doesn't give me what i want as far as the id is concerned. i want to be able to get the count from the database and do a plus one for the data being added.
    //create an annotation maybe.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ColumnDefault("true")
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;
    @Lob
    private byte [] imageDate;

    //a constructor was the issue.

}


