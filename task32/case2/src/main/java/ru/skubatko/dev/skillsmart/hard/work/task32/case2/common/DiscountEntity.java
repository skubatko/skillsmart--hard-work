package ru.skubatko.dev.skillsmart.hard.work.task32.case2.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountEntity {
    private Long objId;
    private BigDecimal value;
    private String type;
    private String discountOrAllowance;
    private ApplicationEntity application;
}
