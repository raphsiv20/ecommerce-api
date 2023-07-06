package com.ecommerce.api.repositories;

import com.ecommerce.api.models.embeddable.GradeEmbedabble;
import com.ecommerce.api.models.entities.GradeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GradeRepository extends CrudRepository<GradeEntity, Long> {
    @Query("SELECT g FROM  GradeEntity g ORDER BY g.product.productId ASC")
    Collection<GradeEntity> findAll();

    @Transactional
    void deleteById(GradeEmbedabble id);
}
