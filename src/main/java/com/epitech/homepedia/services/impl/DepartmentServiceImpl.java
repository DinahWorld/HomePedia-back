package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.mapper.DepartmentDTOMapper;
import com.epitech.homepedia.model.Department;
import com.epitech.homepedia.repository.DepartmentRepository;
import com.epitech.homepedia.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    @Autowired
    private DepartmentDTOMapper mapper;

    @Override
    public List<DepartmentDTO> getAllDepartement() {
        return repository.findAll().stream().map(mapper::convertToDTO).toList();
    }

    @Override
    public DepartmentDTO getDepartementDTO(Long id) {
        return mapper.convertToDTO(getDepartment(id));
    }

    private Department getDepartment(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("Department with id : {} don't exist", id);
            return new RuntimeException("Department with id : " + id + " don't exist");
        });
    }

    @Override
    public void addDepartement(DepartmentDTO department) {
        repository.save(mapper.apply(department));
    }

    @Override
    public void updateDepartementName(Long id, DepartmentDTO department) {
        var departmentData = getDepartment(id);
        departmentData.setName(departmentData.getName());
        repository.save(departmentData);
    }

    @Override
    public void deleteDepartement(Long id) {
        repository.delete(getDepartment(id));
    }

    @Override
    public Department getDepartementByName(String name) {
        return repository.findByName(name).orElseThrow(() -> {
            log.error("Department with name : {} don't exist", name);
            return new RuntimeException("Department with name : " + name + " don't exist");
        });
    }
}