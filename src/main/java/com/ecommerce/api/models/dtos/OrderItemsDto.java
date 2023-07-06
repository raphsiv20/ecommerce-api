package com.ecommerce.api.models.dtos;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.ecommerce.api.models.embeddable.OrderItemsEmbedabble;
import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.OrderEntity;
import com.ecommerce.api.models.entities.ProductEntity;
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
public class OrderItemsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    private OrderItemsEmbedabble id;

    private OrderEntity order;

    private ProductEntity product;

    private int quantity;


}
