package ru.job4j.map;

import ru.job4j.collection.SimpleLinkedList;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int hashCode = hash(key);
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

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    private void expand() {
        if ((float) (count / capacity) >= LOAD_FACTOR) {
            MapEntry<K, V>[] newMap = new MapEntry[table.length * 2];
            capacity = newMap.length;
            for (MapEntry<K, V> tableEntry : table) {
                if (tableEntry != null) {
                    int hashCode = hash(tableEntry.key);
                    int index = indexFor(hashCode);
                    newMap[index] = tableEntry;
                }
            }
            table = newMap;
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int keyHashCode = hash(key);
        int keyIndex = indexFor(keyHashCode);
        if (table[keyIndex] != null) {
            K cellKey = table[keyIndex].key;
            int cellHashCode = hash(cellKey);
            if (keyHashCode == cellHashCode && Objects.equals(key, cellKey)) {
                result = table[keyIndex].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int keyHashCode = hash(key);
        int keyIndex = indexFor(keyHashCode);
        if (table[keyIndex] != null) {
            K cellKey = table[keyIndex].key;
            int cellHashCode = hash(cellKey);
            if (keyHashCode == cellHashCode && Objects.equals(key, cellKey)) {
                table[keyIndex] = null;
                count--;
                modCount++;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                        cursor = i;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
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
