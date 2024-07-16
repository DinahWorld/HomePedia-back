package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.DepartementColorDTO;
import com.epitech.homepedia.dto.DepartmentDTO;
import org.geojson.FeatureCollection;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentService {
    void addDepartement(FeatureCollection featureCollection);

    DepartmentDTO getDepartement(String region);

    void addPriceMaison(BigDecimal price, String codeReg);

    List<DepartementColorDTO> getPriceColor();

    void addPriceAppart(BigDecimal price, String codeReg);
}