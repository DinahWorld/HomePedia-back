package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}