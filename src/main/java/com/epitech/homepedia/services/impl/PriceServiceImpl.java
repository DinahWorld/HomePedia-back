package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.BigDataDTO;
import com.epitech.homepedia.services.CommunesService;
import com.epitech.homepedia.services.DepartmentService;
import com.epitech.homepedia.services.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final DepartmentService departmentService;
    private final CommunesService communeService;

    @Override
    public BigDataDTO getPrice() {
        var communes = communeService.getAll();
        var departements = departmentService.getAll();

        BigDataDTO bigDataDTO = new BigDataDTO();
        bigDataDTO.setDepartements(departements);
        bigDataDTO.setCommunes(communes);
        return bigDataDTO;
    }


}