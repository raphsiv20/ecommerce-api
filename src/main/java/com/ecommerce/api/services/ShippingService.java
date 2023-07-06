package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.ShippingDto;
import com.ecommerce.api.repositories.ShippingRepository;

import java.util.Collection;

public interface ShippingService {
    Collection<ShippingDto> getAll();
    ShippingDto getAShippingInfosByKey(long key);
    void createShippingInfos(ShippingDto shippingDto);
}
