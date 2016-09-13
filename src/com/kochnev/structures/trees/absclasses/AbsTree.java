package com.kochnev.structures.trees.absclasses;

import com.kochnev.structures.simplestructures.Queue;
import com.kochnev.structures.simplestructures.Stack;
import com.kochnev.structures.trees.nodes.Node;

public abstract class AbsTree<T extends Comparable<T>> {

    protected Node<T> root;

    public Node dfs(T key) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node result = null;
        while (!stack.isEmpty) {
            Node node = stack.pop();
            if (node.getKey() == key) {
                result = node;
                break;
            } else {
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            }
        }
        return result;
    }

    public Node bfs(T key) {
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        Node result = null;
        while (!queue.isEmpty) {
            Node node = queue.dequeue();
            if (node.getKey() == key) {
                result = node;
                break;
            } else {
                if (node.getLeft() != null) {
                    queue.enqueue(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.enqueue(node.getRight());
                }
            }
        }
        return result;
    }

    public T minValue(T key) {
        try {
            Node<T> node = search(root, key);
            if (node == null) {
                return null;
            }
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
            return node.getKey();
        } catch (Exception ex) {
            return null;
        }
    }

    public T maxValue(T key) throws NullPointerException {
        Node<T> node = search(root, key);
        if (node == null) {
            throw new NullPointerException("node  == null");
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getKey();
    }

    /**
     * Work only if all elements are unique
     */
    public void treeCentralWalk() {
        Node<T> node = search(root.getKey());
        if (node != null) {
            T child;
            child = node.getLeft() != null ? node.getLeft().getKey() : null;
            if (child != null) {
                elementCentralWalk(child);
            }
            System.out.println(node.getKey());
            child = node.getRight() != null ? node.getRight().getKey() : null;
            if (child != null) {
                elementCentralWalk(child);
            }
        }
    }


    /**
     * Work only if all elements are unique
     * @param key
     */
    public void elementCentralWalk(T key) {
        Node<T> node = search(root, key);
        if (node != null) {
            T child;
            child = node.getLeft() != null ? node.getLeft().getKey() : null;
            if (child != null) {
                elementCentralWalk(child);
            }
            System.out.println(node.getKey());
            child = node.getRight() != null ? node.getRight().getKey() : null;
            if (child != null) {
                elementCentralWalk(child);
            }
        }
    }

    /**
     * if you have several un unique elements this method will return the first one
     * @param key
     * @return next element after key
     */
    public T successor(T key) {
        Node<T> node = search(root, key);
        if (node != null) {
            if (node.getKey() == maxValue(root.getKey())) {
                return null;
            }
            if (node.getRight() != null) {
                return minValue(node.getRight().getKey());
            }

            Node<T> successor = null;
            if (node.getParent() != null) {

                successor = node.getParent();
            } else {
                return null;
            }
            if (successor.getRight() != null) {
                while (successor != null && node.getKey() == successor.getRight().getKey()) {
                    Node<T> tmp = successor;
                    node = successor;
                    successor = successor.getParent();
                    if (successor.getRight() == null) {
                        break;
                    }
                }
            }

            return successor.getKey();
        } else {
            return null;
        }
    }


    /**
     * if you have several un unique elements this method will return the first one
     * @param key
     * @return element before key
     */
    public T predecessor(T key) {
        Node<T> node = search(root, key);
        if (node != null) {
            if (node.getKey() == minValue(root.getKey())) {
                return null;
            }
            if (node.getLeft() != null) {
                return maxValue(node.getLeft().getKey());
            }
            Node<T> predessor = null;
            if (node.getParent() != null) {
                predessor = node.getParent();
            } else {
                return null;
            }
            if (predessor.getLeft() == null) {
                return predessor.getKey();
            }
            while (predessor != null && node.getKey() == predessor.getLeft().getKey()) {
                node = predessor;
                predessor = predessor.getParent();
                if (predessor.getLeft() == null) {
                    break;
                }
            }
            return predessor.getKey();
        } else {
            return null;
        }

    }

    public Node<T> getRoot() {
        return root;
    }

    /**
     *
     * @param value to insert if the value is not unique it will bee added on the right side of the fisrt repeated element
     */
    public abstract void insert(T value);

    /**
     * delete the first matched object
     * @param value
     */
    public abstract void delete(T value);

    /**
     *
     * @param node root object from search will be started down to leafs
     * @param value which try to find
     * @return the first matched object
     */
    public abstract Node<T> search(Node<T> node, T value);

    /**
     *
     * @param value which try to find
     * @return the first matched object
     */
    public abstract Node<T> search(T value);

}
