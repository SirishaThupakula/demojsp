package com.examplejsp.demojsp.controller;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.service.CossStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    CossStudentService cossStudentService;

    @GetMapping("/login")
    public String showLoginPage(ModelMap model) {
        System.out.println("login method");
        model.put("cossStudentModel",new CossStudentDTO());
        return "login";
    }



    @PostMapping("/home")
    public String createOrUpdateCossStudent(ModelMap model,@ModelAttribute("cossStudentModel") CossStudentDTO cossStudentDTO) {
        boolean successful = cossStudentService.createOrUpdateCossStudent(cossStudentDTO);
        if(successful) {
            return "redirect:/cosslist";
        } else{
            model.addAttribute("errorMessage","Invalid Credentials");
            return "/login";
        }


    }

    @GetMapping("/cosslist")
    public String listOfCossStudents(ModelMap model) {
        List<CossStudentDTO> studentList = cossStudentService.retrieveCossStudentList();
        model.addAttribute("cossStudentList", studentList);
        return "showDetails";
    }

    @GetMapping("/edit")
    public String editCossStudentDetails(@RequestParam("id") String id, ModelMap model) {
        CossStudentDTO cossStudentDTO = cossStudentService.editStudentDetails(Long.parseLong(id));
        model.put("cossStudentModel", cossStudentDTO);
        return "login";
    }

    @PostMapping("/delete")
    public String deleteCossStudentDetails(@RequestParam("id") String id) {
        cossStudentService.deleteCossStudent(Long.parseLong(id));
        return "redirect:/cosslist";
    }



}