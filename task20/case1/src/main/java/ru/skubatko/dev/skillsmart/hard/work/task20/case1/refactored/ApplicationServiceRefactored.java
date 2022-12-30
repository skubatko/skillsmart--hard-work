package ru.skubatko.dev.skillsmart.hard.work.task20.case1.refactored;

public class ApplicationServiceRefactored {

    public ApplicationParametersDTO processApplicationSearchParameters(ApplicationParametersDTO source) {
        ApplicationParametersDTO parametersDTO = new ApplicationParametersDTO();
        parametersDTO.setFillingBranchCode(setFromDict(source.getFillingBranchCode(), DICT_CM_ORGANIZATION_UNIT));
        parametersDTO.setIssueBranch(setFromDict(source.getIssueBranch(), DICT_CM_ORGANIZATION_UNIT));
        parametersDTO.setPurchaseRegion(setFromDict(source.getPurchaseRegion(), DICT_CM_REGION));
        parametersDTO.setLiabilityType(setFromDict(source.getLiabilityType(), DICT_CM_LIABILITY_TYPE));
        parametersDTO.setProductKindCode(setFromDict(source.getProductKindCode(), DICT_CM_LOAN_PRODUCT_GROUP));
        parametersDTO.setProductType(setFromDict(source.getProductType(), DICT_CM_LOAN_PRODUCT_TYPE));
        parametersDTO.setSaleChannel(setFromDict(source.getSaleChannel(), DICT_CM_CHANNEL_KIND));
        return parametersDTO;
    }
}
