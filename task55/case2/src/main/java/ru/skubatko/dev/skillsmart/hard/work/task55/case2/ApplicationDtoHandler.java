package ru.skubatko.dev.skillsmart.hard.work.task55.case2;

import java.util.stream.Collectors;

public class ApplicationDtoHandler {

    public ApplicationInputDTO handleApplication(ApplicationInputDTO application) {
        List<ApplicantDTO> applicants = application.getApplicants();
        if (applicants != null && !applicants.isEmpty()) {
            // фильтрация заявителей
            () -> {
                var filtered = applicants.stream().filter(
                        applicantDTO ->
                                applicantDTO.getApplicantRoles() != null &&
                                        applicantDTO.getApplicantRoles().stream().anyMatch(applicantRoleDTO ->
                                                applicantRoleDTO.getApplicantRole().equals(APPLICANT_ROLE)
                                        )
                ).collect(Collectors.toList());
                applicants.clear();
                applicants.addAll((filtered));
            }
        }
        application.setApplicants(applicants);

        return application;
    }
}
