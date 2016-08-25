package com.kochnev.structures.tests;

import com.kochnev.structures.trees.BinaryTree;
import com.kochnev.structures.trees.nodes.Node;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    public BinaryTreeTest() {
        tree = new BinaryTree<>();
        tree.insert(6);
        tree.insert(3);
        tree.insert(9);
        tree.insert(7);
        tree.insert(21);
        tree.insert(12);
        tree.insert(13);
        tree.insert(96);

        tree.insert(71);

    }

    @Test
    public void insert() throws Exception {
        assertEquals("root element", (int) tree.getRoot().getKey(), 6);
        assertEquals("right element", (int) tree.search(7).getKey(), 7);
        assertEquals("left element", (int) tree.search(3).getKey(), 3);

    }

    @Test
    public void bfsSearch() {
        tree.delete(6);
        assertNull("bfs", tree.bfs(6));
        assertEquals("bfs", tree.bfs(3).getKey(), 3);

    }

    @Test
    public void dfsSearch() {
        tree.delete(13);
        assertNull("bfs", tree.dfs(13));
        assertEquals("bfs", tree.dfs(3).getKey(), 3);

    }

    @Test
    public void delete() throws Exception {
        tree.delete(6);
        Node element = tree.search(6);
        assertEquals("delete element", null, element);

        tree.delete(96);
        element = tree.search(96);
        assertEquals("delete element", null, element);

        tree.delete(21);
        element = tree.search(21);
        assertEquals("delete element", null, element);

        tree.delete(13);
        element = tree.search(13);
        assertEquals("delete element", null, element);

        tree.delete(71);
        element = tree.search(71);
        assertEquals("delete element", null, element);

        tree.elementCentralWalk(tree.getRoot().getKey());

    }

    @Test
    public void successor(){
        assertEquals("successor element", 7, (int) tree.successor(6));
    }

    @Test
    public void predecessor(){
        assertEquals("predecessor element", 3, (int) tree.predecessor(6));
    }

}