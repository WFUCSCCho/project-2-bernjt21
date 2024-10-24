/********************************************************************
 * @file: Node.java
 * @description: This program implements the Node class which creates, gets, and sets nodes for the binary search tree
 * @author: June Bernstein
 * @date: October 23, 2024
 ******************************************************************/
public class Node<T extends Comparable<T>> {
    private T element; // element
    private Node<T> left; //left child
    private Node<T> right; //right child

    // Implement the constructor
    public Node() {
        left = right = null;
    }

    public Node(T val) {
        left = right = null;
        element = val;
    }

    public Node(T val, Node<T> l, Node<T> r) {
        left = l;
        right = r;
        element = val;
    }

    // setElement method - setting the element as val
    public void setElement(T val) {
        element = val;
    }

    // setLeft method - sets the left node
    public void setLeft(Node<T> l) {
        left = l;
    }

    // setRight method - sets the right node
    public void setRight(Node<T> r) {
        right = r;
    }

    // getLeft method - getter method, returns left node
    public Node<T> left() {
        return left;
    }

    // getRight method - getter method, returns right node
    public Node<T> right() {
        return right;
    }

    // getElement method - getter method, returns the element
    public T value() {
        return element;
    }

    // isLeaf method returns true if node is a leaf
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

}

