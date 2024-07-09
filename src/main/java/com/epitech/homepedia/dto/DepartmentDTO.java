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
public class DepartmentDTO {
    private String name;
    private String region;
    private String description;
    private BigDecimal averagePricePerSqm;
    private BigDecimal population;
}