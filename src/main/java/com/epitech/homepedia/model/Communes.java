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
    private Integer code;
    private Integer price;
}