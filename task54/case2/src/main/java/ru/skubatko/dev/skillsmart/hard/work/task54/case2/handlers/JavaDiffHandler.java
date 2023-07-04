package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaDiffHandler extends AbstractDiffHandler {

    private static final String MAVEN_SOURCE_PREFIX = "src/main/java/";
    private static final String JAVA_EXT = ".java";
    private static final String DOT = ".";
    private static final String SLASH = "/";

    public JavaDiffHandler(GitService git, SpoonService branchFromSpoonService, SpoonService branchToSpoonService) {
        super(git, branchFromSpoonService, branchToSpoonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(DiffEntry diff, List<AffectedUnit> affected, List<UnhandledUnit> unhandled) {
        if (isJavaFile(getPath(diff))) {
            log.trace("handle() - verdict: Java file found for diff = {}", diff);
            DiffHandler javaModifyHandler = new JavaModifyDiffHandler(git, branchFromSpoonService, branchToSpoonService);
            DiffHandler javaNewHandler = new JavaNewDiffHandler(git, branchFromSpoonService, branchToSpoonService);
            DiffHandler javaDeleteHandler = new JavaDeleteDiffHandler(git, branchFromSpoonService, branchToSpoonService);

            javaModifyHandler.setNext(javaNewHandler);
            javaNewHandler.setNext(javaDeleteHandler);

            javaModifyHandler.handle(diff, affected, unhandled);
        } else {
            next.handle(diff, affected, unhandled);
        }
    }

    private boolean isJavaFile(String path) {
        return path.endsWith(JAVA_EXT);
    }

    protected String getClassQualifiedName(String path) {
        log.trace("getClassQualifiedName() - start: path = {}", path);
        String qualifiedName = path.replace(SLASH, DOT);

        if (path.contains(MAVEN_SOURCE_PREFIX)) {
            int index = path.indexOf(MAVEN_SOURCE_PREFIX);
            qualifiedName = qualifiedName.substring(index + MAVEN_SOURCE_PREFIX.length());
        } else {
            log.trace("getClassQualifiedName() - verdict: qualifiedName is empty");
            return StringUtils.EMPTY;
        }

        if (qualifiedName.endsWith(JAVA_EXT)) {
            int index = qualifiedName.lastIndexOf(JAVA_EXT);
            qualifiedName = qualifiedName.substring(0, index);
        } else {
            log.trace("getClassQualifiedName() - verdict: qualifiedName is empty");
            return StringUtils.EMPTY;
        }

        log.trace("getClassQualifiedName() - end: qualifiedName = {}", qualifiedName);
        return qualifiedName;
    }
}
