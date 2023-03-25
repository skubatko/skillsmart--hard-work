package ru.skubatko.dev.skillsmart.hard.work.task32.case2.refactored;

import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DictionaryMapperService;
import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DiscountDTO;
import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DiscountEntity;

import java.math.BigDecimal;

public class DiscountMapperRefactored {

    public DiscountDTO toDiscountDto(DiscountEntity discount, DictionaryMapperService service) {
        if (discount == null) {
            return null;
        }

        String type = getType(discount);
        return new DiscountDTO()
            .setType(type)
            .setDiscountOrAllowance(getDiscountOrAllowance(discount))
            .setValue(getStripTrailingZeros(discount))
            .setType(getCmDiscountPremiumType(service, type));
    }

    private String getType(DiscountEntity discount) {
        return discount.getType();
    }

    private String getDiscountOrAllowance(DiscountEntity discount) {
        return discount.getDiscountOrAllowance();
    }

    private BigDecimal getStripTrailingZeros(DiscountEntity discount) {
        return discount.getValue().stripTrailingZeros();
    }

    private String getCmDiscountPremiumType(DictionaryMapperService service, String type) {
        return service.setDictValueById("DICT_CM_DISCOUNT_PREMIUM_TYPE", type);
    }
}
