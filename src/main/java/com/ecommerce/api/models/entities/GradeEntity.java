package com.ecommerce.api.models.entities;

import com.ecommerce.api.models.embeddable.GradeEmbedabble;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @EmbeddedId
    private GradeEmbedabble id;

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

    @Column(name = "gradeValue")
    private double gradeValue;

    @Column(name = "gradeDate")
    private String gradeDate;

}


