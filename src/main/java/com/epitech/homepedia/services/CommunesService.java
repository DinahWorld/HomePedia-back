package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.RegionDTO;
import org.geojson.FeatureCollection;

import java.math.BigDecimal;

public interface CommunesService {
    void addCommune(BigDecimal priceMaison,BigDecimal priceAppart, String name);

    RegionDTO getCommune(String region);

    void addPriceAppart(BigDecimal price, String codeReg);

    void addPriceMaison(BigDecimal price, String codeReg);
}