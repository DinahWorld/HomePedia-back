package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.DepartementColorDTO;
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
import java.util.*;
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
        DepartmentDTO regionDTO = new DepartmentDTO();
        regionDTO.setType("FeatureCollection");
        regionDTO.setFeatures(new ArrayList<>());
        var code = List.of("95", "94", "93", "92", "91", "90", "89", "88",
                "87",
                "86",
                "85",
                "84",
                "83",
                "82",
                "81",
                "80",
                "79",
                "78",
                "77",
                "76",
                "75",
                "74",
                "73",
                "72",
                "70",
                "69",
                "68",
                "67",
                "65",
                "63",
                "60",
                "59",
                "58",
                "57",
                "56",
                "54",
                "52",
                "51",
                "50",
                "49",
                "48",
                "47",
                "46",
                "45",
                "44",
                "43",
                "42",
                "41",
                "40",
                "39",
                "38",
                "37",
                "36",
                "35",
                "34",
                "33",
                "32",
                "31",
                "30",
                "2B",
                "2A",
                "29",
                "28",
                "27",
                "25",
                "24",
                "23",
                "22",
                "21",
                "19",
                "18",
                "17",
                "16",
                "15",
                "14",
                "13",
                "12",
                "11",
                "10",
                "09",
                "08",
                "07",
                "06",
                "05",
                "04",
                "03",
                "01");

        for (String c : code) {
            var departementList = departementRepository.findAllByCode(c);
            GeoJsonObject list;
            MultiPolygon pl = new MultiPolygon();

            var feature = new Feature();
            feature.setId(String.valueOf(departementList.get(0).getId()));
            Map<String, Object> properties = new HashMap<>();
            properties.put("nom", regionName);
            properties.put("code", departementList.get(0).getCode());
            properties.put("price_maison", departementList.get(0).getPriceMaison());
            properties.put("price_appart", departementList.get(0).getPriceAppart());
            feature.setProperties(properties);


            for (Departement departement : departementList) {
                Polygon geoJsonPolygon = new Polygon(
                        Arrays.stream(departement.getGeometry().getCoordinates())
                                .map(coord -> new LngLatAlt(coord.x, coord.y))
                                .collect(Collectors.toList())
                );
                if (departementList.size() > 0) {
                    pl.add(geoJsonPolygon);
                } else {
                    feature.setGeometry(geoJsonPolygon);
                }
            }


            if (departementList.size() > 0) {
                feature.setGeometry(pl);
            }
            regionDTO.getFeatures().add(feature);
        }
        log.info("fini");
        return regionDTO;

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
    public List<DepartementColorDTO> getPriceColor() {
        var code = List.of("95", "94", "93", "92", "91", "90", "89", "88",
                "87",
                "86",
                "85",
                "84",
                "83",
                "82",
                "81",
                "80",
                "79",
                "78",
                "77",
                "76",
                "75",
                "74",
                "73",
                "72",
                "70",
                "69",
                "68",
                "67",
                "65",
                "63",
                "60",
                "59",
                "58",
                "57",
                "56",
                "54",
                "52",
                "51",
                "50",
                "49",
                "48",
                "47",
                "46",
                "45",
                "44",
                "43",
                "42",
                "41",
                "40",
                "39",
                "38",
                "37",
                "36",
                "35",
                "34",
                "33",
                "32",
                "31",
                "30",
                "2B",
                "2A",
                "29",
                "28",
                "27",
                "25",
                "24",
                "23",
                "22",
                "21",
                "19",
                "18",
                "17",
                "16",
                "15",
                "14",
                "13",
                "12",
                "11",
                "10",
                "09",
                "08",
                "07",
                "06",
                "05",
                "04",
                "03",
                "01");

        List<DepartementColorDTO> color = new ArrayList<>();
        for (String c : code) {
            var departementList = departementRepository.findAllByCode(c);
            color.add(new DepartementColorDTO(departementList.get(0).getNom(), departementList.get(0).getPriceMaison()));
        }

        return color;
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