package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private String name;
    private String department;
    private Integer postalCode;
    private String description;
    private BigDecimal averagePricePerSqm;
    private BigDecimal population;
    private BigDecimal rating;
}