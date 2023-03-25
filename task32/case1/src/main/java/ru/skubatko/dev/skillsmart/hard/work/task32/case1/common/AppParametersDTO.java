package ru.skubatko.dev.skillsmart.hard.work.task32.case1.common;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class AppParametersDTO  {
  private String number;
  private String surname;
  private String name;
  private String middleName;
  private String clientFullName;
  private LocalDate birthDate;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private String statusCode;
  private String purchaseRegion;
  private String createdChannel;
  private Boolean favouriteAppsFlag;
  private String clientMdmId;
  private String saleChannel;
  private String saleChannelSecondLevel;
  private String liabilityType;
  private String issueBranch;
  private String productType;
  private String passportSeries;
  private String passportNumber;
  private String productKindCode;
  private String user;
  private String userFullName;
}

