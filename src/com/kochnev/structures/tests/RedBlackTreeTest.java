package com.kochnev.structures.tests;

import com.kochnev.structures.trees.RedBlackTree;
import com.kochnev.structures.trees.absclasses.AbsTree;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.CORBA.Object;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class RedBlackTreeTest {

    private AbsTree<Integer> tree;

    public RedBlackTreeTest() {
        tree = new RedBlackTree<>();
        tree.insert(9);
        tree.insert(11);
        tree.insert(13);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(18);
        tree.insert(19);
        tree.insert(21);
        tree.insert(30);
        tree.insert(23);
        tree.insert(24);
        tree.insert(25);
        tree.insert(29);
        tree.insert(33);
    }

    @Test
    public void insert() throws Exception {
        assertNotEquals("insert test", 9, (int) tree.getRoot().getKey());
    }

    @Test
    public void delete() throws Exception {
        System.out.println(tree.getRoot().getKey());
        System.out.println();
        tree.delete(33);
        tree.delete(24);
        tree.delete(9);
        tree.delete(17);
        java.lang.Object val = tree.search(17);
        assertNull("delete element", tree.search(17).getKey());
        assertNull("delete element", tree.search(33).getKey());
        assertNull("delete element", tree.search(24).getKey());
        assertNull("delete element", tree.search(9).getKey());
        assertEquals("delete element", 19, (int) tree.getRoot().getKey());
        tree.treeCentralWalk();
    }
}