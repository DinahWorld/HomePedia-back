package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public ResponseEntity<String> createRegion(@RequestBody RegionDTO name) {
        regionService.addRegion(name);
        return ResponseEntity.ok("Region created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRegion(@PathVariable Long id,
                                               @RequestBody RegionDTO name) {
        regionService.updateRegionName(id, name);
        return ResponseEntity.ok("Region updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.ok("Region deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegion(@PathVariable Long id) {
        return ResponseEntity.ok(regionService.getRegionDTO(id));
    }

}