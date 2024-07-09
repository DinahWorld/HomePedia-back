package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> getAllCity();

    CityDTO getCityDTO(Long id);

    void addCity(CityDTO city);

    void updateCityName(Long id, CityDTO city);

    void deleteCity(Long id);
}