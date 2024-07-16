package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.Feature;
import org.geojson.LngLatAlt;
import org.geojson.Polygon;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String type;
    private List<Feature> features;
}