package ru.skubatko.dev.skillsmart.hard.work.task33;

import java.util.HashMap;
import java.util.Map;

public class ClassAWrapper {
    private final Map<Integer, Object> components = new HashMap<>();

    public void setClassBValue(ClassB b) {
        components.put(ClassB.class.hashCode(), b);
    }

    public ClassB getClassBValue(){
        return (ClassB) components.get(ClassB.class.hashCode());
    }
}
