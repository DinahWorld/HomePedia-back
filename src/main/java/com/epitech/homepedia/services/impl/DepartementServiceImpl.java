package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.model.Departements;
import com.epitech.homepedia.repository.DepartementRepository;
import com.epitech.homepedia.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartementServiceImpl implements DepartmentService {
    private final DepartementRepository departementRepository;

    @Override
    public List<Departements> getAll() {
        return departementRepository.findAll();
    }

    @Override
    public void addDepartment(BigDecimal price, String code) {
        var department = new Departements();
        department.setCode(code);
        department.setPrice(price.intValue());
        departementRepository.save(department);
    }


}