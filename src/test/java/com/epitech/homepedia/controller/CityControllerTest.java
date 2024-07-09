package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.CityDTO;
import com.epitech.homepedia.services.CityService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CityController.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    private CityDTO cityDTO;

    @BeforeEach
    void setUp() {
        cityDTO = CityDTO.builder()
                .name("Paris")
                .department("Ile-de-France")
                .postalCode(75000)
                .description("Capital of France")
                .averagePricePerSqm(new BigDecimal("10500.00"))
                .population(new BigDecimal("2148327"))
                .rating(new BigDecimal("4.8"))
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(new CityController(cityService)).build();
    }

    @Test
    void testCreateCity() throws Exception {
        doNothing().when(cityService).addCity(any(CityDTO.class));

        mockMvc.perform(post("/api/v1/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"department\":\"Ile-de-France\",\"postalCode\":75000,\"description\":\"Capital of France\",\"averagePricePerSqm\":10500.00,\"population\":2148327,\"rating\":4.8}"))
                .andExpect(status().isOk())
                .andExpect(content().string("City created"));
    }

    @Test
    void testUpdateCity() throws Exception {
        doNothing().when(cityService).updateCityName(anyLong(), any(CityDTO.class));

        mockMvc.perform(put("/api/v1/city/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"department\":\"Ile-de-France\",\"postalCode\":75000,\"description\":\"Capital of France\",\"averagePricePerSqm\":10500.00,\"population\":2148327,\"rating\":4.8}"))
                .andExpect(status().isOk())
                .andExpect(content().string("City updated"));
    }

    @Test
    void testDeleteCity() throws Exception {
        doNothing().when(cityService).deleteCity(anyLong());

        mockMvc.perform(delete("/api/v1/city/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("City deleted"));
    }

    @Test
    void testGetAllCities() throws Exception {
        when(cityService.getAllCity()).thenReturn(List.of(cityDTO));

        mockMvc.perform(get("/api/v1/city/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Paris"))
                .andExpect(jsonPath("$[0].department").value("Ile-de-France"))
                .andExpect(jsonPath("$[0].postalCode").value(75000))
                .andExpect(jsonPath("$[0].description").value("Capital of France"))
                .andExpect(jsonPath("$[0].averagePricePerSqm").value(10500.00))
                .andExpect(jsonPath("$[0].population").value(2148327))
                .andExpect(jsonPath("$[0].rating").value(4.8));
    }

    @Test
    void testGetCity() throws Exception {
        when(cityService.getCityDTO(anyLong())).thenReturn(cityDTO);

        mockMvc.perform(get("/api/v1/city/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paris"))
                .andExpect(jsonPath("$.department").value("Ile-de-France"))
                .andExpect(jsonPath("$.postalCode").value(75000))
                .andExpect(jsonPath("$.description").value("Capital of France"))
                .andExpect(jsonPath("$.averagePricePerSqm").value(10500.00))
                .andExpect(jsonPath("$.population").value(2148327))
                .andExpect(jsonPath("$.rating").value(4.8));
    }
}