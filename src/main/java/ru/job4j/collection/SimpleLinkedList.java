package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private int modCount;
    transient int size = 0;
    transient Node<E> fstNode;
    transient Node<E> lstNode;

    private static class Node<E> {
        E currentElement;
        Node<E> nextElement;
        Node<E> prevElement;

        Node(Node<E> prev, E element, Node<E> next) {
            this.currentElement = element;
            this.nextElement = next;
            this.prevElement = prev;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = lstNode;
        final Node<E> newNode = new Node<>(l, value, null);
        lstNode = newNode;
        if (l == null) {
            fstNode = newNode;
        } else {
            l.nextElement = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> target = fstNode;
        for (int i = 0; i < index; i++) {
            target = target.nextElement;
        }
        return target.currentElement;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int expectedModCount = modCount;
            Node<E> next = fstNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> lastReturned = next;
                next = next.nextElement;
                return lastReturned.currentElement;
            }
        };
    }
}
