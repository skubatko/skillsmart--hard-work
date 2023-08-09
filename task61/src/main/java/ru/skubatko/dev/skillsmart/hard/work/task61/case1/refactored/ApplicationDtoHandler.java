package ru.skubatko.dev.skillsmart.hard.work.task61.case1.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task61.case1.common.ApplicantDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationDtoHandler {

    public List<ApplicantDTO> filterApplicants(List<ApplicantDTO> applicants) {

        return applicants.stream().filter(
            applicantDTO ->
                applicantDTO.getApplicantRoles() != null &&
                    applicantDTO.getApplicantRoles().stream().anyMatch(applicantRoleDTO ->
                        applicantRoleDTO.getApplicantRole().equals(APPLICANT_ROLE)
                    )
        ).collect(Collectors.toList());
    }
}
