package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDiffHandler implements DiffHandler {

    protected final GitService git;
    protected final SpoonService branchFromSpoonService;
    protected final SpoonService branchToSpoonService;

    protected DiffHandler next;

    @Override
    public void setNext(DiffHandler handler) {
        this.next = handler;
    }

    protected String getPath(DiffEntry diff) {
        return diff.getChangeType() == DiffEntry.ChangeType.DELETE ? diff.getOldPath() : diff.getNewPath();
    }

    protected boolean isNewChangeType(DiffEntry diff) {
        DiffEntry.ChangeType changeType = diff.getChangeType();
        return changeType == DiffEntry.ChangeType.ADD
                       || changeType == DiffEntry.ChangeType.COPY
                       || changeType == DiffEntry.ChangeType.RENAME;
    }

    protected boolean isDeleteChangeType(DiffEntry diff) {
        return diff.getChangeType() == DiffEntry.ChangeType.DELETE;
    }

    protected boolean isModifyChangeType(DiffEntry diff) {
        return diff.getChangeType() == DiffEntry.ChangeType.MODIFY;
    }

    protected ChangedUnit getChangeUnit(DiffEntry diff, BranchType branchType, CtExecutable<?> method) {
        String path = branchType == BranchType.FROM ? diff.getOldPath() : diff.getNewPath();
        ChangedUnit changedUnit = new ChangedUnit();
        changedUnit.setChangedFileName(path);
        changedUnit.setBranch(git.getBranchByType(branchType));
        changedUnit.setChangedMethod(method);
        changedUnit.setMethodChangeType(diff.getChangeType());
        return changedUnit;
    }
}
