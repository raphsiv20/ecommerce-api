package com.ecommerce.api.models.entities;

import com.ecommerce.api.models.embeddable.ReviewEmbedabble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "rewiews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @EmbeddedId
    private ReviewEmbedabble id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @JsonIgnore
    private ProductEntity product;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserEntity user;

    @Column(name = "rewiewText")
    private String rewiewText;

    @Column(name = "rewiewDate")
    private String reviewData;

}


