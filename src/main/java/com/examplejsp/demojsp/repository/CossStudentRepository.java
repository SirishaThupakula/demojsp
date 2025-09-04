package com.examplejsp.demojsp.repository;

import com.examplejsp.demojsp.model.CossStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CossStudentRepository extends JpaRepository<CossStudent,Long> {
}
