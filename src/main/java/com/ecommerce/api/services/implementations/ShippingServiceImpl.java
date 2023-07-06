package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.ShippingDto;
import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.ShippingEntity;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.repositories.ShippingRepository;
import com.ecommerce.api.repositories.UserRepository;
import com.ecommerce.api.services.CartService;
import com.ecommerce.api.services.ShippingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<ShippingDto> getAll() {
        Collection<ShippingDto> shippingDtosCollection = new ArrayList<>();
        for (ShippingEntity shippingEntity : this.shippingRepository.findAll()) {
            ShippingDto shippingDto = this.mapper.map(shippingEntity, ShippingDto.class);
            shippingDtosCollection.add(shippingDto);
        }
        return shippingDtosCollection;
    }

    @Override
    public ShippingDto getAShippingInfosByKey(long key) {
        return this.mapper.map(this.shippingRepository.findById(key).get(), ShippingDto.class);
    }

    @Override
    public void createShippingInfos(ShippingDto shippingDto) {
        this.shippingRepository.save(this.mapper.map(shippingDto, ShippingEntity.class));
    }


}
