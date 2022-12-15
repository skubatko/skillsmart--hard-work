package ru.skubatko.dev.skillsmart.hard.work.task15.case1.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ReferenceUnit {
    GROUP, REFERENCE, ITEM,
    BRAND, MODEL, SET,
    PRODUCTION_KIND("productionKind"),
    VEHICLE_KIND("vehicleKind"),
    BODY_TYPE("bodyType"),
    ENGINE_CAPACITY("engineCapacity"),
    ENGINE_TYPE("engineType"),
    ENGINE_POWER("enginePower"),
    TRANSMISSION("transmission"),
    YEAR_OF_MANUFACTURE("yearOfManufacture");

    private String sysName;
}
