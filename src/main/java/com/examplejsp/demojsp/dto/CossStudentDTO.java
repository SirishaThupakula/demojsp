package com.examplejsp.demojsp.dto;

import com.examplejsp.demojsp.model.CossStudent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class CossStudentDTO {

    private Long id;
    private String cossName;
    private String cossEmail;
    private String cossMobile;

    public CossStudentDTO(CossStudent student) {
        this.id = student.getId();
        this.cossName = student.getCossName();
        this.cossEmail = student.getCossEmail();
        this.cossMobile = student.getCossMobile();

    }

    public CossStudentDTO(String cossName, String cossEmail, String cossMobile) {
        this.cossName = cossName;
        this.cossEmail = cossEmail;
        this.cossMobile = cossMobile;

    }
}
