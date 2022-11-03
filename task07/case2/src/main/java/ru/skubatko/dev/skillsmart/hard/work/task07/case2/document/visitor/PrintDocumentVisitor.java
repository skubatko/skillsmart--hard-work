package ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor;

import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.Document;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.JsonDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.XmlDocument;

public class PrintDocumentVisitor implements DocumentVisitor {

    @Override
    public void visit(Document doc) {
        System.out.println("printing Document ...");
    }

    @Override
    public void visit(JsonDocument json) {
        System.out.println("printing JsonDocument ...");
    }

    @Override
    public void visit(XmlDocument xml) {
        System.out.println("printing XmlDocument ...");
    }
}
