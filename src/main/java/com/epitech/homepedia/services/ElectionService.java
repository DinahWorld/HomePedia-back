package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.ElectionDTO;

import java.util.Map;

public interface ElectionService {
    void addElection(ElectionDTO electionDTO);

    Map<String, Long> calculateElection(String city);
}