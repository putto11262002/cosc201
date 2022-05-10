package cosc201.week0709;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

/**
 * A basic binary search tree for extending in lab 7.
 *
 * @author Michael Albert
 */
public class BST {

    private static final String PADDING = " ".repeat(4); // Used in toString
    private static final boolean RIGHT = false;
    private static final boolean LEFT = true;
    private Node root = null;

    public BST() {};

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Determines whether a string is stored in this tree.
     * @param s The string
     * @return true, if s occurs in the tree.
     */
    public boolean contains(String s) {
        return findNode(s, root) != null;
    }

    /**
     * Adds (if not already present) a string to this tree.
     * @param s the string to add
     * @return true, if s was added, false if s was already present
     */
    public boolean add(String s) {
        if (isEmpty()) {
            root = new Node(s, 0, 0);
            return true;
        }
        Node parent = null, child = this.root;
        while (child != null) {
            if (child.key.equals(s)) return false;
            parent = child;
            parent.size++;
            if (parent.key.compareTo(s) < 0) {

                child = parent.right;
            } else {

                child = parent.left;
            }

        }
        addLink(parent, new Node(s, 0, 0));

        int newHeight = 1;
        Node c = parent;
        while(c != null){
            if(newHeight > c.height) c.height++;
            c = c.parent;
            newHeight++;
        }
        return true;
    }

    /**
     * Delete a string from the BST if it's present.
     * @param s the string to be deleted
     * @return true if s was deleted, false if s was not in the tree
     */
    public boolean delete(String s) {
        Node n = findNode(s, root);
        if (n == null) return false;
        delete(n);
        return true;
    }

    /**
     *
     * @param s the string in which its size will be returned
     * @return return the size of the subtree rooted at s, if s does not exist return 0
     */
    public int size(String s){
        Node n = findNode(s, root);
        if(n == null) return 0;
        return n.size;
    }

    /**
     *
     * @param s the string in which its height will be returned
     * @return return the height of s, if s does not exist return -1
     */
    public int height(String s){
        Node n = findNode(s, root);
        if(n == null) return -1;
        return n.height;
    }

    /**
     *
     * @param s string to get the proceeding string in alphabetical order
     * @return s's next string in alphabetical order
     */

    public String next(String s){
        Node n = findNode(s, root);
        Node sn = successor(n);
        return sn.key;
    }

//    public String next(String s){
//        ArrayList<String> sortedArr = inorder();
//        for(int i = 0; i < sortedArr.size() - 1; i++){
//            if(sortedArr.get(i).compareTo(s) == 0){
//                if(i + 1 < sortedArr.size()){
//                    return sortedArr.get(i + 1);
//                }
//            }
//        }
//        return null;
//
//    }

    /**
     *
     * @param dictionary an array of string used to construct the balance BST
     * @return a balanced BST
     */
    public BST makeBalance(String[] dictionary){
        Arrays.sort(dictionary);
        BST balanced = new BST();
        return construct(balanced, dictionary, 0, dictionary.length - 1);
    }

    private BST construct(BST t, String[] a, int l, int r){
        if(l > r) return null;
        int mid = (l + r)/2;
        t.add(a[mid]);
        construct(t, a, l, mid - 1);
        construct(t, a, mid + 1, r);
        return t;
    }

    /**
     * Preorder traverse the BST. This is for illustrative purposes only,
     * traversal is generally an idea used in an algorithm, not a method
     * per se.
     *
     * @return the strings stored in this tree in preorder
     */

    public ArrayList<String> preorder() {
        ArrayList<String> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    // Helper method for preorder traversal
    // Use r as working storage to preorder traverse the tree below n
    private void preorder(Node n, ArrayList<String> r) {
        if (n == null) return;
        r.add(n.key);
        preorder(n.left, r);
        preorder(n.right, r);
    }

    /**
     * Inorder traverse the BST. This is for illustrative purposes only,
     * traversal is generally an idea used in an algorithm, not a method
     * per se.
     *
     * @return the strings stored in this tree in order
     */

    public ArrayList<String> inorder() {
        ArrayList<String> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    // Helper method for inorder traversal
    // Use r as working storage to inorder traverse the tree below n
    private void inorder(Node n, ArrayList<String> r) {
        if (n == null) return;
        inorder(n.left, r);
        r.add(n.key);
        inorder(n.right, r);
    }

    /**
     * Postorder traverse the BST. This is for illustrative purposes only,
     * traversal is generally an idea used in an algorithm, not a method
     * per se.
     *
     * @return the strings stored in this tree in postorder
     */

    public ArrayList<String> postorder() {
        ArrayList<String> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    // Helper method for postorder traversal
    // Use r as working storage to postorder traverse the tree below n
    private void postorder(Node n, ArrayList<String> r) {
        if (n == null) return;
        postorder(n.left, r);
        postorder(n.right, r);
        r.add(n.key);
    }

    /**
     * Traverse the BST in level order, i.e., first the root, then all its
     * children, then all its grandchildren and so on. This is for illustrative
     * purposes only, traversal is generally an idea used in an algorithm, not
     * a method per se.
     *
     * @return the strings stored in this tree in level order
     */
    public ArrayList<String> levelorder() {
        ArrayList<String> result = new ArrayList<>();
        if (isEmpty()) return result;
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.remove();
            result.add(n.key);
            if (n.left  != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
        return result;
    }

    public String toString() {
        if (isEmpty()) return "<>";
        StringBuilder result = new StringBuilder();
        addString(root, result, "");
        return result.toString();
    }

    // Finds the node (if any) containing s below n - returns null if there is
    // no such node
    private Node findNode(String s, Node n) {
        if (n == null || n.key.equals(s)) return n;
        if (n.key.compareTo(s) < 0) {
            return findNode(s, n.right);
        } else {
            return findNode(s, n.left);
        }
    }

    // Delete a node
    // Assumption: n is not null
    private void delete(Node n) {
        if (n == root) {
            deleteRoot(); return;
        }
        if (n.left == null) {
            Node c = n.parent;


            while(c != null){

                int leftChildHeight = c.left == null ? -1 : c.left.height;
                int rightChildHeight = c.right == null ? -1 : c.right.height;


                if(linkType(c, n) == RIGHT && rightChildHeight > leftChildHeight) c.height--;
                if(linkType(c, n) == LEFT && leftChildHeight > rightChildHeight) c.height--;

                c.size--;
                c = c.parent;
            }
            addLink(n.parent, n.right, linkType(n.parent, n));
            return;
        }
        if (n.right == null) {
            Node c = n.parent;

            while(c != null){
                int leftChildHeight = c.left == null ? 0 : c.left.height;
                int rightChildHeight = c.right == null ? 0 : c.right.height;
                if(linkType(c, n) == RIGHT && rightChildHeight > leftChildHeight) c.height--;
                if(linkType(c, n) == LEFT && leftChildHeight > rightChildHeight) c.height--;

               c.size--;
               c = c.parent;
            }
            addLink(n.parent, n.left, linkType(n.parent, n));
            return;
        }
        Node sn = successor(n);
        String s = sn.key;
        delete(sn);
        n.key = s;


    }

    // Delete the root. This is a special case because the root's
    // parent doesn't exist.
    private void deleteRoot() {
        if (root.left == null) {
            root = root.right;
            root.parent = null;
            return;
        }

        if (root.right == null) {
            root = root.left;
            root.parent = null;
            return;
        }

        Node n = successor(root);
        String s = n.key;
        delete(n);
        root.key = s;
    }
    // Adds the string contained in a node together with a suitable padding
    // prefix to a StringBuilder -- used in toString()
    private void addString(Node n, StringBuilder result, String prefix) {
        if (n == null) return;
        addString(n.right, result, prefix + PADDING);
        result.append(prefix + n.key);
        //---
        result.append(" <");
        result.append(n.size);
        result.append(", ");
        result.append(n.height);
        result.append(">");
        // --
        result.append("\n");
        addString(n.left, result, prefix + PADDING);
    }

    // Finds the successor of a node, used in the 'difficult' delete case.
    // Assumption: n has a non-null right child.
    private Node successor(Node n) {
        Node result = n.right;
        while (result.left != null) {
            result = result.left;
        }
        return result;
    }

    // Determines the type of a parent-child link
    // Assumption: this is a parent-child pair and neither is null
    private boolean linkType(Node parent, Node child) {
        if (parent.key.compareTo(child.key) < 0) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }

    // Links a parent and child node
    // Assumption: neither node is null or has a null key
    //             the two keys are different
    private void addLink(Node parent, Node child) {
        child.parent = parent;
        if (child.key.compareTo(parent.key)< 0) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // Links a parent and child node in the indicated direction
    // Assumption: parent is not null (child might be)
    private void addLink(Node parent, Node child, boolean type) {
        if (type == RIGHT) {
            parent.right = child;
        } else {
            parent.left = child;
        }
        if (child != null) child.parent = parent;
    }

    private class Node {
        Node parent = null;
        Node left = null;
        Node right = null;
        String key;
        int size;
        int height;

        Node(String key, int size, int height) {
            this.key = key;
            this.size = size;
            this.height = height;
        }

    }

}