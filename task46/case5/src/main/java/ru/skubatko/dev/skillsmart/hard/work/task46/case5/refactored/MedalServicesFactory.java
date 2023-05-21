package ru.skubatko.dev.skillsmart.hard.work.task46.case5.refactored;

import java.util.HashMap;
import java.util.Map;

public class MedalServicesFactory {
    private static final Map<String, MedalService> map = new HashMap<>();

    static {
        map.put("guard", new GuardMedalService());
        map.put("vip", new VipMedalService());
        map.put("guest", new GuestMedalService());
    }

    public static MedalService getMedalService(String medalType) {
        return map.get(medalType);
    }
}
