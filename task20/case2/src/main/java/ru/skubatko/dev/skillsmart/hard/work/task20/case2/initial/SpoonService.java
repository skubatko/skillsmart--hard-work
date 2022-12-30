package ru.skubatko.dev.skillsmart.hard.work.task20.case2.initial;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SpoonService {

    private void enhanceCallersByInterfaces(CtExecutable<?> executable, CtTypeReference<?> methodClass, Set<CtExecutable<?>> callers) {
        log.trace("enhanceCallersByInterfaces() - trace: method = {}, class = {}",
            SpoonHelper.getQualifiedName(executable), methodClass.getQualifiedName());

        Set<CtTypeReference<?>> superInterfaces = methodClass.getSuperInterfaces();

        while (CollectionUtils.isNotEmpty((superInterfaces))) {
            log.trace("enhanceCallersByInterfaces() - trace: class = {} has super interfaces = {}",
                methodClass.getQualifiedName(), superInterfaces.size());
            superInterfaces.forEach(i -> log.trace("enhanceCallersByInterfaces() - trace: class = {} has super interface = {}",
                methodClass.getQualifiedName(), i.getQualifiedName()));

            superInterfaces.forEach(
                superInterface -> callers.addAll(
                    getMethodCallers(superInterface.getQualifiedName() + DOT + executable.getSimpleName())));

            superInterfaces = superInterfaces.stream()
                .map(CtTypeReference::getSuperInterfaces)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        }
    }
}
