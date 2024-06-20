package com.epitech.homepedia.repository;

import com.epitech.homepedia.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}