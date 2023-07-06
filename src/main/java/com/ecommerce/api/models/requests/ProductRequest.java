package com.ecommerce.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String productName;

    private String productDescription;

    private Double productPrice;

    private String productImage;

    private String productCategory;

    private Integer stock;
}
