package ru.skubatko.dev.skillsmart.hard.work.task07.case1;

import ru.skubatko.dev.skillsmart.hard.work.task07.case1.document.Document;
import ru.skubatko.dev.skillsmart.hard.work.task07.case1.document.JsonDocument;
import ru.skubatko.dev.skillsmart.hard.work.task07.case1.document.XmlDocument;

public class Task07Case1 {

    public static void main(String[] args) {
        Document d = new Document();
        Document json = new JsonDocument();
        Document xml = new XmlDocument();

        d.print();
        json.print();
        xml.print();
    }
}
