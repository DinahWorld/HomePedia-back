package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.ElectionDTO;
import com.epitech.homepedia.model.Election;
import com.epitech.homepedia.repository.ElectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class ElectionServiceImplTest {

    @Mock
    private ElectionRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ElectionServiceImpl electionService;

    @Test
    void testAddElection() {
        // define test data
        ElectionDTO electionDTO = new ElectionDTO();
        electionDTO.setCity("Paris");
        electionDTO.setPersonName("John Doe");

        Election expectedElection = new Election();
        expectedElection.setCity(electionDTO.getCity());
        expectedElection.setPersonName(electionDTO.getPersonName());

        // mock behavior of mapper
        Mockito.when(mapper.map(electionDTO, Election.class)).thenReturn(expectedElection);

        // call the method under test
        electionService.addElection(electionDTO);

        // verify interaction with repository
        Mockito.verify(repository).save(expectedElection);
    }

}