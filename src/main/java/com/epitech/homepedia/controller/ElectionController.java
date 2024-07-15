package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.ElectionDTO;
import com.epitech.homepedia.services.ElectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/election")
@RequiredArgsConstructor
public class ElectionController {
    private final ElectionService electionService;

    @PostMapping
    public ResponseEntity<String> createCity(@RequestBody ElectionDTO electionDTO) {
        electionService.addElection(electionDTO);
        return ResponseEntity.ok("Election created");
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Long>> createCity(@RequestParam String city) {
        return ResponseEntity.ok(electionService.calculateElection(city));
    }

}