package com.kochnev.structures.simplestructures;

public class Queue<T> {

    private class Element {
        Element next;
        Element previous;
        T node;

        Element(T node) {
            this.node = node;
            previous = null;
            next = null;
        }
    }

    private Element head;
    private Element tail;

    public boolean isEmpty;

    public void enqueue(T node) {
        Element element = new Element(node);
        if (head == null) {
            isEmpty = false;
            head = element;
            tail = element;
        } else {
            Element old = tail;
            tail = element;
            tail.next = old;
            tail.next.previous = element;
        }
    }

    public T dequeue() {
        if (head == null) {
            return null;
        }
        T result = head.node;
        if (head.previous != null) {
            head = head.previous;
            head.next = null;
        } else {
            head = null;
            tail = null;
            isEmpty = true;
        }
        return result;
    }
}
