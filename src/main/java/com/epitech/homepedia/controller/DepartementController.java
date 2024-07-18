package com.epitech.homepedia.controller;

import com.epitech.homepedia.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/departements")
@RequiredArgsConstructor
public class DepartementController {
    private final DepartmentService departementService;

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestParam BigDecimal price,
                                                   @RequestParam String code) {
        departementService.addDepartment(price, code);
        return ResponseEntity.ok("Departement created");
    }


}