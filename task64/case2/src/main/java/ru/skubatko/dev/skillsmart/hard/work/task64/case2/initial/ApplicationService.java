package ru.skubatko.dev.skillsmart.hard.work.task64.case2.initial;

import java.util.List;

public class ApplicationService {

    public void enhanceEmails(List<ApplicantEmailEntity> emails, ApplicantEntity applicant) {
        for (ApplicantEmailEntity email : emails) {
            email.setApplicant(applicant);
        }
    }
}
