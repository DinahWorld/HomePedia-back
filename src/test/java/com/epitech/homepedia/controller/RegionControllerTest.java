package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.RegionDTO;
import com.epitech.homepedia.model.Region;
import com.epitech.homepedia.services.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(Region.class)
class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegionService regionService;

    private RegionDTO dto;

    @BeforeEach
    void setUp() {
        dto = new RegionDTO("Paris");

        mockMvc = MockMvcBuilders.standaloneSetup(new RegionController(regionService)).build();
    }

    @Test
    void testAddRegion() throws Exception {
        doNothing().when(regionService).addRegion(any(RegionDTO.class));

        mockMvc.perform(post("/api/v1/region")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Region created"));
    }

    @Test
    void testUpdateRegion() throws Exception {
        doNothing().when(regionService).updateRegionName(anyLong(), any(RegionDTO.class));

        mockMvc.perform(put("/api/v1/region/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Region updated"));
    }

    @Test
    void testDeleteRegion() throws Exception {
        doNothing().when(regionService).deleteRegion(anyLong());

        mockMvc.perform(delete("/api/v1/region/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Region deleted"));
    }

    @Test
    void testGetAllRegion() throws Exception {
        when(regionService.getAllRegions()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/region/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Paris"));
    }

    @Test
    void testGetRegion() throws Exception {
        when(regionService.getRegionDTO(anyLong())).thenReturn(dto);

        mockMvc.perform(get("/api/v1/region/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paris"));
    }
}