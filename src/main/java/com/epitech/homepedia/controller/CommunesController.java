package com.epitech.homepedia.controller;

import com.epitech.homepedia.services.CommunesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/communes")
@RequiredArgsConstructor
public class CommunesController {
    private final CommunesService communesService;

    @PostMapping
    public ResponseEntity<String> createCommune(@RequestParam BigDecimal price,
                                                @RequestParam String code) {
        communesService.addCommune(price, code);
        return ResponseEntity.ok("Commune created");
    }


}