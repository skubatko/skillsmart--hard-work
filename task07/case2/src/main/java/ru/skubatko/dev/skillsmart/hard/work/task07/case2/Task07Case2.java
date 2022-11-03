package ru.skubatko.dev.skillsmart.hard.work.task07.case2;

import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.AbstractDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.Document;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.JsonDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.XmlDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor.DocumentVisitor;
import ru.skubatko.dev.skillsmart.hard.work.task07.case2.document.visitor.PrintDocumentVisitor;

public class Task07Case2 {

    public static void main(String[] args) {
        DocumentVisitor visitor = new PrintDocumentVisitor();

        AbstractDocument doc = new Document();
        AbstractDocument json = new JsonDocument();
        AbstractDocument xml = new XmlDocument();

        doc.accept(visitor);
        json.accept(visitor);
        xml.accept(visitor);
    }
}
