package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.BigDataDTO;
import com.epitech.homepedia.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    public ResponseEntity<BigDataDTO> getPrice() {
        return ResponseEntity.ok(priceService.getPrice());
    }

}