package com.ecommerce.api.models.dtos;

import com.ecommerce.api.models.entities.ProductEntity;
import com.ecommerce.api.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    private Long reviewId;

    private ProductEntity product;

    private UserEntity user;

    private String reviewText;

    private String reviewDate;
}
