package com.adeghl.invoice.service;

import com.adeghl.invoice.model.Shipping;
import com.adeghl.invoice.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    public Shipping saveShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
}