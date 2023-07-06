package com.ecommerce.api.models.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsEmbedabble implements Serializable {

    @Column(name = "orderId")
    Long orderId;

    @Column(name = "productId")
    Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemsEmbedabble cartItemsEmbedabble = (OrderItemsEmbedabble) o;
        return orderId.equals(cartItemsEmbedabble.orderId) &&
                productId.equals(cartItemsEmbedabble.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }
}
