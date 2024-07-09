package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.model.Region;
import com.epitech.homepedia.repository.RegionRepository;
import com.epitech.homepedia.services.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<RegionDTO> getAllRegions() {
        return repository.findAll().stream().map(region -> mapper.map(region, RegionDTO.class)).toList();
    }

    @Override
    public RegionDTO getRegionDTO(Long id) {
        return mapper.map(getRegion(id), RegionDTO.class);
    }

    private Region getRegion(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.error("Region with id : {} don't exist", id);
            return new RuntimeException("Region with id : " + id + " don't exist");
        });
    }

    @Override
    public void addRegion(RegionDTO region) {
        repository.save(mapper.map(region, Region.class));
    }

    @Override
    public void updateRegionName(Long id, RegionDTO region) {
        var regionData = getRegion(id);
        regionData.setName(region.getName());
        repository.save(regionData);
    }

    @Override
    public void deleteRegion(Long id) {
        repository.delete(getRegion(id));
    }

    @Override
    public Region getRegionByName(String name) {
        return repository.findByName(name).orElseThrow(() -> {
            log.error("Region with name : {} don't exist", name);
            return new RuntimeException("Region with name : " + name + " don't exist");
        });
    }
}