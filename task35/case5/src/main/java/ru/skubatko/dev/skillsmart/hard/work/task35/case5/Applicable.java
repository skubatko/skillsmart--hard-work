package ru.skubatko.dev.skillsmart.hard.work.task35.case5;

import lombok.NonNull;

public interface Applicable {

    Identifier getId();

    default Applicable addTimeAndVersionInfo(UnifiedApplication unifiedApplication) {
        addTimeAndVersionInfo(getId(), unifiedApplication);
        return this;
    }

    private void addTimeAndVersionInfo(@NonNull Identifier identifier, UnifiedApplication unifiedApplication) {
        identifier.setCreateTime(unifiedApplication.getAppDate());
        identifier.setUpdateTime(unifiedApplication.getAppLastUpDate());
        identifier.setVersion(Integer.valueOf(unifiedApplication.getVersion()));
    }
}
