package com.adeghl.invoice.service;

import com.adeghl.invoice.model.*;
import com.adeghl.invoice.model.dto.InvoiceDTO;
import com.adeghl.invoice.model.dto.InvoiceItemDTO;
import com.adeghl.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ShippingService shippingService;
    
    @Autowired
    private ProductService productService;

    @Transactional
public Invoice generateInvoice(InvoiceDTO invoiceDTO) {
    // Validate input
    if (invoiceDTO == null || invoiceDTO.getCustomer() == null || invoiceDTO.getShipping() == null) {
        throw new IllegalArgumentException("Invoice data cannot be null");
    }
    if (invoiceDTO.getItems() == null || invoiceDTO.getItems().isEmpty()) {
        throw new IllegalArgumentException("Invoice must contain at least one item");
    }

    // Save customer with validation
    Customer customer = invoiceDTO.getCustomer();
    if (customer.getName() == null || customer.getName().isEmpty()) {
        throw new IllegalArgumentException("Customer name is required");
    }
    customer = customerService.saveCustomer(customer);

    // Save shipping with validation
    Shipping shipping = invoiceDTO.getShipping();
    if (shipping.getName() == null || shipping.getName().isEmpty()) {
        throw new IllegalArgumentException("Shipping name is required");
    }
    shipping = shippingService.saveShipping(shipping);

    // Create invoice
    Invoice invoice = new Invoice();
    invoice.setInvoiceNumber(generateInvoiceNumber());
    invoice.setInvoiceDate(new Date());
    invoice.setCustomer(customer);
    invoice.setShipping(shipping);
    
    // Initialize totals
    double subtotal = 0;
    double totalTax = 0;
    double totalDiscount = 0;
    
    // Process items
    for (InvoiceItemDTO itemDTO : invoiceDTO.getItems()) {
        Product product = productService.saveProduct(itemDTO.getProduct());
        
        InvoiceItem item = new InvoiceItem();
        item.setInvoice(invoice);  // THIS IS THE CRUCIAL LINE YOU'RE MISSING
        item.setSerialNo(itemDTO.getSerialNo());
        item.setProduct(product);
        item.setQuantity(itemDTO.getQuantity());
        item.setPrice(itemDTO.getPrice());
        item.setTax(itemDTO.getTax());
        item.setDiscount(itemDTO.getDiscount());
        
        double itemTotal = itemDTO.getPrice() * itemDTO.getQuantity();
        double itemTax = itemTotal * (itemDTO.getTax() / 100);
        double itemDiscount = itemTotal * (itemDTO.getDiscount() / 100);
        double itemFinalTotal = itemTotal + itemTax - itemDiscount;
        
        item.setTotal(itemFinalTotal);
        
        subtotal += itemTotal;
        totalTax += itemTax;
        totalDiscount += itemDiscount;
        
        invoice.getItems().add(item);
    }
    
    // Set calculated totals
    invoice.setSubtotal(subtotal);
    invoice.setTotalTax(totalTax);
    invoice.setTotalDiscount(totalDiscount);
    invoice.setGrandTotal(subtotal + totalTax - totalDiscount);
    
    return invoiceRepository.save(invoice);
}
    
    private String generateInvoiceNumber() {
        return "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}