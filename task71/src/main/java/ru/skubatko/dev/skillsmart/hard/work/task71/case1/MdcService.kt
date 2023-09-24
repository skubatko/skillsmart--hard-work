package ru.skubatko.dev.skillsmart.hard.work.task71.case1

import java.util.function.BiConsumer

interface MdcService {

    fun fillMdc(url: String, method: String, headerGetter: Function<String, String?>)

    fun fillHeaders(headerSetter: BiConsumer<String, String>)
}
