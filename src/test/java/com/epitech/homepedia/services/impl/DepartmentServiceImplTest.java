package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.DepartmentDTO;
import com.epitech.homepedia.mapper.DepartmentDTOMapper;
import com.epitech.homepedia.model.Department;
import com.epitech.homepedia.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository repository;

    @Mock
    private DepartmentDTOMapper mapper;

    @InjectMocks
    private DepartmentServiceImpl service;

    private Department department;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("IT");

        departmentDTO = new DepartmentDTO();
        departmentDTO.setName("IT");
    }

    @Test
    void testGetAllDepartement() {
        when(repository.findAll()).thenReturn(List.of(department));
        when(mapper.convertToDTO(department)).thenReturn(departmentDTO);

        List<DepartmentDTO> result = service.getAllDepartement();

        assertEquals(1, result.size());
        assertEquals(departmentDTO, result.getFirst());
    }

    @Test
    void testGetDepartementDTO() {
        when(repository.findById(1L)).thenReturn(Optional.of(department));
        when(mapper.convertToDTO(department)).thenReturn(departmentDTO);

        DepartmentDTO result = service.getDepartementDTO(1L);

        assertEquals(departmentDTO, result);
    }

    @Test
    void testGetDepartementDTO_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getDepartementDTO(1L));
        assertEquals("Department with id : 1 don't exist", exception.getMessage());
    }

    @Test
    void testAddDepartement() {
        when(mapper.apply(departmentDTO)).thenReturn(department);

        service.addDepartement(departmentDTO);

        verify(repository, times(1)).save(department);
    }

    @Test
    void testUpdateDepartementName() {
        when(repository.findById(1L)).thenReturn(Optional.of(department));

        service.updateDepartementName(1L, departmentDTO);

        verify(repository, times(1)).save(department);
    }

    @Test
    void testDeleteDepartement() {
        when(repository.findById(1L)).thenReturn(Optional.of(department));

        service.deleteDepartement(1L);

        verify(repository, times(1)).delete(department);
    }

    @Test
    void testDeleteDepartement_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.deleteDepartement(1L));
        assertEquals("Department with id : 1 don't exist", exception.getMessage());
    }

    @Test
    void testGetDepartementByName() {
        when(repository.findByName("IT")).thenReturn(Optional.of(department));

        Department result = service.getDepartementByName("IT");

        assertEquals(department, result);
    }

    @Test
    void testGetDepartementByName_NotFound() {
        when(repository.findByName("IT")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getDepartementByName("IT"));
        assertEquals("Department with name : IT don't exist", exception.getMessage());
    }
}