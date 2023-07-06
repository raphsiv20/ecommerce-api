package com.ecommerce.api.models.responses;

import com.ecommerce.api.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse implements Serializable {

    private Long cartId;

    private UserEntity user;

    private Double totalPrice;

    private int totalQuantity;


}

