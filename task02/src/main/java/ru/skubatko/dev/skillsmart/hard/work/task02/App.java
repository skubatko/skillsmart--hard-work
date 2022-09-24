package ru.skubatko.dev.skillsmart.hard.work.task02;

public class App {

    public static String parseComplex(byte[] data) {
        if (data.length >= 3) {
            if (data[0] == (byte) 0x46 && data[1] == (byte) 0x55 && data[2] == (byte) 0x5a &&
                data[3] == (byte) 0x5a) {
                return "this will throw out of bound exception";
            }
        }
        return "ok";
    }
}
