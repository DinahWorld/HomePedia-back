package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.model.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartement();

    DepartmentDTO getDepartementDTO(Long id);

    void addDepartement(DepartmentDTO department);

    void updateDepartementName(Long id, DepartmentDTO department);

    void deleteDepartement(Long id);

    Department getDepartementByName(String name);
}