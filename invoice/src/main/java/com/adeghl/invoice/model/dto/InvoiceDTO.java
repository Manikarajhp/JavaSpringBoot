package com.adeghl.invoice.model.dto;

import com.adeghl.invoice.model.Customer;
import com.adeghl.invoice.model.Shipping;

import java.util.List;

public class InvoiceDTO {
    private Customer customer;
    private Shipping shipping;
    private List<InvoiceItemDTO> items;

    // Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<InvoiceItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDTO> items) {
        this.items = items;
    }
}