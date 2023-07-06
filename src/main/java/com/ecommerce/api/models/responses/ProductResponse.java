package com.ecommerce.api.models.responses;

import jakarta.persistence.*;
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
public class ProductResponse implements Serializable {

    private Long productId;

    private String productName;

    private String productDescription;

    private Double productPrice;

    private String productImage; //lien de l'image

    private String productCategory;

    private Integer stock;
}