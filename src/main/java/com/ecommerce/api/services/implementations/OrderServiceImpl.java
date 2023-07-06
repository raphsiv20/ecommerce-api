package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.OrderDto;
import com.ecommerce.api.models.entities.OrderEntity;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.repositories.OrderRepository;
import com.ecommerce.api.repositories.UserRepository;
import com.ecommerce.api.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Collection<OrderDto> getAll() {
        Collection<OrderDto> collectionOrderDto = new ArrayList<OrderDto>();
        for (OrderEntity orderEntity : this.orderRepository.findAll()) {
            collectionOrderDto.add(this.mapper.map(orderEntity, OrderDto.class));
        }
        return collectionOrderDto;
    }

    @Override
    public OrderDto getAnOrderById(long orderId) {
        return this.mapper.map(this.orderRepository.findById(orderId).get(), OrderDto.class);
    }

    @Override
    public Collection<OrderDto> getAllOrdersByUser(long userId) {
        Collection<OrderDto> collectionOrderDto = new ArrayList<OrderDto>();
        for (OrderEntity orderEntity : this.orderRepository.findAll()) {
            if (orderEntity.getUser().getUserId().equals(userId)) {
                collectionOrderDto.add(this.mapper.map(orderEntity, OrderDto.class));
            }
        }
        return collectionOrderDto;
    }

    @Override
    public void updateOrderShippingStatus(long orderId, String orderShippingStatus) {
        OrderEntity orderEntity = this.orderRepository.findById(orderId).get();
        orderEntity.setOrderShippingStatus(orderShippingStatus);
        this.orderRepository.save(orderEntity);
    }

    @Override
    public void updateOrderPaymentStatus(long orderId, String orderPaymentStatus) {
        OrderEntity orderEntity = this.orderRepository.findById(orderId).get();
        orderEntity.setOrderPaymentStatus(orderPaymentStatus);
        this.orderRepository.save(orderEntity);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
       OrderEntity orderEntity =  this.orderRepository.save(this.mapper.map(orderDto, OrderEntity.class));
       return this.mapper.map(this.orderRepository.findById(orderEntity.getOrderId()), OrderDto.class);
    }

    @Override
    public void delete(Long orderId) {
        this.orderRepository.deleteById(orderId);
    }
}
