package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.model.Departements;
import com.epitech.homepedia.repository.DepartementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departmentService;

    @Test
    void testGetAll() {
        // define mock data
        List<Departements> departementsList = Collections.singletonList(createMockDepartement());

        // mock behavior of departementRepository
        Mockito.when(departementRepository.findAll()).thenReturn(departementsList);

        // call the method under test
        List<Departements> retrievedList = departmentService.getAll();

        // verify returned list
        assertEquals(departementsList, retrievedList);
    }

    private Departements createMockDepartement() {
        Departements departement = new Departements();
        departement.setCode("75");
        departement.setPrice(1000000);
        return departement;
    }

    @Test
    void testAddDepartment() {
        // define test data
        BigDecimal price = BigDecimal.valueOf(2000000);
        String code = "94";

        // mock behavior of departementRepository
        Departements department = new Departements();
        department.setCode(code);
        department.setPrice(price.intValue());
        Mockito.when(departementRepository.save(department)).thenReturn(department);

        // call the method under test
        departmentService.addDepartment(price, code);

        // verify interactions with departementRepository
        Mockito.verify(departementRepository).save(department);
    }
}