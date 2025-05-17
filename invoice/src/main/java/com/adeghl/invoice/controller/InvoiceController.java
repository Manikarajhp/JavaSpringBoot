package com.adeghl.invoice.controller;

import com.adeghl.invoice.model.Invoice;
import com.adeghl.invoice.model.dto.InvoiceDTO;
import com.adeghl.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/create")
    public String showCreateInvoiceForm(Model model) {
        model.addAttribute("invoiceDTO", new InvoiceDTO());
        return "create-invoice";
}

    @PostMapping("/generate")
    public String generateInvoice(@ModelAttribute("invoiceDTO") InvoiceDTO invoiceDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-invoice";
        }
        System.out.println("Received InvoiceDTO: " + invoiceDTO);
        System.out.println("Customer: " + invoiceDTO.getCustomer());
        System.out.println("Shipping: " + invoiceDTO.getShipping());
        System.out.println("Items: " + invoiceDTO.getItems());

        System.out.println("Received: " + invoiceDTO); // Debug log
        Invoice invoice = invoiceService.generateInvoice(invoiceDTO);
        return "redirect:/invoice/view/" + invoice.getId();

        // Invoice invoice = invoiceService.generateInvoice(invoiceDTO);
        // model.addAttribute("invoice", invoice);
        // return "invoice-preview";
    }

    @GetMapping("/view/{id}")
    public String viewInvoice(@PathVariable Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "invoice-preview";
    }

    @GetMapping("/download/{id}")
    public String downloadInvoice(@PathVariable Long id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        model.addAttribute("invoice", invoice);
        return "invoice-print";
    }
}