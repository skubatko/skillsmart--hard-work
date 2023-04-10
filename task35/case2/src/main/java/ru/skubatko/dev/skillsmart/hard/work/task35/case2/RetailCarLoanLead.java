package ru.skubatko.dev.skillsmart.hard.work.task35.case2;

import lombok.Data;

/**
 * Лид Автокредит.
 */
@Data
public class RetailCarLoanLead implements Leadable, LMDWrappable {

    /**
     * Дополнительная информация о продукте
     */
    private RetailCarLoanLeadProductDetails productDetails;

    /**
     * Стартовое событие
     */
    private RetailLeadEventOnHookUpRef startEvent;

    /**
     * Запрошенный продукт
     */
    private String status;

    /**
     * Клиентская сессия
     */
    private RetailLeadSessionRef session;

    /**
     * Клиент
     */
    private RetailLeadCustomer customer;

    /**
     * Канал
     */
    private String channel;

    /**
     * Точка продаж
     */
    private RetailLeadSalesPoint salesPoint;

    /**
     * Маркетинговое предложение
     */
    private RetailLeadMarketingOfferRef offer;

    /**
     * Согласия
     */
    private List<RetailLeadConsentRef> consents;

    /**
     * Заявка на Продукт
     */
    private RetailLeadApplicationRef application;

    /**
     * Запрошенный продукт
     */
    private RetailLeadProduct product;

    /**
     * Ссылка на бизнес сущность
     */
    private Identifier id;
}
