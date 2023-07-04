package ru.skubatko.dev.skillsmart.hard.work.task54.case2.handlers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlDiffHandler extends AbstractDiffHandler {

    private static final String XML_EXT = ".xml";

    public XmlDiffHandler(GitService git, SpoonService branchFromSpoonService, SpoonService branchToSpoonService) {
        super(git, branchFromSpoonService, branchToSpoonService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(DiffEntry diff, List<AffectedUnit> affected, List<UnhandledUnit> unhandled) {
        if (isXmlFile(getPath(diff))) {
            log.trace("handle() - verdict: XML file found for diff = {}", diff);
            DiffHandler myBatisXmlDiffHandler = new MyBatisXmlDiffHandler(git, branchFromSpoonService, branchToSpoonService);
            DiffHandler unhandledDiffHandler = new UnhandledDiffHandler(git, branchFromSpoonService, branchToSpoonService);

            myBatisXmlDiffHandler.setNext(unhandledDiffHandler);

            myBatisXmlDiffHandler.handle(diff, affected, unhandled);
        } else {
            next.handle(diff, affected, unhandled);
        }
    }

    private boolean isXmlFile(String path) {
        return path.endsWith(XML_EXT);
    }
}
