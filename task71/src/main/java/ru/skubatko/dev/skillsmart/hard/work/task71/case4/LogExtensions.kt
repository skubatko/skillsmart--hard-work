package ru.skubatko.dev.skillsmart.hard.work.task71.case4

import org.slf4j.Logger
import org.slf4j.Marker

inline fun Logger.info(marker: Marker? = null, lazyMessage: () -> String?) {
    if (isInfoEnabled) {
        if (marker != null) info(marker, lazyMessage()) else info(lazyMessage())
    }
}
