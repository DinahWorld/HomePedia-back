package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.RegionDTO;
import org.geojson.FeatureCollection;

import java.math.BigDecimal;

public interface RegionService {
    void addRegion(FeatureCollection featureCollection);

    RegionDTO getRegion(String region);

    void addPrice(BigDecimal price, String codeReg);

    void addPriceAppart(BigDecimal price, String codeReg);

    void addPriceMaison(BigDecimal price, String codeReg);
}