package com.example.invoice.controller;

import com.example.invoice.model.*;
import com.example.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    
    @GetMapping
    public String showInvoiceForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("shipping", new Shipping());
        return "invoice";
    }
    
    @PostMapping("/customer")
    @ResponseBody
    public Customer saveCustomer(@RequestBody Customer customer) {
        return invoiceService.saveCustomer(customer);
    }
    
    @PostMapping("/shipping")
    @ResponseBody
    public Shipping saveShipping(@RequestBody Shipping shipping, @RequestParam Long customerId) {
        return invoiceService.saveShipping(shipping, customerId);
    }
    
    @GetMapping("/products")
    @ResponseBody
    public List<Product> searchProducts(@RequestParam String query) {
        return invoiceService.searchProducts(query);
    }
    
    @PostMapping("/generate")
    @ResponseBody
    public Invoice generateInvoice(@RequestBody InvoiceRequest request) {
        return invoiceService.generateInvoice(request);
    }
}
