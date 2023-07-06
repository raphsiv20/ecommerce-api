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
public class OrderResponse implements Serializable {

    private long orderId;

    private UserEntity user;

    private Double orderPrice;

    private int orderQuantity;

    private String orderDate;

    private String orderShippingStatus;

    private String orderPaymentStatus;

}

