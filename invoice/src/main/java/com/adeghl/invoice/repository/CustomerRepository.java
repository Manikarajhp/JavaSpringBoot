package com.adeghl.invoice.repository;

import com.adeghl.invoice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}