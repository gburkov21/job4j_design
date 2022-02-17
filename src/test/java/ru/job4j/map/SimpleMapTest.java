package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddNullThenReturnCorrectSize() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put(null, 1);
        assertEquals(1, map.size());
    }

    @Test
    public void whenAddNullAndDeleteThisThenDecreaseSize() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put(null, 1);
        assertEquals(1, map.size());
        map.remove(null);
        assertEquals(0, map.size());
    }

    @Test
    public void whenAddOneThenSizeIsOne() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        assertEquals(1, map.size());
    }

    @Test
    public void whenAddDuplicateThenSizeIsOne() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put("test", 1);
        assertEquals(1, map.size());
    }

    @Test
    public void whenAddTwoDifferentElementsThenSizeIsTwo() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        map.put("supertest", 5);
        assertEquals(2, map.size());
    }

    @Test
    public void whenAddElementThenReturnThisElement() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("test", 55);
        int value = map.get("test");
        assertEquals(55, value);
    }

    @Test
    public void whenAddTwoElementsThenReturnCorrectElement() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("test", 55);
        map.put("rama", 45);
        int value = map.get("rama");
        assertEquals(45, value);
    }

    @Test
    public void whenGetIncorrectElementThenReturnNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("super", 100);
        assertNull(map.get("test"));
    }

    @Test
    public void whenRemoveElementThenSizeDecrease() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("super", 100);
        map.put("mama", 10);
        assertEquals(2, map.size());
        map.remove("super");
        assertEquals(1, map.size());
        map.remove("mama");
        assertEquals(0, map.size());
    }

    @Test
    public void whenRemoveIncorrectElementThenReturnFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("super", 100);
        assertFalse(map.remove("Hello"));
    }
}