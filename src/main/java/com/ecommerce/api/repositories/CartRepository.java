package com.ecommerce.api.repositories;

import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
    @Query("SELECT c FROM  CartEntity c ORDER BY c.cartId ASC")
    Collection<CartEntity> findAll();
}
