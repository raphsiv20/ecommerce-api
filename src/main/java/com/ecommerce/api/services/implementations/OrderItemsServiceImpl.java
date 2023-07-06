package com.ecommerce.api.services.implementations;

import com.ecommerce.api.models.dtos.CartDto;
import com.ecommerce.api.models.dtos.CartItemsDto;
import com.ecommerce.api.models.dtos.OrderDto;
import com.ecommerce.api.models.dtos.OrderItemsDto;
import com.ecommerce.api.models.entities.CartItemsEntity;
import com.ecommerce.api.models.entities.OrderEntity;
import com.ecommerce.api.models.entities.OrderItemsEntity;
import com.ecommerce.api.repositories.CartItemsRepository;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.repositories.OrderItemsRepository;
import com.ecommerce.api.repositories.OrderRepository;
import com.ecommerce.api.services.CartItemsService;
import com.ecommerce.api.services.CartService;
import com.ecommerce.api.services.OrderItemsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Collection<OrderItemsDto> getAll() {
        Collection<OrderItemsDto> collectionOrderItemsDto = new ArrayList<OrderItemsDto>();
        for (OrderItemsEntity orderItemsEntity : this.orderItemsRepository.findAll()) {
            collectionOrderItemsDto.add(this.mapper.map(orderItemsEntity, OrderItemsDto.class));
        }
        return collectionOrderItemsDto;
    }

    @Override
    public Collection<OrderItemsDto> getAllProductsOnAnOrder(long orderId) {
        Collection<OrderItemsDto> collectionOrderItemsDto = new ArrayList<OrderItemsDto>();
        for (OrderItemsEntity orderItemsEntity : this.orderItemsRepository.findAll()) {
            if (orderItemsEntity.getOrder().getOrderId().equals(orderId)) {
                collectionOrderItemsDto.add(this.mapper.map(orderItemsEntity, OrderItemsDto.class));
            }
        }
        return collectionOrderItemsDto;
    }

    @Override
    public void create(OrderItemsDto orderItemsDto) {
        this.orderItemsRepository.save(this.mapper.map(orderItemsDto, OrderItemsEntity.class));
    }

    @Override
    public void delete(Long orderId, Long productId) {
        for (OrderItemsEntity orderItemsEntity: this.orderItemsRepository.findAll()) {
            if (orderItemsEntity.getProduct().getProductId().equals(productId) && orderItemsEntity.getOrder().getOrderId().equals(orderId)) {
                this.orderItemsRepository.deleteById(orderItemsEntity.getId());
            }
        }
    }
}
