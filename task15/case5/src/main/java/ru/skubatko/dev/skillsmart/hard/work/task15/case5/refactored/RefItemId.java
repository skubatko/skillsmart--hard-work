package ru.skubatko.dev.skillsmart.hard.work.task15.case5.refactored;

import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class RefItemId {
    @NonNull private final UUID id;
}
