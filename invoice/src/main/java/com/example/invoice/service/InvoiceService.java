package com.example.invoice.service;

import com.example.invoice.model.*;
// import com.example.invoice.model.InvoiceItemRequest; // Removed because it cannot be resolved
import com.example.invoice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ShippingRepository shippingRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    public Customer saveCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }
    
    public Shipping saveShipping(Shipping shipping, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        shipping.setCustomer(customer);
        return shippingRepository.save(shipping);
    }
    
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContaining(query);
    }
    
    public Invoice generateInvoice(InvoiceRequest request) {
        Customer customer = saveCustomer(request.getCustomer());
        Shipping shipping = saveShipping(request.getShipping(), customer.getId());
        
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8));
        invoice.setCustomer(customer);
        invoice.setShipping(shipping);
        invoice.setCreatedAt(LocalDateTime.now());
        
        // Calculate and set invoice items
        double grandTotal =0;
        // Please ensure that request.getItems() returns a list of a valid type (e.g., InvoiceItem or a DTO you have defined)
        for (InvoiceItem itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID " + itemRequest.getProductId()));
            InvoiceItem item = new InvoiceItem();
            item.setProduct(product); // Never null
            // ...set other fields...
            item.setInvoice(invoice);
            item.setQuantity(itemRequest.getQuantity());
            item.setTax(itemRequest.getTax());
            item.setDiscount(itemRequest.getDiscount());

            double subtotal = product.getPrice() * itemRequest.getQuantity();
            double taxAmount = subtotal * (itemRequest.getTax() / 100);
            double discountAmount = subtotal * (itemRequest.getDiscount() / 100);
            double total = subtotal + taxAmount - discountAmount;

            item.setTotal(total);
            grandTotal += total;

            invoice.getItems().add(item);
        }
        
        return invoiceRepository.save(invoice);
    }
}
