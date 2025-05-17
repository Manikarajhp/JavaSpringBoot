package com.example.invoice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // This was missing the @Id annotation
    
    private String name;
    private String address;
    private String contact;
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
