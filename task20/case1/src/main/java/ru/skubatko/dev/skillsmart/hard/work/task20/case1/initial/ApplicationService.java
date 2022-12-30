package ru.skubatko.dev.skillsmart.hard.work.task20.case1.initial;

public class ApplicationService {

    public void processApplicationSearchParameters(ApplicationParametersDTO parametersDTO) {
        parametersDTO.setFillingBranchCode(setFromDict(parametersDTO.getFillingBranchCode(), DICT_CM_ORGANIZATION_UNIT));
        parametersDTO.setIssueBranch(setFromDict(parametersDTO.getIssueBranch(), DICT_CM_ORGANIZATION_UNIT));
        parametersDTO.setPurchaseRegion(setFromDict(parametersDTO.getPurchaseRegion(), DICT_CM_REGION));
        parametersDTO.setLiabilityType(setFromDict(parametersDTO.getLiabilityType(), DICT_CM_LIABILITY_TYPE));
        parametersDTO.setProductKindCode(setFromDict(parametersDTO.getProductKindCode(), DICT_CM_LOAN_PRODUCT_GROUP));
        parametersDTO.setProductType(setFromDict(parametersDTO.getProductType(), DICT_CM_LOAN_PRODUCT_TYPE));
        parametersDTO.setSaleChannel(setFromDict(parametersDTO.getSaleChannel(), DICT_CM_CHANNEL_KIND));
    }
}
