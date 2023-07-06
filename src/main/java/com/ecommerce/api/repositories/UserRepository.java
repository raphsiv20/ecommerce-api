package com.ecommerce.api.repositories;

import com.ecommerce.api.models.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("SELECT u FROM  UserEntity u ORDER BY u.userId ASC")
    Collection<UserEntity> findAll();

}


