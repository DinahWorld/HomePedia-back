package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.services.CommunesService;
import lombok.RequiredArgsConstructor;
import org.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/commune")
@RequiredArgsConstructor
public class CommunesController {
    private final CommunesService regionService;

    @PostMapping
    public ResponseEntity<String> createRegion(@RequestBody FeatureCollection featureCollection) {
        regionService.addCommune(featureCollection);
        return ResponseEntity.ok("Region created");
    }

    @GetMapping
    public ResponseEntity<RegionDTO> getRegion(@RequestParam String region) {
        return ResponseEntity.ok(regionService.getCommune(region));
    }

    @PutMapping("/appart")
    public ResponseEntity<String> addPrice(@RequestParam BigDecimal price, @RequestParam String codeReg) {
        regionService.addPriceAppart(price, codeReg);
        return ResponseEntity.ok("Price added");
    }

    @PutMapping("/maison")
    public ResponseEntity<String> addPriceM(@RequestParam BigDecimal price, @RequestParam String codeReg) {
        regionService.addPriceMaison(price, codeReg);
        return ResponseEntity.ok("Price added");
    }


}