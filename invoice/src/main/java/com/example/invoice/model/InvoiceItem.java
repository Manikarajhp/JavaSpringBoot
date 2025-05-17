package com.example.invoice.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class InvoiceItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Invoice invoice;

    @ManyToOne
    private Product product;

    private int quantity;
    private double tax;
    private double discount;
    private double total;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

// existing fields, constructors, and methods

public Long getProductId() {
    if (product != null) {
        return product.getId();
    }
    return null;
}

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}