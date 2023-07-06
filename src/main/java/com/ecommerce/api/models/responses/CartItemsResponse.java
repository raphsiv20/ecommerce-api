package com.ecommerce.api.models.responses;

import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsResponse implements Serializable {


    CartEntity cart;

    ProductEntity product;

    Long quantity;


}