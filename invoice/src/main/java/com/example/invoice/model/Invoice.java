package com.example.invoice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // This was missing the @Id annotation
    
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> items;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
