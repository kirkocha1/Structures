package com.kochnev.structures.trees;

import com.kochnev.structures.trees.nodes.Node;
import com.kochnev.structures.trees.nodes.RedBlackNode;

public class RedBlackTree<T extends Comparable<T>> extends BinaryTree<T> {

    private RedBlackNode<T> emptyNode = new RedBlackNode<>();

    public RedBlackTree() {
        root = emptyNode;
    }

    private void rightRotate(RedBlackNode<T> node) {
        RedBlackNode<T> leftChild;
        if (node.hasLeftChild(emptyNode)) {
            leftChild = (RedBlackNode<T>) node.getLeft();
            node.setLeft(leftChild.getRight());
            if (leftChild.hasRightChild(emptyNode)) {
                leftChild.getRight().setParent(node);
            }
            leftChild.setParent(node.getParent());
            if (!node.hasParent(emptyNode)) {
                root = leftChild;
            } else if (node.equals(node.getParent().getRight())) {
                node.getParent().setRight(leftChild);
            } else {
                node.getParent().setLeft(leftChild);
            }
            leftChild.setRight(node);
            node.setParent(leftChild);
        }
    }

    private void leftRotate(RedBlackNode<T> node) {
        RedBlackNode<T> rightChild;
        if (node.hasRightChild(emptyNode)) {
            rightChild = (RedBlackNode<T>) node.getRight();
            node.setRight(rightChild.getLeft());
            if (rightChild.hasLeftChild(emptyNode)) {
                rightChild.getLeft().setParent(node);
            }
            rightChild.setParent(node.getParent());
            if (!node.hasParent(emptyNode)) {
                root = rightChild;
            } else if (node.equals(node.getParent().getLeft())) {
                node.getParent().setLeft(rightChild);
            } else {
                node.getParent().setRight(rightChild);
            }
            rightChild.setLeft(node);
            node.setParent(rightChild);
        }
    }

    private void balanceAfterInsert(RedBlackNode<T> node) {

        while (((RedBlackNode<T>) node.getParent()).getColour() == RedBlackNode.Colour.Red) {

            if (node.getParent().equals(node.getGrandNode().getLeft())) {
                RedBlackNode element = (RedBlackNode) node.getGrandNode().getRight();

                if (element.getColour() == RedBlackNode.Colour.Red) {
                    ((RedBlackNode) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    element.setColour(RedBlackNode.Colour.Black);
                    node.getGrandNode().setColour(RedBlackNode.Colour.Red);
                    node = node.getGrandNode();
                } else {
                    if (node.equals(node.getParent().getRight())) {
                        node = (RedBlackNode<T>) node.getParent();
                        leftRotate(node);
                    }
                    ((RedBlackNode) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    node.getGrandNode().setColour(RedBlackNode.Colour.Red);
                    rightRotate(node.getGrandNode());
                }

            } else {
                RedBlackNode element;
                element = (RedBlackNode) node.getGrandNode().getLeft();
                if (element.getColour() == RedBlackNode.Colour.Red) {
                    ((RedBlackNode) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    element.setColour(RedBlackNode.Colour.Black);
                    node.getGrandNode().setColour(RedBlackNode.Colour.Red);
                    node = node.getGrandNode();
                } else {
                    if (node.equals(node.getParent().getLeft())) {
                        node = (RedBlackNode<T>) node.getParent();
                        rightRotate(node);
                    }
                    ((RedBlackNode) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    node.getGrandNode().setColour(RedBlackNode.Colour.Red);
                    leftRotate(node.getGrandNode());
                }


            }
        }
        ((RedBlackNode) root).setColour(RedBlackNode.Colour.Black);
    }

    private void fillNodeWithNIL(RedBlackNode<T> node) {
        node.setRight(emptyNode);
        node.setLeft(emptyNode);
        node.setParent(emptyNode);
    }

    @Override
    public void insert(T value) {
        if (root.equals(emptyNode)) {
            root = new RedBlackNode<>(value);
            fillNodeWithNIL((RedBlackNode<T>) root);
        } else {
            RedBlackNode<T> element = new RedBlackNode<>(value);
            RedBlackNode<T> tempNode = (RedBlackNode<T>) root;
            RedBlackNode<T> parent = null;
            while (!tempNode.equals(emptyNode)) {
                parent = tempNode;
                if (element.getKey().compareTo(parent.getKey()) < 0) {
                    tempNode = (RedBlackNode<T>) tempNode.getLeft();
                } else {
                    tempNode = (RedBlackNode<T>) tempNode.getRight();
                }
            }
            element.setParent(parent);
            if (element.getKey().compareTo(parent.getKey()) < 0) {
                parent.setLeft(element);
            } else {
                parent.setRight(element);
            }
            element.setLeft(emptyNode);
            element.setRight(emptyNode);
            if (!element.equals(emptyNode)) {
                element.setColour(RedBlackNode.Colour.Red);
                balanceAfterInsert(element);
            }
        }
    }

    @Override
    protected void Transplant(Node<T> oldNode, Node<T> newNode) {
        if (!oldNode.equals(emptyNode)) {
            if (oldNode.getParent().equals(emptyNode)) {
                root = newNode;
            } else if (oldNode.equals(oldNode.getParent().getLeft())) {
                oldNode.getParent().setLeft(newNode);
            } else {
                oldNode.getParent().setRight(newNode);
            }
        }
        newNode.setParent(oldNode.getParent());
    }

    @Override
    public void delete(T value) {
        RedBlackNode<T> node = (RedBlackNode<T>) search(root, value);
        if (node.equals(emptyNode)) {
            throw new NullPointerException("value wasn't found, nothing to delete");
        }
        RedBlackNode<T> replacedNode = node;
        RedBlackNode.Colour originColor = replacedNode.getColour();
        RedBlackNode<T> relativeNode;
        if (!node.hasLeftChild(emptyNode)) {
            relativeNode = (RedBlackNode<T>) node.getRight();
            Transplant(node, node.getRight());
        } else if (!node.hasRightChild(emptyNode)) {
            relativeNode = (RedBlackNode<T>) node.getLeft();
            Transplant(node, node.getLeft());
        } else {
            replacedNode = (RedBlackNode<T>) search(root, minValue(node.getRight().getKey()));
            originColor = replacedNode.getColour();
            relativeNode = (RedBlackNode<T>) replacedNode.getRight();
            if (replacedNode.getParent().equals(node)) {
                relativeNode.setParent(replacedNode);
            } else {
                Transplant(replacedNode, replacedNode.getRight());
                replacedNode.setRight(node.getRight());
                replacedNode.getRight().setParent(replacedNode);
            }

            Transplant(node, replacedNode);
            replacedNode.setLeft(node.getLeft());
            replacedNode.getLeft().setParent(replacedNode);
            replacedNode.setColour(node.getColour());
        }
        if (originColor == RedBlackNode.Colour.Black) {
            balanceAfterDelete(relativeNode);
        }
    }

    private void balanceAfterDelete(RedBlackNode<T> node) {
        while (!node.equals(root) && node.getColour() == RedBlackNode.Colour.Black) {
            if (node.equals(node.getParent().getLeft())) {
                RedBlackNode<T> brotherNode = (RedBlackNode<T>) node.getParent().getRight();
                if (brotherNode.getColour() == RedBlackNode.Colour.Red) {
                    brotherNode.setColour(RedBlackNode.Colour.Black);
                    ((RedBlackNode<T>) node.getParent()).setColour(RedBlackNode.Colour.Red);
                    leftRotate((RedBlackNode<T>) node.getParent());
                    brotherNode = (RedBlackNode<T>) node.getParent().getRight();
                }
                if (((RedBlackNode<T>) brotherNode.getLeft()).getColour() == RedBlackNode.Colour.Black
                        && ((RedBlackNode) brotherNode.getRight()).getColour() == RedBlackNode.Colour.Black) {
                    brotherNode.setColour(RedBlackNode.Colour.Red);
                    node = (RedBlackNode<T>) node.getParent();
                } else if (((RedBlackNode) brotherNode.getRight()).getColour() == RedBlackNode.Colour.Black) {
                    ((RedBlackNode) brotherNode.getLeft()).setColour(RedBlackNode.Colour.Black);
                    brotherNode.setColour(RedBlackNode.Colour.Red);
                    rightRotate(brotherNode);
                } else {
                    brotherNode.setColour(((RedBlackNode) node.getParent()).getColour());
                    ((RedBlackNode) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    ((RedBlackNode) brotherNode.getRight()).setColour(RedBlackNode.Colour.Black);
                    leftRotate((RedBlackNode<T>) node.getParent());
                    node = (RedBlackNode<T>) root;
                }

            } else {
                RedBlackNode<T> brotherNode = (RedBlackNode<T>) node.getParent().getLeft();
                if (brotherNode.getColour() == RedBlackNode.Colour.Red) {
                    brotherNode.setColour(RedBlackNode.Colour.Black);
                    ((RedBlackNode<T>) node.getParent()).setColour(RedBlackNode.Colour.Red);
                    rightRotate((RedBlackNode<T>) node.getParent());
                    brotherNode = (RedBlackNode<T>) node.getParent().getLeft();
                }
                if (((RedBlackNode<T>) brotherNode.getRight()).getColour() == RedBlackNode.Colour.Black
                        && ((RedBlackNode<T>) brotherNode.getLeft()).getColour() == RedBlackNode.Colour.Black) {
                    brotherNode.setColour(RedBlackNode.Colour.Red);
                    node = (RedBlackNode<T>) node.getParent();
                } else if (((RedBlackNode) brotherNode.getLeft()).getColour() == RedBlackNode.Colour.Black) {
                    ((RedBlackNode) brotherNode.getRight()).setColour(RedBlackNode.Colour.Black);
                    brotherNode.setColour(RedBlackNode.Colour.Red);
                    leftRotate(brotherNode);
                } else {

                    brotherNode.setColour(((RedBlackNode<T>) node.getParent()).getColour());
                    ((RedBlackNode<T>) node.getParent()).setColour(RedBlackNode.Colour.Black);
                    ((RedBlackNode<T>) brotherNode.getLeft()).setColour(RedBlackNode.Colour.Black);
                    rightRotate((RedBlackNode<T>) node.getParent());
                    node = (RedBlackNode<T>) root;
                }


            }
        }
        node.setColour(RedBlackNode.Colour.Black);
    }


    @Override
    public T minValue(T key) {
        try {
            Node<T> node = search(root, key);
            if (node == null) {
                return null;
            }
            while (!node.getLeft().equals(emptyNode)) {
                node = node.getLeft();
            }
            return node.getKey();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public T maxValue(T key) throws NullPointerException{
        Node<T> node = search(root, key);
        if (node == null) {
            throw new NullPointerException("node  == null");
        }

        while (!node.getRight().equals(emptyNode)) {
            node = node.getRight();
        }
        return node.getKey();
    }
}

