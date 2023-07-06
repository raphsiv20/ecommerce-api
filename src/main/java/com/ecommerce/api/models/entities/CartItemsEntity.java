package com.ecommerce.api.models.entities;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cartItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @EmbeddedId
    private CartItemsEmbedabble id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private CartEntity cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @JsonIgnore
    private ProductEntity product;


    private int quantity;

}
