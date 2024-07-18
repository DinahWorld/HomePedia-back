package com.epitech.homepedia.dto;

import com.epitech.homepedia.model.Communes;
import com.epitech.homepedia.model.Departements;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BigDataDTO {
    private List<Departements> departements;
    private List<Communes> communes;
}