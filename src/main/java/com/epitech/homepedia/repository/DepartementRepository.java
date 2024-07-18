package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Departements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departements, Long> {
}