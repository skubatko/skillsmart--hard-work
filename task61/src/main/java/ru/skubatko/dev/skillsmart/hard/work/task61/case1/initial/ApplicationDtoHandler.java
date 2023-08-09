package ru.skubatko.dev.skillsmart.hard.work.task61.case1.initial;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDtoHandler {

    public List<ApplicantDTO> filterApplicants(List<ApplicantDTO> applicants) {

        List<ApplicantDTO> list = new ArrayList<>();
        for (ApplicantDTO applicantDTO : applicants) {
            if (applicantDTO.getApplicantRoles() != null) {
                for (ApplicantRoleDTO applicantRoleDTO : applicantDTO.getApplicantRoles()) {
                    if (applicantRoleDTO.getApplicantRole().equals(APPLICANT_ROLE)) {
                        list.add(applicantDTO);
                        break;
                    }
                }
            }
        }
        return list;
    }
}
