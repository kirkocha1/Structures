package com.kochnev.structures.trees;

import com.kochnev.structures.trees.absclasses.AbsTree;
import com.kochnev.structures.trees.nodes.Node;

public class BinaryTree<T extends Comparable<T>> extends AbsTree<T> {

    public BinaryTree() {
        root = null;
    }

    protected void Transplant(Node<T> oldNode, Node<T> newNode) {
        if (newNode != null && oldNode != null) {
            if (oldNode.getParent() == null) {
                root = newNode;
            } else if (oldNode.equals(oldNode.getParent().getLeft())) {
                    oldNode.getParent().setLeft(newNode);
            } else if (oldNode.equals(oldNode.getParent().getRight())) {
                    oldNode.getParent().setRight(newNode);
            }
            newNode.setParent(oldNode.getParent());
        }
    }

    @Override
    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            Node<T> element = new Node<>(value);
            Node<T> tempNode = root;
            Node<T> parent = null;
            while (tempNode != null) {
                parent = tempNode;
                if (element.getKey().compareTo(parent.getKey()) < 0) {
                    tempNode = tempNode.getLeft();
                } else {
                    tempNode = tempNode.getRight();
                }
            }
            element.setParent(parent);
            if (element.getKey().compareTo(parent.getKey()) < 0) {
                parent.setLeft(element);
            } else {
                parent.setRight(element);
            }
        }

    }


    @Override
    public void delete(T value) throws NullPointerException {
        Node<T> node = search(root, value);
        if (node == null) {
            throw new NullPointerException("value wasn't found, nothing to delete");
        }
        if (!node.hasLeftChild() && !node.hasRightChild()) {
            if (node.getParent().hasLeftChild() && node.getParent().getLeft().getKey() == node.getKey()) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
        } else if (!node.hasLeftChild()) {
            Transplant(node, node.getRight());
        } else if (!node.hasRightChild()) {
            Transplant(node, node.getLeft());
        } else {
            Node<T> oldNode = search(root, minValue(node.getRight().getKey()));
            if (oldNode.getParent().getKey() != node.getKey()) {
                if (oldNode.getRight() != null){
                    Transplant(oldNode, oldNode.getRight());
                } else {
                    oldNode.getParent().setLeft(null);
                }
                oldNode.setRight(node.getRight());
                oldNode.getRight().setParent(oldNode);

            }

            Transplant(node, oldNode);
            oldNode.setLeft(node.getLeft());
            oldNode.getLeft().setParent(oldNode);
        }
    }

    @Override
    public Node<T> search(T value) {
        if (root == null || root.getKey() == value) {
            return root;
        }
        Node<T> resultElement = root;
        while (resultElement != null && value != resultElement.getKey()) {
            if (value.compareTo(resultElement.getKey()) < 0) {
                resultElement = resultElement.getLeft();
            } else {
                resultElement = resultElement.getRight();
            }
        }
        return resultElement;
    }

    @Override
    public Node<T> search(Node<T> node, T value) {
        if (node == null || node.getKey().compareTo(value) == 0) {
            return node;
        }
        while (node != null && value != node.getKey()) {
            if (value.compareTo(node.getKey()) < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
    }
}


