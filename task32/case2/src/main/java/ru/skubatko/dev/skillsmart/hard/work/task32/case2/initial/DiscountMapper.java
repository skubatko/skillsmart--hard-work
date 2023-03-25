package ru.skubatko.dev.skillsmart.hard.work.task32.case2.initial;

import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DictionaryMapperService;
import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DiscountDTO;
import ru.skubatko.dev.skillsmart.hard.work.task32.case2.common.DiscountEntity;

public class DiscountMapper {

    public DiscountDTO toDiscountDto(DiscountEntity discount, DictionaryMapperService service) {
        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setType(discount.getType());
        discountDTO.setDiscountOrAllowance(discount.getDiscountOrAllowance());

        discountDTO.setValue(discount.getValue().stripTrailingZeros());

        discountDTO.setType(service.setDictValueById("DICT_CM_DISCOUNT_PREMIUM_TYPE", discountDTO.getType()));

        return discountDTO;
    }
}
