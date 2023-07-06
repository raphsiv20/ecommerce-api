package com.ecommerce.api.models.requests;

import com.ecommerce.api.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private UserEntity user;

    private Double totalPrice;

    private int totalQuantity;


}
