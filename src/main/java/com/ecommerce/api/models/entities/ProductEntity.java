package com.ecommerce.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productPrice")
    private Double productPrice;

    @Column(name = "productImage")
    private String productImage; //lien de l'image

    @Column(name = "productCategory")
    private String productCategory;

    @Column(name = "productStock")
    private Integer stock;


    //@OneToMany(mappedBy="product")
    //private Set<CartItemsEntity> carts;
}


