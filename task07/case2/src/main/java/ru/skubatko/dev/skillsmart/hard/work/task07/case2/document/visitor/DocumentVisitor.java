package ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor;

import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.Document;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.JsonDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.XmlDocument;

public interface DocumentVisitor {

    void visit(Document doc);

    void visit(JsonDocument json);

    void visit(XmlDocument xml);
}
