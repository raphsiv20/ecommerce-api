package com.ecommerce.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingRequest {

    private String modeLivraison;

    private String livreur;

    private int livreDansXJours;

    private double prix;


}
