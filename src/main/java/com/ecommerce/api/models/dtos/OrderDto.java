package com.ecommerce.api.models.dtos;


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
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    private Long orderId;

    private UserEntity user;

    private Double orderPrice;

    private int orderQuantity;

    private String orderDate;

    private String orderShippingStatus;

    private String orderPaymentStatus;


}
