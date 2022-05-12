package ru.job4j.kiss;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void getMaxFromIntegerList() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(10, 2, 101, 3, 5);
        assertThat(maxMin.max(list, Integer::compareTo), Is.is(101));
    }

    @Test
    public void getMinFromIntegerList() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(10, 2, 101, 3, 5);
        assertThat(maxMin.min(list, Integer::compareTo), Is.is(2));
    }

    @Test
    public void getMaxFromStringList() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("ac", "mama", "torrent", "hello", "baby", "ab");
        assertThat(maxMin.max(list, String::compareTo), Is.is("torrent"));
    }

    @Test
    public void getMinFromStringList() {
        MaxMin maxMin = new MaxMin();
        List<String> list = List.of("ac", "mama", "torrent", "hello", "baby", "ab");
        assertThat(maxMin.min(list, String::compareTo), Is.is("ab"));
    }
}