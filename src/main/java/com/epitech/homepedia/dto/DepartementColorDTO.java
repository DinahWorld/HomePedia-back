package com.epitech.homepedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.geojson.LngLatAlt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DepartementColorDTO {
    private String name;
    private BigInteger price;

    public DepartementColorDTO(String nom, BigDecimal priceMaison) {
        this.name = nom;
        this.price = priceMaison.toBigInteger();
    }
}