package com.examplejsp.demojsp.service;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.model.CossStudent;
import com.examplejsp.demojsp.repository.CossStudentRepository;
import com.examplejsp.demojsp.utility.CossUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.eclipse.jdt.internal.compiler.problem.ProblemSeverities.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CossStudentServiceTest {

    @Mock
    private CossStudentRepository cossStudentRepository;

    @InjectMocks
    private CossStudentServiceImpl  cossStudentService;

    @Test
    void testRetrieveCossStudentList(){
        List<CossStudent> cossStudentList = Arrays.asList(
                new CossStudent(1L,"sirisha","siri@gmail.com","1234"),
                new CossStudent(1L,"sai","sai@gmail.com","1234"),
                new CossStudent(1L,"char","chra@gmail.com","1234")
        );
        when(cossStudentRepository.findAll()).thenReturn(cossStudentList);
        List<CossStudentDTO> cossStudentDTOS =  cossStudentService.retrieveCossStudentList();
        assertEquals(3,cossStudentList.size());
        verify(cossStudentRepository, times(1)).findAll();
        assertEquals("sirisha",cossStudentDTOS.get(0).getCossName());
        assertEquals("chra@gmail.com",cossStudentDTOS.get(2).getCossEmail());

    }


    @Test
    void testSaveOrUpdateCossStudent(){
        CossStudent cossStudent =
                new CossStudent(1L,"sirisha","siri@gmail.com","1234");
        CossStudentDTO studentDTo =CossUtility.convertCossStudentToCossStudentDTO(cossStudent);
            
        when(cossStudentRepository.save(cossStudent)).thenReturn(cossStudent);

        Boolean  bool=  cossStudentService.createOrUpdateCossStudent(studentDTo);

        verify(cossStudentRepository, times(1)).save(cossStudent);
        assertEquals(true,bool);

    }

    @Test
    void testEditCossStudentDetails(){
        CossStudent cossStudent =
                new CossStudent(1L,"sirisha","siri@gmail.com","1234");

        when(cossStudentRepository.findById(1L)).thenReturn(java.util.Optional.of(cossStudent));
        CossStudentDTO studentDTo =CossUtility.convertCossStudentToCossStudentDTO(cossStudent);
        CossStudentDTO editStudentDTo = cossStudentService.editStudentDetails(1L);

        assertEquals(studentDTo,editStudentDTo);

    }

    @Test
    void testDeleteStudentById(){
        cossStudentService.deleteCossStudent(1L);
        verify(cossStudentRepository, times(1)).deleteById(1L);
        assertFalse(cossStudentRepository.findById(1L).isPresent());

    }
}
