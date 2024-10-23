/********************************************************************
 * @file: BST.java
 * @description: This program implements the BST and BSTIterator class which allows the program to create a BST and interate through the BST recursively
 * @author: June Bernstein
 * @date: October 23, 2024
 ******************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.LinkedList;

public class BST<T extends Comparable<T>> implements Iterable<Node <T>> {
    private Node<T> root; //root of BST
    private int nodecount; //size of the tree

    // Implements the constructor
    public BST() {
        root = null;
        nodecount = 0;
    }

    // Implement the clear method - reinitializes tree
    public void clear() {
        root = null;
        nodecount = 0;
    }

    // Implement the size method, returns the size
    public int size() {
        return nodecount;
    }

    // Implement the insert method, inserts new node
    public void insert(T t) {
        root = inserthelp(root, t);
        nodecount++;
    }

    // Implement the remove method, removes nodes
    public T remove(T key) {
        T temp = findhelp(root, key); //find it
        if (temp != null) {
            root = removehelp(root, key); //now remove it
            nodecount--;
        }
        return temp;
    }

    // Implement the search method, searches for node, calls findhelp
    public T search(T key) {
        return findhelp(root, key);
    }

    // findhelp method, recursive method for searching for a specific node
    private T findhelp(Node<T> root, T key) {
        if (root == null) {
            return null;
        }
        if (root.value().compareTo(key) > 0) {
            return findhelp(root.left(), key);
        } else if (root.value().compareTo(key) == 0) {
            return root.value();
        } else {
            return findhelp(root.right(), key);
        }
    }

    // removehelp method, recursive method used to remove a specific node
    private Node<T> removehelp(Node<T> root, T key) {
        if (root == null) {
            return null;
        }
        if (root.value().compareTo(key) > 0) {
            root.setLeft(removehelp(root.left(), key));
        } else if (root.value().compareTo(key) < 0) {
            root.setRight(removehelp(root.right(), key));
        } else {
            if (root.left() == null) {
                return root.right();
            } else if (root.right() == null) {
                return root.left();
            } else {
                Node<T> temp = getMax(root.left());
                root.setElement(temp.value());
                root.setLeft(deleteMax(root.left()));
            }
        }
        return root;
    }

    //getMax method find the max of the BST
    private Node<T> getMax(Node<T> root) {
        if (root.right() == null) {
            return root;
        }
        return getMax(root.right());
    }

    //deletes the larger/max node
    private Node<T> deleteMax(Node<T> root) {
        if (root.right() == null) {
            return root.left();
        }
        root.setRight(deleteMax(root.right()));
        return root;
    }

    // inserthelp method, inserts node in its necessary place
    private Node<T> inserthelp(Node<T> root, T key) {
        if (root == null) {
            return new Node<T>(key);
        }
        if (root.value().compareTo(key) > 0) {
            root.setLeft(inserthelp(root.left(), key));
        }
        else {
            root.setRight(inserthelp(root.right(), key));
        }
        return root;
    }

    // Implements the iterator method
    @Override
    public Iterator<Node<T>> iterator() {
        return new BSTIterator(this.root);
    }

    // Implement the BSTIterator class
    public class BSTIterator implements Iterator<Node<T>> {
        private LinkedList<Node<T>> nodelist1 = new LinkedList<>();

        //constructor
        private BSTIterator(Node<T> root) {
            this.generate(nodelist1, root);
        }

        //generates a LinkedList for the BST iterations
        private void generate(LinkedList<Node<T>> nodelist1, Node<T> root) {
            if (root != null) {
                generate(nodelist1, root.left());
                nodelist1.add(root);
                generate(nodelist1, root.right());
            }
        }

        //returns whether there is a next node
        public boolean hasNext() {
            return !this.nodelist1.isEmpty();
        }

        //iterates to next node
        public Node<T> next() {
            return nodelist1.removeFirst();
        }
    }
}