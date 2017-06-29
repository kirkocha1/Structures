package com.kochnev.structures.trees.nodes;

public class Node<T extends Comparable<T>> {

    private T key;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public boolean hasLeftChild() {
        return left != null;
    }

    public boolean hasRightChild() {
        return right != null;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Node(T value) {
        key = value;
        left = null;
        right = null;
        parent = null;
    }


}
