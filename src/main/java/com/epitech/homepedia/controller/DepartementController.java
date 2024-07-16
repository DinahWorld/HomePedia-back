package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/departement")
@RequiredArgsConstructor
public class DepartementController {
    private final DepartmentService departementService;

    @PostMapping
    public ResponseEntity<String> createDepartement(@RequestBody FeatureCollection featureCollection) {
        departementService.addDepartement(featureCollection);
        return ResponseEntity.ok("Departement created");
    }

    @GetMapping
    public ResponseEntity<DepartmentDTO> getDepartement(@RequestParam String departement) {
        return ResponseEntity.ok(departementService.getDepartement(departement));
    }

    @PutMapping("/appart")
    public ResponseEntity<String> addPriceA(@RequestParam BigDecimal price, @RequestParam String codeReg) {
        departementService.addPriceAppart(price, codeReg);
        return ResponseEntity.ok("Price added");
    }

    @PutMapping("/maison")
    public ResponseEntity<String> addPriceM(@RequestParam BigDecimal price, @RequestParam String codeReg) {
        departementService.addPriceMaison(price, codeReg);
        return ResponseEntity.ok("Price added");
    }


}