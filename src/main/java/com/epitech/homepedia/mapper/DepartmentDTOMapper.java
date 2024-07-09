package com.epitech.homepedia.mapper;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.model.Department;
import com.epitech.homepedia.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DepartmentDTOMapper implements Function<DepartmentDTO, Department> {
    private final RegionService regionService;

    @Override
    public Department apply(DepartmentDTO dto) {
        return Department.builder()
                .name(dto.getName())
                .region(regionService.getRegionByName(dto.getRegion()))
                .description(dto.getDescription())
                .averagePricePerSqm(dto.getAveragePricePerSqm())
                .population(dto.getPopulation())
                .build();
    }

    public DepartmentDTO convertToDTO(Department department) {
        return DepartmentDTO.builder()
                .name(department.getName())
                .region(Objects.isNull(department.getRegion()) ? "" : department.getRegion().getName())
                .description(department.getDescription())
                .averagePricePerSqm(department.getAveragePricePerSqm())
                .population(department.getPopulation())
                .build();
    }

}