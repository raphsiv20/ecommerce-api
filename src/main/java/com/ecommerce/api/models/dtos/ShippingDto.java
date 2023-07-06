package com.ecommerce.api.models.dtos;


import com.ecommerce.api.models.entities.UserEntity;
import jakarta.persistence.Column;
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
public class ShippingDto {

    private Long key;

    private String modeLivraison;

    private String livreur;

    private int livreDansXJours;

    private double prix;



}
