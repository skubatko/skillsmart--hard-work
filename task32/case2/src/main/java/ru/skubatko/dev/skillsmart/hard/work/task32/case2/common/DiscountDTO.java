package ru.skubatko.dev.skillsmart.hard.work.task32.case2.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class DiscountDTO {
    private BigDecimal value;
    private String type;
    private String discountOrAllowance;
}
