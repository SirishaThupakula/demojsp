package com.examplejsp.demojsp.service;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.model.CossStudent;
import com.examplejsp.demojsp.repository.CossStudentRepository;
import com.examplejsp.demojsp.utility.CossUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CossStudentServiceImpl implements CossStudentService{


    @Autowired
    CossStudentRepository cossStudentRepository;
    @Override
    public boolean createOrUpdateCossStudent(CossStudentDTO cossStudentDTO) {

        if(CossUtility.validateUser(cossStudentDTO))
        {
             cossStudentRepository.save(CossUtility.convertCossStudentDtoToStudent(cossStudentDTO));
             return true;

        }

        return false;
    }

    @Override
    public List<CossStudentDTO> retrieveCossStudentList() {
        List<CossStudent> cossStudentList = cossStudentRepository.findAll();
        return cossStudentList.parallelStream().map( CossUtility::convertCossStudentToCossStudentDTO).toList();
    }

    @Override
    public CossStudentDTO editStudentDetails(Long cossStudentId) {
        System.out.println("cossStudentId::"+cossStudentId);
        Optional<CossStudent> cossStudentOptional=  cossStudentRepository.findById(cossStudentId);
        System.out.println(cossStudentOptional.isPresent());

       return    cossStudentOptional.map(CossUtility::convertCossStudentToCossStudentDTO).orElseThrow(IllegalArgumentException ::new);
    }

    @Override
    public void deleteCossStudent(Long cossStudentId) {
        cossStudentRepository.deleteById(cossStudentId);
    }
}
