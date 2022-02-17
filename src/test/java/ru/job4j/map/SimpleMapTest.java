package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        map.put("super", 100);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Iterator<String> iterator = map.iterator();
        iterator.next();
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("aaaaa", 1);
        map.put("bbbbb", 2);
        map.put("ccccc", 3);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNotNull(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNotNull(iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertNotNull(iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("aaaaa", 1);
        Assert.assertEquals("aaaaa", map.iterator().next());
        Assert.assertEquals("aaaaa", map.iterator().next());
    }

    @Test
    public void whenGetIteratorFromEmptyMapThenHasNextReturnFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertFalse(map.iterator().hasNext());
    }
}