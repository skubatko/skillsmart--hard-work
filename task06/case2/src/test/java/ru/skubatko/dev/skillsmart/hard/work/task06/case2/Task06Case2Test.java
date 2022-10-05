package ru.skubatko.dev.skillsmart.hard.work.task06.case2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Task06Case2Test {

    @Test
    public void testCase1() {
        String s = "abcd";
        Task06Case2.DoublyLinkedList list = new Task06Case2.DoublyLinkedList(s);

        list.split(1);
        list.reverse();
        list.split(1);

        assertThat(list.toString()).isEqualTo("dcba");
    }

    @Test
    public void testCase2() {
        String s = "ab";
        Task06Case2.DoublyLinkedList list = new Task06Case2.DoublyLinkedList(s);

        list.split(1);
        list.reverse();

        assertThat(list.toString()).isEqualTo("ab");
    }

    @Test
    public void testCase3() {
        String s = "abc";
        Task06Case2.DoublyLinkedList list = new Task06Case2.DoublyLinkedList(s);

        list.split(1);
        assertThat(list.toString()).isEqualTo("cba");

        list.split(1);
        assertThat(list.toString()).isEqualTo("abc");

        list.reverse();
        assertThat(list.toString()).isEqualTo("bac");
    }

    @Test
    public void testCase4() {
        String s = "abcdefgh";
        Task06Case2.DoublyLinkedList list = new Task06Case2.DoublyLinkedList(s);

        list.split(2);
        assertThat(list.toString()).isEqualTo("ghefcdab");

        list.reverse();
        assertThat(list.toString()).isEqualTo("badcfehg");

    }

    @Test
    public void testCase02() {
        String s = "abcdefghijklmnopqr";
        Task06Case2.DoublyLinkedList list = new Task06Case2.DoublyLinkedList(s);

        list.split(2);
        assertThat(list.toString()).isEqualTo("cdefghijklmnopqrab");

        list.split(5);
        assertThat(list.toString()).isEqualTo("hijklmnopqrabcdefg");

        list.reverse();
        assertThat(list.toString()).isEqualTo("gfedcbarqponmlkjih");

        list.split(1);
        assertThat(list.toString()).isEqualTo("fedcbarqponmlkjihg");

        list.reverse();
        assertThat(list.toString()).isEqualTo("ghijklmnopqrabcdef");

        list.split(8);
        assertThat(list.toString()).isEqualTo("opqrabcdefghijklmn");
    }
}
