package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnhandledDiffHandler extends AbstractDiffHandler {

    public UnhandledDiffHandler(GitService git, SpoonService branchFromSpoonService, SpoonService branchToSpoonService) {
        super(git, branchFromSpoonService, branchToSpoonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(DiffEntry diff, List<AffectedUnit> affected, List<UnhandledUnit> unhandled) {
        log.trace("handle() - verdict: Unhandled file found for diff = {}", diff);

        DiffHandler unhandledNonDeleteDiffHandler = new UnhandledNonDeleteDiffHandler(git, branchFromSpoonService, branchToSpoonService);
        DiffHandler unhandledDeleteDiffHandler = new UnhandledDeleteDiffHandler(git, branchFromSpoonService, branchToSpoonService);

        unhandledNonDeleteDiffHandler.setNext(unhandledDeleteDiffHandler);

        unhandledNonDeleteDiffHandler.handle(diff, affected, unhandled);
    }
}
