package com.ecommerce.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity user;

    @Column(name="orderPrice")
    private Double orderPrice;

    @Column(name="orderQuantity")
    private int orderQuantity;

    @Column(name="orderDate")
    private String orderDate;

    @Column(name="orderShippingStatus")
    private String orderShippingStatus;

    @Column(name="orderPaymentStatus")
    private String orderPaymentStatus;
}
