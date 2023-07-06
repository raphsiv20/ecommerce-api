package com.ecommerce.api.models.dtos;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CartItemsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    private CartItemsEmbedabble id;

    private CartEntity cart;

    private ProductEntity product;

    private int quantity;


}
