package com.examplejsp.demojsp.controller;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.model.CossStudent;
import com.examplejsp.demojsp.repository.CossStudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CossStudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CossStudentRepository cossStudentRepository;

    @Test
    @Transactional
    void testStudentCreation() throws Exception {
        //record created
        mockMvc.perform(MockMvcRequestBuilders.post("/home")
                        .param("cossName", "John")        // StudentId field in CossStudentDTO
                        .param("cossEmail", "John@gmail.com")   // StudentName field in CossStudentDTO
                        .param("cossMobile", "12345"))           // Course field in CossStudentDTO
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cosslist"));

        //checking that only one record created and has the name email and phone number as specified.
        List<CossStudent> cossStudentList = cossStudentRepository.findAll();
        assert(cossStudentList.size()==1);
        assert(cossStudentList.getFirst().getCossName().equals("John"));
        assert(cossStudentList.getFirst().getCossEmail().equals("John@gmail.com"));
        assert(cossStudentList.getFirst().getCossMobile().equals("12345"));

    }


    @Test
    @Transactional
    void testStudentFetch() throws Exception {
        cossStudentRepository.save(CossStudent.builder().cossName("sirisha").cossEmail("siri@gmail.com").cossMobile("11234").build());

        MvcResult result = mockMvc.perform(get("/cosslist")).andReturn();
        @SuppressWarnings("unchecked")
        List<CossStudentDTO> students = (List<CossStudentDTO>)( result.getModelAndView().getModel().get("cossStudentList"));
        assertEquals(1,students.size());
        for (CossStudentDTO student : students) {
            assertEquals("sirisha",student.getCossName());
            assertEquals("siri@gmail.com",student.getCossEmail());
            assertEquals("11234",student.getCossMobile());
        }

    }

    @Test
    @Transactional
    public void editStudentListTest() throws Exception {
        CossStudent cossStudent= cossStudentRepository.save(CossStudent.builder().cossName("sirisha").cossEmail("siri@gmail.com").cossMobile("11234").build());
        mockMvc.perform(get("/edit")
                        .param("id",cossStudent.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("cossStudentModel"))
                .andExpect(model().attribute("cossStudentModel",hasProperty("cossName", is("sirisha"))))
                .andExpect(model().attribute("cossStudentModel",hasProperty("cossEmail", is("siri@gmail.com"))))
                .andExpect(model().attribute("cossStudentModel",hasProperty("cossMobile", is("11234")))) ;


    }

    @Test
    @Transactional
    public void UpdateStudentListTest() throws Exception {
        cossStudentRepository.save(CossStudent.builder().cossName("john").cossEmail("john@gmail.com").cossMobile("1234").build());
        //updating already edited record using edit method
        mockMvc.perform(MockMvcRequestBuilders.post("/home")
                        .param("id","1")
                        .param("cossName", "sirisha")        // StudentId field in CossStudentDTO
                        .param("cossEmail", "siri@gmail.com")   // StudentName field in CossStudentDTO
                        .param("cossMobile", "12345"))           // Course field in CossStudentDTO
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cosslist"));

        //checking that the existing record is updated with new name email and phone number as specified.
        List<CossStudent> cossStudentList = cossStudentRepository.findAll();
        assert(cossStudentList.size()==1);
        assert(cossStudentList.getFirst().getCossName().equals("sirisha"));
        assert(cossStudentList.getFirst().getCossEmail().equals("siri@gmail.com"));
        assert(cossStudentList.getFirst().getCossMobile().equals("12345"));



    }

    @Test
    @Transactional
    public void deleteStudentListTest() throws Exception {
        CossStudent cossStudent = cossStudentRepository.save(CossStudent.builder().cossName("john").cossEmail("john@gmail.com").cossMobile("1234").build());
        //updating already edited record using edit method
        mockMvc.perform(MockMvcRequestBuilders.post("/delete")
                        .param("id",cossStudent.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cosslist"));

        //checking that the existing record is deleted
        List<CossStudent> cossStudentList = cossStudentRepository.findAll();
        assertEquals(0,cossStudentList.size());

        Optional<CossStudent> cossStudentRetrieval = cossStudentRepository.findById(cossStudent.getId());
        assertFalse(cossStudentRetrieval.isPresent());


    }
}


