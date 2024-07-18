package com.epitech.homepedia.services;

import com.epitech.homepedia.model.Communes;

import java.math.BigDecimal;
import java.util.List;

public interface CommunesService {
    void addCommune(BigDecimal price, String code);

    List<Communes> getAll();
}