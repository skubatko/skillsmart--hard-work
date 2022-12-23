package ru.skubatko.dev.skillsmart.hard.work.task18.imprecision.case2;

import java.util.List;

public interface ReferenceItemRepositoryRefactored {

    // передача одного id сужает логику
    // изменили на передачу списка id-шников,
    // теперь можно искать более гибко
    void find(List<Long> idList);
}
