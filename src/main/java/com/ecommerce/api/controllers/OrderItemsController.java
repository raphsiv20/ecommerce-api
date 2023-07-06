package com.ecommerce.api.controllers;

import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.entities.ProductEntity;
import com.ecommerce.api.models.requests.OrderItemsRequest;
import com.ecommerce.api.models.responses.OrderItemsResponse;
import com.ecommerce.api.models.responses.OrderItemsResponse;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.OrderItemsService;
import com.ecommerce.api.services.OrderItemsService;
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
@RequestMapping("/v1/orderItems")
@CrossOrigin
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;


    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<OrderItemsResponse>> getAllOrderItems() {
        Collection<OrderItemsResponse> orderItemsResponseCollection = new ArrayList<>();
        for (OrderItemsDto orderItemsDto : this.orderItemsService.getAll()) {
            OrderItemsResponse orderItemsResponse = this.mapper.map(orderItemsDto, OrderItemsResponse.class);
            orderItemsResponseCollection.add(orderItemsResponse);
        }
        return new ResponseEntity<>(orderItemsResponseCollection, HttpStatus.OK);
    }


    @GetMapping(value = "/{orderId}") //id=OrderId
    public ResponseEntity<Collection<OrderItemsResponse>> getAnOrderItems(@PathVariable long orderId) throws NoSuchElementException {
        Collection<OrderItemsResponse> orderItemsResponseCollection = new ArrayList<>();
        try {
            for (OrderItemsDto orderItemsDto: this.orderItemsService.getAllProductsOnAnOrder(orderId)) {
                orderItemsResponseCollection.add(this.mapper.map(orderItemsDto, OrderItemsResponse.class));
            }
        } catch (NoSuchElementException e) {
            log.error("La commande n'existe pas");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderItemsResponseCollection, HttpStatus.OK);
    }

    @PostMapping //pour lister les produits d'une commande
    public void createOrderItems(@RequestBody OrderItemsRequest OrderItemsRequest) {
        OrderItemsDto orderItemsDto = this.mapper.map(OrderItemsRequest, OrderItemsDto.class);
        this.orderItemsService.create(orderItemsDto);
    }


    @DeleteMapping("/{orderId}/{productId}") //appeler la methode update negative pour mettre à jour les infos du panier
    public void deleteOrderItems(@PathVariable long orderId, @PathVariable long productId) {
        for (OrderItemsDto orderItemsDto : this.orderItemsService.getAll()) {
            if (orderItemsDto.getProduct().getProductId().equals(productId) && orderItemsDto.getOrder().getOrderId().equals(orderId)) {
                this.orderItemsService.delete(orderId, productId);
            }
        }
    }
}
