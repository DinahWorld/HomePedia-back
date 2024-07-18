package com.epitech.homepedia.services;

import com.epitech.homepedia.model.Departements;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentService {
    List<Departements> getAll();

    void addDepartment(BigDecimal price, String code);
}