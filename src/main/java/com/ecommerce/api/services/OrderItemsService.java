package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.CartItemsDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;

import java.util.Collection;

public interface OrderItemsService {

    Collection<OrderItemsDto> getAll();

    Collection<OrderItemsDto> getAllProductsOnAnOrder(long orderId);

    void create(OrderItemsDto orderItemsDto);

    void delete(Long orderId, Long productId);
}
