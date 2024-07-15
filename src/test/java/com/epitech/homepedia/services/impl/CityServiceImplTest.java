package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.CityDTO;
import com.epitech.homepedia.mapper.CityDTOMapper;
import com.epitech.homepedia.model.City;
import com.epitech.homepedia.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    @Mock
    private CityRepository repository;

    @Mock
    private CityDTOMapper mapper;

    @InjectMocks
    private CityServiceImpl service;

    private City city;
    private CityDTO cityDTO;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Paris");

        cityDTO = new CityDTO();
        cityDTO.setName("Paris");
    }

    @Test
    void testGetAllCity() {
        when(repository.findAll()).thenReturn(List.of(city));
        when(mapper.convertToDTO(city)).thenReturn(cityDTO);

        List<CityDTO> result = service.getAllCity();

        assertEquals(1, result.size());
        assertEquals(cityDTO, result.get(0));
    }

    @Test
    void testGetCityDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(city));
        when(mapper.convertToDTO(city)).thenReturn(cityDTO);

        CityDTO result = service.getCityDTO(1L);

        assertEquals(cityDTO, result);
    }

    @Test
    void testGetCityDTO_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getCityDTO(1L));
        assertEquals("City with id : 1 don't exist", exception.getMessage());
    }

    @Test
    void testAddCity() {
        when(mapper.apply(cityDTO)).thenReturn(city);

        service.addCity(cityDTO);

        verify(repository, times(1)).save(city);
    }

    @Test
    void testUpdateCityName() {
        when(repository.findById(1L)).thenReturn(Optional.of(city));

        service.updateCityName(1L, cityDTO);

        assertEquals("Paris", city.getName());
        verify(repository, times(1)).save(city);
    }

    @Test
    void testDeleteCity() {
        when(repository.findById(1L)).thenReturn(Optional.of(city));

        service.deleteCity(1L);

        verify(repository, times(1)).delete(city);
    }

    @Test
    void testDeleteCity_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.deleteCity(1L));
        assertEquals("City with id : 1 don't exist", exception.getMessage());
    }
}