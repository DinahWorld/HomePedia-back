package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}