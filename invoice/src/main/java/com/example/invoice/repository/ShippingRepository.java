package com.example.invoice.repository;

import com.example.invoice.model.Shipping; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.stereotype.Repository;

@Repository public interface ShippingRepository extends JpaRepository<Shipping, Long> { }