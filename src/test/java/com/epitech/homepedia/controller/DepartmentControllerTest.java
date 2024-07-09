package com.epitech.homepedia.controller;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.services.DepartmentService;
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
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentDTO dto;

    @BeforeEach
    void setUp() {
        dto = DepartmentDTO.builder()
                .name("Paris")
                .region("Seine-Saint-Denis")
                .description("Capital of France")
                .averagePricePerSqm(new BigDecimal("10500.00"))
                .population(new BigDecimal("2148327"))
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentController(departmentService)).build();
    }

    @Test
    void testAddDepartment() throws Exception {
        doNothing().when(departmentService).addDepartement(any(DepartmentDTO.class));

        mockMvc.perform(post("/api/v1/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"region\":\"Seine-Saint-Denis\",\"description\":\"Capital of France\",\"averagePricePerSqm\":10500.00,\"population\":2148327}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department created"));
    }

    @Test
    void testUpdateDepartment() throws Exception {
        doNothing().when(departmentService).updateDepartementName(anyLong(), any(DepartmentDTO.class));

        mockMvc.perform(put("/api/v1/department/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"region\":\"Seine-Saint-Denis\",\"description\":\"Capital of France\",\"averagePricePerSqm\":10500.00,\"population\":2148327}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department updated"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartement(anyLong());

        mockMvc.perform(delete("/api/v1/department/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department deleted"));
    }

    @Test
    void testGetAllDepartment() throws Exception {
        when(departmentService.getAllDepartement()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/department/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Paris"))
                .andExpect(jsonPath("$[0].region").value("Seine-Saint-Denis"))
                .andExpect(jsonPath("$[0].description").value("Capital of France"))
                .andExpect(jsonPath("$[0].averagePricePerSqm").value(10500.00))
                .andExpect(jsonPath("$[0].population").value(2148327));
    }

    @Test
    void testGetDepartment() throws Exception {
        when(departmentService.getDepartementDTO(anyLong())).thenReturn(dto);

        mockMvc.perform(get("/api/v1/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paris"))
                .andExpect(jsonPath("$.region").value("Seine-Saint-Denis"))
                .andExpect(jsonPath("$.description").value("Capital of France"))
                .andExpect(jsonPath("$.averagePricePerSqm").value(10500.00))
                .andExpect(jsonPath("$.population").value(2148327));
    }
}