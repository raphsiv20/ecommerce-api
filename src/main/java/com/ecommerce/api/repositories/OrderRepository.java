package com.ecommerce.api.repositories;

import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT o FROM  OrderEntity o ORDER BY o.orderId DESC")
    Collection<OrderEntity> findAll();
}
