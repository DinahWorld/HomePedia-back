package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Communes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunesRepository extends JpaRepository<Communes, Long> {
    List<Communes> findAllByNom(String region);

    Optional<Communes> findByNom(String regionName);

    Optional<Communes> findByCode(String codeReg);

    List<Communes> findAllByCode(String codeReg);
}