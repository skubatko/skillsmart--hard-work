package ru.skubatko.dev.skillsmart.hard.work.task06.case1;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.skubatko.dev.skillsmart.hard.work.task06.case1.Task06Case1.proceed;

import org.junit.jupiter.api.Test;

class Task06Case1Test {

    @Test
    public void proceedCase1() {
        int[] array = {0, 1, 1, 1, 3, 5, 8, 13, 21, 34};
        int value = 1;
        int[] expected = {0, 2};

        int[] result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase2() {
        int[] array = {0, 1, 1, 1, 3, 5, 8, 13, 21, 34};
        int value = 2;
        int[] expected = {3, 5};

        int[] result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase3() {
        int[] array = {0, 1, 1, 1, 3, 5, 8, 13, 21, 34};
        int value = 22;
        int[] expected = {9, 9};

        int[] result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase4() {
        int[] array = {0, 1, 1, 1, 3, 5, 8, 13, 21, 34};
        int value = 6;
        int[] expected = {6, 8};

        int[] result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void proceedCase5() {
        int[] array = {0, 1, 1, 1, 3, 5, 8, 13, 21, 34};
        int value = 36;
        int[] expected = {-1};

        int[] result = proceed(array, value);

        assertThat(result).isEqualTo(expected);
    }
}
