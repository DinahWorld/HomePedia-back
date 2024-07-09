package com.epitech.homepedia.mapper;

import com.epitech.homepedia.dto.CityDTO;
import com.epitech.homepedia.model.City;
import com.epitech.homepedia.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CityDTOMapper implements Function<CityDTO, City> {
    private final DepartmentService departmentService;

    @Override
    public City apply(CityDTO dto) {
        return City.builder()
                .name(dto.getName())
                .department(departmentService.getDepartementByName(dto.getName()))
                .postalCode(dto.getPostalCode())
                .description(dto.getDescription())
                .averagePricePerSqm(dto.getAveragePricePerSqm())
                .population(dto.getPopulation())
                .rating(dto.getRating())
                .build();

    }

    public CityDTO convertToDTO(City city) {
        return CityDTO.builder()
                .name(city.getName())
                .department(city.getDepartment().getName())
                .postalCode(city.getPostalCode())
                .description(city.getDescription())
                .averagePricePerSqm(city.getAveragePricePerSqm())
                .population(city.getPopulation())
                .rating(city.getRating())
                .build();
    }


}