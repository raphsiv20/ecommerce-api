package com.ecommerce.api.models.responses;

import com.ecommerce.api.models.entities.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponse {

    private Long key;

    private String modeLivraison;

    private String livreur;

    private int livreDansXJours;

    private double prix;

}

