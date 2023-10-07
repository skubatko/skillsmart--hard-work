package ru.skubatko.dev.skillsmart.hard.work.task73.case1;

import java.util.List;

public interface ApiChangesResolverService<S, R> {

    List<R> getResult(S source);
}
