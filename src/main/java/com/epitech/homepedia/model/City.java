package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "city")
@Data
public class City {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    private Integer postalCode;

    private String description;

    @JoinColumn(name = "average_price_per_sqm")
    private BigDecimal averagePricePerSqm;

    private BigDecimal population;

    private BigDecimal rating;
}