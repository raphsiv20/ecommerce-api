package com.ecommerce.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserEntity user;

    @Column(name="totalPrice")
    private Double totalPrice;

    @Column(name="totalQuantity")
    private int totalQuantity;

    //@OneToMany(mappedBy="cart")
    //private Set<CartItemsEntity> products;


}
