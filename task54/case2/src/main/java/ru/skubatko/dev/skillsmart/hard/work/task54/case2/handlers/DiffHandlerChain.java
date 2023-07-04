package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiffHandlerChain {

    private final DiffHandler head;

    public DiffHandlerChain(GitService git, SpoonService branchFromSpoonService, SpoonService branchToSpoonService) {

        head = new JavaDiffHandler(git, branchFromSpoonService, branchToSpoonService);
        DiffHandler xmlDiffHandler = new XmlDiffHandler(git, branchFromSpoonService, branchToSpoonService);
        DiffHandler tail = new UnhandledDiffHandler(git, branchFromSpoonService, branchToSpoonService);

        head.setNext(xmlDiffHandler);
        xmlDiffHandler.setNext(tail);
    }

    public void handle(DiffEntry diff, List<AffectedUnit> affected, List<UnhandledUnit> unhandled) {
        log.debug("handle() - start: diff = {}, affected = {}, unhandled = {}",
                diff, affected.size(), unhandled.size());

        head.handle(diff, affected, unhandled);

        log.debug("handle() - end: diff = {}, affected = {}, unhandled = {}",
                diff, affected.size(), unhandled.size());
    }
}
