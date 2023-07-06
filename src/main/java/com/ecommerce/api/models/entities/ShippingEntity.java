package com.ecommerce.api.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "shipping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6452632333830143700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @Column(name="modeLivraison")
    private String modeLivraison;

    @Column(name="livreur")
    private String livreur;

    @Column(name="livreDansXJours")
    private int livreDansXJours;

    @Column(name="prix")
    private double prix;




}
