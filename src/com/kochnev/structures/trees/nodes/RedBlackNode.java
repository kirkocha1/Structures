package com.kochnev.structures.trees.nodes;

public class RedBlackNode<T extends Comparable<T>> extends Node<T> {

    public enum Colour {
        Red, Black
    }

    private Colour colour;

    public RedBlackNode(T value) {
        super(value);
        colour = Colour.Black;
    }

    public RedBlackNode() {
        super(null);
        colour = Colour.Black;
        setLeft(this);
        setRight(this);
    }

    public boolean hasLeftChild(RedBlackNode emptyNode) {
        return !getLeft().equals(emptyNode);
    }

    public boolean hasRightChild(RedBlackNode emptyNode) {
        return !getRight().equals(emptyNode);
    }


    public boolean hasParent(RedBlackNode emptyNode) {
        return !getParent().equals(emptyNode);
    }

    public RedBlackNode<T> getGrandNode() {
        return hasParent() && getParent().hasParent() ? (RedBlackNode<T>) getParent().getParent() : null;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }
}