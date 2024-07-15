package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.math.BigDecimal;

@Entity
@Table(name = "departements")
@Data
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;
    private BigDecimal price;
    private BigDecimal priceAppart;
    private BigDecimal priceMaison;

    @Column(columnDefinition = "geometry(Polygon, 4326)")
    private Polygon geometry;

}