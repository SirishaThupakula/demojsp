package com.examplejsp.demojsp.utility;

import com.examplejsp.demojsp.dto.CossStudentDTO;
import com.examplejsp.demojsp.model.CossStudent;

import java.util.regex.Pattern;

public class CossUtility {


    public static CossStudent convertCossStudentDtoToStudent(CossStudentDTO cossStudentDTO){
        CossStudent cs = new CossStudent();
        if(cossStudentDTO.getId()!=null){
            cs.setId(cossStudentDTO.getId());
        }
        cs.setCossMobile(cossStudentDTO.getCossMobile());
        cs.setCossEmail(cossStudentDTO.getCossEmail());
        cs.setCossName(cossStudentDTO.getCossName());
        return cs;

    }

    public static CossStudentDTO convertCossStudentToCossStudentDTO(CossStudent cossStudent){
        CossStudentDTO cs = new CossStudentDTO();
        cs.setId(cossStudent.getId());
        cs.setCossMobile(cossStudent.getCossMobile());
        cs.setCossEmail(cossStudent.getCossEmail());
        cs.setCossName(cossStudent.getCossName());
        return cs;

    }

    public static  boolean validateUser(CossStudentDTO cossStudentDto) {
        String cossName = cossStudentDto.getCossName();
        String cossEmail=cossStudentDto.getCossEmail();
        String cossMobile =cossStudentDto.getCossMobile();
        System.out.println(" in login service");
        String regExName = "[A-Za-z]+";
        String regExMobile ="[0-9]+";
        String regExEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern namePattern = Pattern.compile(regExName, Pattern.CASE_INSENSITIVE);
        Pattern emailPattern = Pattern.compile(regExEmail, Pattern.CASE_INSENSITIVE);
        Pattern mobilePattern = Pattern.compile(regExMobile, Pattern.CASE_INSENSITIVE);
        System.out.println(namePattern.matcher(cossName).matches() +"  namePattern.matcher(cossName).matches()");
        System.out.println(emailPattern.matcher(cossEmail).matches() +" emailPattern.matcher(cossEmail).matches()");
        System.out.println(mobilePattern.matcher(cossMobile).matches() +"  mobilePattern.matcher(cossMobile).matches()");
        System.out.println("cossName "+cossName);
        System.out.println("cossEmail "+cossEmail);
        System.out.println("cossMobile "+cossMobile);


        return namePattern.matcher(cossName).matches() && emailPattern.matcher(cossEmail).matches() &&
                mobilePattern.matcher(cossMobile).matches();

    }







}
