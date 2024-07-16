package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.DepartmentDTO;
import org.geojson.FeatureCollection;

import java.math.BigDecimal;

public interface DepartmentService {
    void addDepartement(FeatureCollection featureCollection);

    DepartmentDTO getDepartement(String region);

    void addPriceMaison(BigDecimal price, String codeReg);

    void addPriceAppart(BigDecimal price, String codeReg);
}