package com.examplejsp.demojsp.controller;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.service.CossStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class CossStudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CossStudentService cossStudentService;
    @Test
    public void showLoginPageTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("cossStudentModel"));

    }

    @Test
    void testUpdateCossStudent() throws Exception {
        when(cossStudentService.createOrUpdateCossStudent(any(CossStudentDTO.class))).thenReturn(true);
        mockMvc.perform(post("/home")
                        .param("cossName", "John")        // StudentId field in CossStudentDTO
                        .param("cossEmail", "John@gmail.com")   // StudentName field in CossStudentDTO
                        .param("cossMobile", "12345"))           // Course field in CossStudentDTO
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cosslist"));

    }
    @Test
    void testUpdateCossStudentForFailure() throws Exception {
        when(cossStudentService.createOrUpdateCossStudent(any(CossStudentDTO.class))).thenReturn(false);
        mockMvc.perform(post("/home")
                        .param("cossName", "John")        // StudentId field in CossStudentDTO
                        .param("cossEmail", "John@gmail.com")   // StudentName field in CossStudentDTO
                        .param("cossMobile", "12345"))           // Course field in CossStudentDTO
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));

    }

    @Test
    public void showStudentListTest() throws Exception {
        mockMvc.perform(get("/cosslist"))
                .andExpect(status().isOk())
                .andExpect(view().name("showDetails"))
                .andExpect(model().attributeExists("cossStudentList"));

    }

    @Test
    public void editStudentListTest() throws Exception {
        when(cossStudentService.editStudentDetails(any(Long.class))).thenReturn(new CossStudentDTO(2L,"sirisha","siri@gmail.com","1234"));
        mockMvc.perform(get("/edit")
                .param("id","2"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("cossStudentModel"));

    }

    @Test
    public void deleteStudentTest() throws Exception {
        mockMvc.perform(post("/delete")
                        .param("id","2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cosslist"));


    }


}
