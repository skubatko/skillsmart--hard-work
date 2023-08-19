package ru.skubatko.dev.skillsmart.hard.work.task64.case1.initial;

import java.util.List;

public class ApplicationService {

    public void enhancePhones(List<ApplicantPhoneEntity> phones, ApplicantEntity applicant) {
        for (ApplicantPhoneEntity phone : phones) {
            phone.setApplicant(applicant);
        }
    }
}
