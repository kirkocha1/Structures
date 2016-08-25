package com.kochnev.structures.simplestructures;

public class Stack<T> {

    private class Element
    {
        Element previous;
        T node;

        Element(T node)
        {
            this.node = node;
        }
    }

    public boolean isEmpty;
    private Element head;

    public void push(T node)
    {
        Element element = new Element(node);
        if (head == null)
        {
            head = element;
        }
        else
        {
            Element old = head;
            head = element;
            head.previous = old;
        }
        isEmpty = false;
    }

    public T pop()
    {
        if (isEmpty)
        {
            return null;
        }
        else
        {
            T result = head.node;
            head = head.previous;
            if (head == null)
            {
                isEmpty = true;
            }
            return result;
        }
    }
}
