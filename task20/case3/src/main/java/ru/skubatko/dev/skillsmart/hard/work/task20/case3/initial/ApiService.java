package ru.skubatko.dev.skillsmart.hard.work.task20.case3.initial;

import java.util.Collections;
import java.util.Set;

public class ApiService {

    private void getAllMethodCallers(CtExecutable<?> executable, Set<CtExecutable<?>> executables) {
        log.trace("getAllMethodCallers() - start: branch = {}, method = {}",
            branch, SpoonHelper.getQualifiedName(executable));

        if (executable instanceof CtLambda) {
            CtMethod<?> parent = executable.getParent(CtMethod.class);
            return Collections.singleton(parent);
        }

        log.trace("getAllMethodCallers() - trace - getMethodCallers: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        enhanceCallersBySuperClasses(executable, executables);
        log.trace("getAllMethodCallers() - trace - enhanceCallersBySuperClasses: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        enhanceCallersByInterfaces(executable, executables);
        log.trace("getAllMethodCallers() - trace - enhanceCallersByInterfaces: method = {}, found executables = {}",
            SpoonHelper.getQualifiedName(executable), executables.size());

        log.trace("getAllMethodCallers() - end: branch = {}, final number of executables found = {}", branch, executables.size());
    }
}
