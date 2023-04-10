package ru.skubatko.dev.skillsmart.hard.work.task35.case5;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class InsuranceCompanyParty extends RetailCarLoanParty {
    @EqualsAndHashCode.Include
    private String Inn;
    @EqualsAndHashCode.Include
    private String CompanyCode;
    private String CompanyName;
    @EqualsAndHashCode.Include
    private String CompanyBik;
    private String CompanyAccount;
    private String CompanyAddress;
}
