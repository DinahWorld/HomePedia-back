package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.BigDataDTO;
import com.epitech.homepedia.model.Communes;
import com.epitech.homepedia.model.Departements;
import com.epitech.homepedia.services.CommunesService;
import com.epitech.homepedia.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private DepartmentService departmentService;

    @Mock
    private CommunesService communeService;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    void testGetPrice() {
        // define mock data
        List<Departements> departementsList = Collections.singletonList(createMockDepartement());
        List<Communes> communesList = Collections.singletonList(createMockCommune());

        // mock behavior of collaborators
        Mockito.when(communeService.getAll()).thenReturn(communesList);
        Mockito.when(departmentService.getAll()).thenReturn(departementsList);

        // call the method under test
        BigDataDTO bigDataDTO = priceService.getPrice();

        // verify interactions with collaborators
        Mockito.verify(communeService).getAll();
        Mockito.verify(departmentService).getAll();

        // assert content of BigDataDTO
        assertEquals(departementsList, bigDataDTO.getDepartements());
        assertEquals(communesList, bigDataDTO.getCommunes());
    }

    private Departements createMockDepartement() {
        Departements departement = new Departements();
        departement.setCode("75");
        departement.setPrice(1000000);
        return departement;
    }

    private Communes createMockCommune() {
        Communes communes = new Communes();
        communes.setCode("75002");
        communes.setPrice(200000);
        return communes;
    }
}