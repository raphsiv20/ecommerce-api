package com.ecommerce.api.models.entities;

import com.ecommerce.api.models.embeddable.CartItemsEmbedabble;
import com.ecommerce.api.models.embeddable.OrderItemsEmbedabble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "orderItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @EmbeddedId
    private OrderItemsEmbedabble id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private OrderEntity order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @JsonIgnore
    private ProductEntity product;


    private int quantity;

}
