package com.ecommerce.api.repositories;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.ecommerce.api.models.entities.CartItemsEntity;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartItemsRepository extends CrudRepository<CartItemsEntity, Long> {
    @Query("SELECT ci FROM  CartItemsEntity ci ORDER BY ci.cart.cartId ASC")
    Collection<CartItemsEntity> findAll();

    @Transactional
    void deleteById(CartItemsEmbedabble id);
}
