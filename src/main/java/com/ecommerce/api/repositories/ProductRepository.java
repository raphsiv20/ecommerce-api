package com.ecommerce.api.repositories;

import com.ecommerce.api.models.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    @Query("SELECT p FROM  ProductEntity p ORDER BY p.productId ASC")
    Collection<ProductEntity> findAll();


}
