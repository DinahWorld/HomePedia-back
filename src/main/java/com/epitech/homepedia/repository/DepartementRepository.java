package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
    List<Departement> findAllByNom(String region);

    Optional<Departement> findByNom(String regionName);

    Optional<Departement> findByCode(String codeReg);

    List<Departement> findAllByCode(String codeReg);
}