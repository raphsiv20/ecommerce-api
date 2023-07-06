package com.ecommerce.api.models.dtos;

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
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    private Long productId;

    private String productName;

    private String productDescription;

    private Double productPrice;

    private String productImage;

    private String productCategory;

    private Integer stock;
}
