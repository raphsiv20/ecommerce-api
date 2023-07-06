package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.ProductDto;
import com.ecommerce.api.models.dtos.ShippingDto;
import com.ecommerce.api.models.requests.ProductRequest;
import com.ecommerce.api.models.requests.ShippingRequest;
import com.ecommerce.api.models.responses.CartResponse;
import com.ecommerce.api.models.responses.ProductResponse;
import com.ecommerce.api.models.responses.ProductStockResponse;
import com.ecommerce.api.models.responses.ShippingResponse;
import com.ecommerce.api.services.ProductService;
import com.ecommerce.api.services.ShippingService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/v1/shippings")
@CrossOrigin
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<ShippingResponse>> getAllShippings() {
        Collection<ShippingResponse> shippingResponseCollection = new ArrayList<>();
        for (ShippingDto shippingDto : this.shippingService.getAll()) {
            ShippingResponse cartResponse = this.mapper.map(shippingDto, ShippingResponse.class);
            shippingResponseCollection.add(cartResponse);
        }
        return new ResponseEntity<>(shippingResponseCollection, HttpStatus.OK);
    }

    @GetMapping(value = "/{key}") //get
    public ResponseEntity<ShippingResponse> getAShippingByKey(@PathVariable long key) throws NoSuchElementException {
        ShippingResponse shippingResponse;
        try {
            shippingResponse = this.mapper.map(this.shippingService.getAShippingInfosByKey(key), ShippingResponse.class);
        } catch (NoSuchElementException e) {
            log.error("Le cart n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(shippingResponse, HttpStatus.OK);
    }

    @PostMapping
    public void createShippingInfos(@RequestBody ShippingRequest shippingRequest) {
        this.shippingService.createShippingInfos(this.mapper.map(shippingRequest, ShippingDto.class));
    }
}
