package ru.job4j.map;

import ru.job4j.collection.SimpleLinkedList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        int hashCode = 0;
        if (key != null) {
            hashCode = key.hashCode();
        }
        int index = indexFor(hashCode);
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    public int size() {
        return count;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    private void expand() {
        table = Arrays.copyOf(table, table.length * 2);
        capacity = table.length;
    }

    @Override
    public V get(K key) {
        int hashCode = 0;
        if (key != null) {
            hashCode = key.hashCode();
        }
        int index = indexFor(hashCode);
        if (table[index] != null) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int hashCode = 0;
        if (key != null) {
            hashCode = key.hashCode();
        }
        int index = indexFor(hashCode);
        if (table[index] != null) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return null;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
