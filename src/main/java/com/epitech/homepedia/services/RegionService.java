package com.epitech.homepedia.services;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.model.Region;

import java.util.List;

public interface RegionService {
    List<RegionDTO> getAllRegions();

    RegionDTO getRegionDTO(Long id);

    void addRegion(RegionDTO region);

    void updateRegionName(Long id, RegionDTO region);

    void deleteRegion(Long id);

    Region getRegionByName(String name);
}