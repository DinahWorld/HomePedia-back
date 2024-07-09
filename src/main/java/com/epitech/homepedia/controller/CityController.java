package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.CityDTO;
import com.epitech.homepedia.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity<String> createCity(@RequestBody CityDTO cityDTO) {
        cityService.addCity(cityDTO);
        return ResponseEntity.ok("City created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        cityService.updateCityName(id, cityDTO);
        return ResponseEntity.ok("City updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok("City deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCity());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCity(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCityDTO(id));
    }
}