package com.adeghl.invoice.repository;

import com.adeghl.invoice.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}