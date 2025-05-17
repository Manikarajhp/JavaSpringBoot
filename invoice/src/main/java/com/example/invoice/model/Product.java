package com.example.invoice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // This was missing the @Id annotation
    
    private String name;
    private Double price;
}
