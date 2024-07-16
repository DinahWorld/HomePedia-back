package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.LngLatAlt;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String name;
    private List<List<List<LngLatAlt>>> polygon;
    private Integer code;
    private BigDecimal priceMaison;
    private BigDecimal priceAppart;
}