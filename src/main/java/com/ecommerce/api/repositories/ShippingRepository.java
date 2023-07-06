package com.ecommerce.api.repositories;

import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.ShippingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ShippingRepository extends CrudRepository<ShippingEntity, Long> {
    @Query("SELECT s FROM  ShippingEntity s ORDER BY s.key ASC")
    Collection<ShippingEntity> findAll();
}
