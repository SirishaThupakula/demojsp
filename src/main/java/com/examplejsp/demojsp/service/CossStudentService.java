package com.examplejsp.demojsp.service;

import com.examplejsp.demojsp.dto.CossStudentDTO;

import java.util.List;

public interface CossStudentService {
    public boolean createOrUpdateCossStudent(CossStudentDTO cossStudentDTO);
    public List<CossStudentDTO> retrieveCossStudentList();
    public CossStudentDTO editStudentDetails(Long cossStudentId);
    public  void deleteCossStudent(Long cossStudentId);

 }
