package ru.skubatko.dev.skillsmart.hard.work.task07.case2.document;

import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor.DocumentVisitor;

public abstract class AbstractDocument {

    public abstract void accept(DocumentVisitor visitor);
}
