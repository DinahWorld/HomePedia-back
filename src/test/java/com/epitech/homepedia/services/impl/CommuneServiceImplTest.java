package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.model.Communes;
import com.epitech.homepedia.repository.CommunesRepository;
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
class CommuneServiceImplTest {

    @Mock
    private CommunesRepository communesRepository;

    @InjectMocks
    private CommuneServiceImpl communesService;

    @Test
    void testAddCommune() {
        BigDecimal price = BigDecimal.valueOf(100000);
        String code = "75001";

        Communes communes = new Communes();
        communes.setCode(code);
        communes.setPrice(price.intValue());
        Mockito.when(communesRepository.save(communes)).thenReturn(communes);

        communesService.addCommune(price, code);

        Mockito.verify(communesRepository).save(communes);
    }

    @Test
    void testGetAll() {
        List<Communes> communesList = Collections.singletonList(createMockCommune());
        Mockito.when(communesRepository.findAll()).thenReturn(communesList);
        List<Communes> retrievedList = communesService.getAll();
        assertEquals(communesList, retrievedList);
    }

    private Communes createMockCommune() {
        Communes communes = new Communes();
        communes.setCode("75002");
        communes.setPrice(200000);
        return communes;
    }
}