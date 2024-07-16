package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.LngLatAlt;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionDTO {
    private String name;
    private List<List<List<LngLatAlt>>> polygon;
    private Integer code;
    private BigDecimal priceMaison;
    private BigDecimal priceAppart;
}