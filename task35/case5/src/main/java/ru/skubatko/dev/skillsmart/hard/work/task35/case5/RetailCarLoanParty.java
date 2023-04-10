package ru.skubatko.dev.skillsmart.hard.work.task35.case5;

import lombok.Data;

/**
 * Вовлеченное лицо
 */
@Data
public abstract class RetailCarLoanParty implements Applicable, LMDWrappable {

    private Identifier Id;
    private String PartyType;
}
