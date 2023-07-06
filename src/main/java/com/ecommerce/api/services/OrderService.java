package com.ecommerce.api.services;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.OrderDto;

import java.util.Collection;

public interface OrderService {
    Collection<OrderDto> getAll();
    OrderDto getAnOrderById(long orderId);
    Collection<OrderDto> getAllOrdersByUser(long userId);
    void updateOrderShippingStatus(long orderId, String orderShippingStatus);

    void updateOrderPaymentStatus(long orderId, String orderPaymentStatus);

    OrderDto create(OrderDto orderDto);

    void delete(Long orderId);
}
