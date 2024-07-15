package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.model.Departement;
import com.epitech.homepedia.repository.DepartementRepository;
import com.epitech.homepedia.services.DepartmentService;
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
public class DepartementServiceImpl implements DepartmentService {
    private final DepartementRepository departementRepository;

    @SneakyThrows
    @Override
    public void addDepartement(FeatureCollection featureCollection) {
        featureCollection.getFeatures().stream()
                .filter(feature -> feature.getGeometry() instanceof Polygon || feature.getGeometry() instanceof MultiPolygon) // Filtrer les polygones et les multipolygones
                .flatMap(feature -> convertToRegions(feature).stream()) // Convertir chaque feature en une ou plusieurs régions
                .collect(Collectors.toList()); // Collecter dans une liste
    }

    @Override
    public DepartmentDTO getDepartement(String regionName) {
        Departement region = departementRepository.findByNom(regionName).orElse(null);
        Polygon geoJsonPolygon = new Polygon(
                Arrays.stream(region.getGeometry().getCoordinates())
                        .map(coord -> new LngLatAlt(coord.x, coord.y))
                        .collect(Collectors.toList())
        );
        DepartmentDTO regionDTO = new DepartmentDTO();
        regionDTO.setName(regionName);
        regionDTO.setPolygon(geoJsonPolygon);
        regionDTO.setCode(Integer.valueOf(region.getCode()));
        regionDTO.setPriceMaison(region.getPriceMaison());
        regionDTO.setPriceAppart(region.getPriceAppart());
        return regionDTO;

    }

    @Override
    public void addPrice(BigDecimal price, String codeReg) {
        var regionList = departementRepository.findAllByCode(codeReg);
        departementRepository.deleteAll(regionList);
        for (Departement departement : regionList) {
            departement.setPrice(price);
        }
        departementRepository.saveAll(regionList);
    }

    @Override
    public void addPriceMaison(BigDecimal price, String codeReg) {
        var regionList = departementRepository.findAllByCode(codeReg);
        departementRepository.deleteAll(regionList);
        for (Departement departement : regionList) {
            departement.setPriceMaison(price);
        }
        departementRepository.saveAll(regionList);
    }

    @Override
    public void addPriceAppart(BigDecimal price, String codeReg) {
        var regionList = departementRepository.findAllByCode(codeReg);
        departementRepository.deleteAll(regionList);
        for (Departement departement : regionList) {
            departement.setPriceAppart(price);
        }
        departementRepository.saveAll(regionList);
    }

    private List<Departement> convertToRegions(Feature feature) {
        if (feature.getGeometry() instanceof Polygon) {
            try {
                return List.of(convertToRegion(feature, (Polygon) feature.getGeometry()));
            } catch (IllegalArgumentException e) {
                // Ignorer les polygones non fermés
                return List.of();
            }
        } else if (feature.getGeometry() instanceof MultiPolygon multiPolygon) {
            return multiPolygon.getCoordinates().stream()
                    .map(coordinates -> {
                        // Conversion en List<LngLatAlt>
                        List<LngLatAlt> polygonCoords = coordinates.get(0).stream()
                                .map(position -> new LngLatAlt(position.getLongitude(), position.getLatitude()))
                                .collect(Collectors.toList());

                        return new Polygon(polygonCoords); // Utilise le constructeur Polygon(List<LngLatAlt>)
                    })
                    .flatMap(geoJsonPolygon -> {
                        try {
                            // Fermer le polygone (dupliquer la première coordonnée à la fin)
                            List<LngLatAlt> coords = new ArrayList<>(geoJsonPolygon.getCoordinates().get(0));
                            coords.add(coords.get(0));

                            org.locationtech.jts.geom.Polygon jtsPolygon = new GeometryFactory().createPolygon(
                                    coords.stream()
                                            .map(coord -> new Coordinate(coord.getLongitude(), coord.getLatitude()))
                                            .toArray(Coordinate[]::new)
                            );
                            return Stream.of(convertToRegionFromPolygon(feature, jtsPolygon));
                        } catch (IllegalArgumentException e) {
                            // Ignorer les polygones non fermés
                            return Stream.empty();
                        }
                    })
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("La géométrie doit être un polygone ou un multipolygone");
        }
    }


    private Departement convertToRegionFromPolygon(Feature feature, org.locationtech.jts.geom.Polygon geoJsonPolygon) {
        // Création de l'entité Region
        Departement region = new Departement();
        region.setCode(feature.getProperty("code").toString());
        region.setNom(feature.getProperty("nom").toString());
        region.setGeometry(geoJsonPolygon);

        return departementRepository.save(region);
    }

    private Departement convertToRegion(Feature feature, Polygon geoJsonPolygon) {
        // Conversion de Polygon GeoJSON en Polygon JTS
        org.locationtech.jts.geom.Polygon jtsPolygon = new GeometryFactory().createPolygon(
                geoJsonPolygon.getCoordinates().stream()
                        .flatMap(List::stream) // Aplatir la liste de listes de coordonnées
                        .map(coord -> new Coordinate(coord.getLongitude(), coord.getLatitude()))
                        .toArray(Coordinate[]::new)
        );

        // Création de l'entité Region
        Departement region = new Departement();
        region.setCode(feature.getProperty("code").toString());
        region.setNom(feature.getProperty("nom").toString());
        region.setGeometry(jtsPolygon);

        return departementRepository.save(region);
    }
}