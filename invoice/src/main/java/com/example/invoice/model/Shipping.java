package com.example.invoice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // This was missing the @Id annotation
    
    private String name;
    private String address;
    private String contact;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
