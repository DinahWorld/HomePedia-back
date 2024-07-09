package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "department")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(targetEntity = Region.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

    private String description;

    @JoinColumn(name = "average_price_per_sqm")
    private BigDecimal averagePricePerSqm;

    private BigDecimal population;
}