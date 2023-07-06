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
public class ReviewEmbedabble implements Serializable {

    @Column(name = "userId")
    Long userId;

    @Column(name = "productId")
    Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEmbedabble cartItemsEmbedabble = (ReviewEmbedabble) o;
        return userId.equals(cartItemsEmbedabble.userId) &&
                productId.equals(cartItemsEmbedabble.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }
}
