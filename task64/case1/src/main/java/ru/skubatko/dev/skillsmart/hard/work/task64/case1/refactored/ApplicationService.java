package ru.skubatko.dev.skillsmart.hard.work.task64.case1.refactored;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationService {

    public List<ApplicantPhoneEntity> enhancePhones(List<ApplicantPhoneEntity> phones, ApplicantEntity applicant) {
        return phones.stream()
            .map(phone -> {
                ApplicantPhoneEntity applicantPhoneEntity = new ApplicantPhoneEntity();
                applicantPhoneEntity.setType(phone.getType());
                applicantPhoneEntity.setValue(phone.getValue());
                applicantPhoneEntity.setApplicant(applicant);
                return applicantPhoneEntity;
            })
            .collect(Collectors.toList());
    }
}
