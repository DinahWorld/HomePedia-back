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
    public void addCommune(FeatureCollection featureCollection) {
        featureCollection.getFeatures().stream()
                .filter(feature -> feature.getGeometry() instanceof Polygon || feature.getGeometry() instanceof MultiPolygon) // Filtrer les polygones et les multipolygones
                .flatMap(feature -> convertToRegions(feature).stream()) // Convertir chaque feature en une ou plusieurs régions
                .collect(Collectors.toList()); // Collecter dans une liste
    }

    @Override
    public RegionDTO getCommune(String regionName) {
        var departementList = regionRepository.findAllByNom(regionName);
        List<List<List<LngLatAlt>>> list = new ArrayList<>();
        for (Communes departement : departementList) {
            Polygon geoJsonPolygon = new Polygon(
                    Arrays.stream(departement.getGeometry().getCoordinates())
                            .map(coord -> new LngLatAlt(coord.x, coord.y))
                            .collect(Collectors.toList())
            );
            list.add(geoJsonPolygon.getCoordinates());
        }

        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setName(regionName);
        regionDTO.setPolygon(list);
        regionDTO.setCode(Integer.valueOf(departementList.get(0).getCode()));
        regionDTO.setPriceMaison(departementList.get(0).getPriceMaison());
        regionDTO.setPriceAppart(departementList.get(0).getPriceAppart());
        return regionDTO;

    }

    @Override
    public void addPriceMaison(BigDecimal price, String codeReg) {
        var regionList = regionRepository.findAllByCode(codeReg);
        regionRepository.deleteAll(regionList);
        for (Communes region : regionList) {
            region.setPriceMaison(price);
        }
        regionRepository.saveAll(regionList);
    }

    @Override
    public void addPriceAppart(BigDecimal price, String codeReg) {
        var regionList = regionRepository.findAllByCode(codeReg);
        regionRepository.deleteAll(regionList);
        for (Communes region : regionList) {
            region.setPriceAppart(price);
        }
        regionRepository.saveAll(regionList);
    }

    private List<Communes> convertToRegions(Feature feature) {
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


    private Communes convertToRegionFromPolygon(Feature feature, org.locationtech.jts.geom.Polygon geoJsonPolygon) {
        // Création de l'entité Region
        Communes region = new Communes();
        region.setCode(feature.getProperty("code").toString());
        region.setNom(feature.getProperty("nom").toString());
        region.setGeometry(geoJsonPolygon);

        return regionRepository.save(region);
    }

    private Communes convertToRegion(Feature feature, Polygon geoJsonPolygon) {
        // Conversion de Polygon GeoJSON en Polygon JTS
        org.locationtech.jts.geom.Polygon jtsPolygon = new GeometryFactory().createPolygon(
                geoJsonPolygon.getCoordinates().stream()
                        .flatMap(List::stream) // Aplatir la liste de listes de coordonnées
                        .map(coord -> new Coordinate(coord.getLongitude(), coord.getLatitude()))
                        .toArray(Coordinate[]::new)
        );

        // Création de l'entité Region
        Communes region = new Communes();
        region.setCode(feature.getProperty("code").toString());
        region.setNom(feature.getProperty("nom").toString());
        region.setGeometry(jtsPolygon);

        return regionRepository.save(region);
    }
}