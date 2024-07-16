package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.math.BigDecimal;

@Entity
@Table(name = "communes")
@Data
public class Communes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    private BigDecimal priceMaison;
    private BigDecimal priceAppart;

    @Column(columnDefinition = "geometry(Polygon, 4326)")
    private Polygon geometry;

}