package ru.skubatko.dev.skillsmart.hard.work.task07.case2.document;

import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor.DocumentVisitor;

public class JsonDocument extends AbstractDocument {

    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
}
