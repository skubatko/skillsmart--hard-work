package ru.skubatko.dev.skillsmart;

import java.util.ArrayList;
import java.util.List;

/**
 * Нужно написать класс с функцией преобразования любого числа в название столбца Excel
 *
 * Например 1-A , 2-B ... 26-Z
 *
 * После 26 идет добавление символа в начало: 27-AA, 28-AB, 29-AC ... 53-BA ... 150-ET ... 5000-GJH, ... 18279-AAAA
 *
 * Программа должна показать опыт проектирования классов и использования структур данных.
 *
 * На вход в программу должен подаваться индекс столбца Excel, причем желательно иметь возможность подать сразу
 * несколько чисел:
 *
 * 1
 * 2
 * 26
 * 27
 * 150
 * 18279
 *
 * На выходе программа должна выдавать название столбца Excel:
 *
 * A
 * B
 * Z
 * AA
 * ET
 * AAAA
 */
public class ExcelTask {

    public static String convertNumToExcelCol(int num) {

        if (num < 1) {
            throw new IllegalArgumentException("wrong num");
        }

        String result;
        StringBuilder sb = new StringBuilder();

        char c;
        int val = 0;
        do {
            val = num % 26;
            if (val != 0) {
                c = (char) (val + 64);
            } else {
                c = 'Z';
            }
            sb.insert(0, c);
            num /= 26;
        } while (num > 0);
        result = sb.toString();
        return result;
    }

    public static String convertNumToExcelColByRecursion(int num) {

        if (num < 1) {
            throw new IllegalArgumentException("wrong num");
        }

        return convertNumToExcelColRecursively(num);
    }

    public static String convertNumToExcelColRecursively(int num) {
        StringBuilder s = new StringBuilder();
        char c;
        int val = num % 26;
        if (val != 0) {
            c = (char) (val + 64);
        } else {
            c = 'Z';
        }
        if (num / 26 > 0) {
            s.append(convertNumToExcelColRecursively(num / 26));
        }
        s.append(c);
        return s.toString();
    }

    public static List<String> doBatchConversion(int... nums) throws IllegalArgumentException {
        List<String> result = new ArrayList<>();

        for (int o : nums) {
            try {
                result.add(convertNumToExcelCol(o));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Wrong argument: " + o);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("==== by cycle ====");
        System.out.println(convertNumToExcelCol(1));
        System.out.println(convertNumToExcelCol(2));
        System.out.println(convertNumToExcelCol(26));
        System.out.println(convertNumToExcelCol(27));
        System.out.println(convertNumToExcelCol(150));
        System.out.println(convertNumToExcelCol(18279));

        try {
            System.out.println(convertNumToExcelCol(0));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument");
        }

        System.out.println("==== by recursion ====");
        System.out.println(convertNumToExcelColByRecursion(1));
        System.out.println(convertNumToExcelColByRecursion(2));
        System.out.println(convertNumToExcelColByRecursion(26));
        System.out.println(convertNumToExcelColByRecursion(27));
        System.out.println(convertNumToExcelColByRecursion(150));
        System.out.println(convertNumToExcelColByRecursion(18279));
        try {
            System.out.println(convertNumToExcelColByRecursion(0));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong argument");
        }

        try {
            System.out.println(doBatchConversion(1, 2, 26, 27, 150, 18279, 0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }

        try {
            System.out.println(doBatchConversion(1, 2, 26, 27, 150, 18279));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
