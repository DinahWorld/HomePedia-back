package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findAllByNom(String region);

    Optional<Region> findByNom(String regionName);

    Optional<Region> findByCode(String codeReg);

    List<Region> findAllByCode(String codeReg);
}