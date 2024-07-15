package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.Polygon;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String name;
    private Polygon polygon;
    private Integer code;
    private BigDecimal priceMaison;
    private BigDecimal priceAppart;
}