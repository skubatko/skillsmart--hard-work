package ru.skubatko.dev.skillsmart.hard.work.task20.case3.refactored;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ApiServiceRefactored {

    private Set<CtExecutable<?>> getAllMethodCallers(CtExecutable<?> executable) {
        log.trace("getAllMethodCallers() - start: branch = {}, method = {}",
            branch, SpoonHelper.getQualifiedName(executable));

        if (executable instanceof CtLambda) {
            CtMethod<?> parent = executable.getParent(CtMethod.class);
            return Collections.singleton(parent);
        }

        Set<CtExecutable<?>> executables = new HashSet<>(getMethodCallers(SpoonHelper.getQualifiedName(executable)));
        log.trace("getAllMethodCallers() - trace - getMethodCallers: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        enhanceCallersBySuperClasses(executable, executables);
        log.trace("getAllMethodCallers() - trace - enhanceCallersBySuperClasses: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        enhanceCallersByInterfaces(executable, executables);
        log.trace("getAllMethodCallers() - trace - enhanceCallersByInterfaces: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        log.trace("getAllMethodCallers() - end: branch = {}, final number of executables found = {}", branch, executables.size());
        return executables;
    }
}
