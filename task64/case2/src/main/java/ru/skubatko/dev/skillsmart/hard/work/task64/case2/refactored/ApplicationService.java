package ru.skubatko.dev.skillsmart.hard.work.task64.case2.refactored;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService {

    public List<ApplicantEmailEntity> enhanceEmails(List<ApplicantEmailEntity> emails, ApplicantEntity applicant) {
        return emails.stream()
            .map(email -> {
                ApplicantEmailEntity applicantEmailEntity = new ApplicantEmailEntity();
                applicantEmailEntity.setType(email.getType());
                applicantEmailEntity.setValue(email.getValue());
                applicantEmailEntity.setApplicant(applicant);
                return applicantEmailEntity;
            })
            .collect(Collectors.toList());
    }
}
