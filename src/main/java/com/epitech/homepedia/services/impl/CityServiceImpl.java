package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.CityDTO;
import com.epitech.homepedia.mapper.CityDTOMapper;
import com.epitech.homepedia.model.City;
import com.epitech.homepedia.repository.CityRepository;
import com.epitech.homepedia.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository repository;
    @Autowired
    private CityDTOMapper mapper;

    @Override
    public List<CityDTO> getAllCity() {
        return repository.findAll().stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public CityDTO getCityDTO(Long id) {
        return mapper.convertToDTO(getCity(id));
    }

    private City getCity(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("City with id : {} don't exist", id);
            return new RuntimeException("City with id : " + id + " don't exist");
        });
    }

    @Override
    public void addCity(CityDTO city) {
        repository.save(mapper.apply(city));
    }

    @Override
    public void updateCityName(Long id, CityDTO city) {
        var cityData = getCity(id);
        cityData.setName(city.getName());
        repository.save(cityData);
    }

    @Override
    public void deleteCity(Long id) {
        repository.delete(getCity(id));
    }
}