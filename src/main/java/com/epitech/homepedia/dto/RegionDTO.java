package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.Polygon;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    private String name;
    private Polygon polygon;
    private Integer code;
    private BigDecimal priceMaison;
    private BigDecimal priceAppart;
}