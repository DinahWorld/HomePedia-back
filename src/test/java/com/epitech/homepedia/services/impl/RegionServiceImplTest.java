package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.model.Region;
import com.epitech.homepedia.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {

    @Mock
    private RegionRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private RegionServiceImpl regionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRegions() {
        Region region = new Region();
        RegionDTO regionDTO = new RegionDTO("");

        when(repository.findAll()).thenReturn(List.of(region));
        when(mapper.map(region, RegionDTO.class)).thenReturn(regionDTO);

        List<RegionDTO> regions = regionService.getAllRegions();

        assertNotNull(regions);
        assertEquals(1, regions.size());
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).map(region, RegionDTO.class);
    }

    @Test
    void testGetRegionDTO() {
        Long id = 1L;
        Region region = new Region();
        RegionDTO regionDTO = new RegionDTO("");

        when(repository.findById(id)).thenReturn(Optional.of(region));
        when(mapper.map(region, RegionDTO.class)).thenReturn(regionDTO);

        RegionDTO result = regionService.getRegionDTO(id);

        assertNotNull(result);
        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).map(region, RegionDTO.class);
    }

    @Test
    void testAddRegion() {
        RegionDTO regionDTO = new RegionDTO("");
        Region region = new Region();

        when(mapper.map(regionDTO, Region.class)).thenReturn(region);

        regionService.addRegion(regionDTO);

        verify(repository, times(1)).save(region);
    }

    @Test
    void testUpdateRegionName() {
        Long id = 1L;
        RegionDTO regionDTO = new RegionDTO("New Name");
        Region region = new Region();

        when(repository.findById(id)).thenReturn(Optional.of(region));

        regionService.updateRegionName(id, regionDTO);

        assertEquals("New Name", region.getName());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(region);
    }

    @Test
    void testDeleteRegion() {
        Long id = 1L;
        Region region = new Region();

        when(repository.findById(id)).thenReturn(Optional.of(region));

        regionService.deleteRegion(id);

        verify(repository, times(1)).delete(region);
    }

    @Test
    void testGetRegionNotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            regionService.getRegionDTO(id);
        });

        assertEquals("Region with id : " + id + " don't exist", exception.getMessage());
        verify(repository, times(1)).findById(id);
    }
}