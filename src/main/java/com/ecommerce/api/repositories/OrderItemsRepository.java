package com.ecommerce.api.repositories;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.ecommerce.api.models.embeddable.OrderItemsEmbedabble;
import com.ecommerce.api.models.entities.CartItemsEntity;
import com.ecommerce.api.models.entities.OrderItemsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrderItemsRepository extends CrudRepository<OrderItemsEntity, Long> {
    @Query("SELECT oi FROM  OrderItemsEntity oi ORDER BY oi.order.orderId ASC")
    Collection<OrderItemsEntity> findAll();

    @Transactional
    void deleteById(OrderItemsEmbedabble id);
}
