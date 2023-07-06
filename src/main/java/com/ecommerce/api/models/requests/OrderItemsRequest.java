package com.ecommerce.api.models.requests;

import com.ecommerce.api.models.entities.CartEntity;
import com.ecommerce.api.models.entities.OrderEntity;
import com.ecommerce.api.models.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsRequest {

    OrderEntity order;

    ProductEntity product;

    int quantity;


}
