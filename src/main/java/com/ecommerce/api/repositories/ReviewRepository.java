package com.ecommerce.api.repositories;

import com.ecommerce.api.models.embeddable.ReviewEmbedabble;
import com.ecommerce.api.models.entities.ReviewEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM  ReviewEntity r ORDER BY r.product.productId ASC")
    Collection<ReviewEntity> findAll();

    @Transactional
    void deleteById(ReviewEmbedabble id);

}
