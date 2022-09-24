package ru.skubatko.dev.skillsmart.hard.work.task02;

import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class ExcelTaskTest {

    @Fuzz
    public void testConvertNumToExcelCol(int num) {
        ExcelTask.convertNumToExcelCol(num);
    }
}
