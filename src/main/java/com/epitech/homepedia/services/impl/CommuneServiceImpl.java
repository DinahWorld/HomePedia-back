package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.model.Communes;
import com.epitech.homepedia.repository.CommunesRepository;
import com.epitech.homepedia.services.CommunesService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.geojson.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommuneServiceImpl implements CommunesService {
    private final CommunesRepository regionRepository;

    @SneakyThrows
    @Override
    public void addCommune(BigDecimal price, BigDecimal code) {
        var communes = new Communes();
        communes.setCode(code);
        communes.setPrice(price.intValue());
        regionRepository.save(communes);
    }

    @Override
    public RegionDTO getCommune(String regionName) {
        return null;

    }

    @Override
    public void addPriceMaison(BigDecimal price, String codeReg) {
    }

    @Override
    public void addPriceAppart(BigDecimal price, String codeReg) {
    }

}