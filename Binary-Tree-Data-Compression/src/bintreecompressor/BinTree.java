package bintreecompressor;

import java.util.ArrayList;

public class BinTree {
    private TNode root;

    public BinTree(String[] a) throws IllegalArgumentException {
        /*
         *  constructs a BinTree that represents the prefix-free code stored in the String array a. 
            Each String in the array a is a binary sequence (contains only 0’s and 1’s) representing
            a codeword. The codeword stored in a[i] corresponds to the alphabet symbol ci.
            Thus, the tree leaf corresponding to this codeword must store the String: "c"
            + i. If array a contains two identical codewords, or a codeword that is a pre-
            fix of another one, then the set of codewords is not prefix-free and the constructor
            should throw an IllegalArgumentException with the message “Prefix condition
            violated!”. If any element in the array is not a binary string, the method should
            throw an IllegalArgumentException with the message ”Invalid argument!”.
         */
        if (a.length == 0) { // empty array
            throw new IllegalArgumentException("Invalid argument!");
        }

        root = new TNode(null, null, null);
        for (int i = 0; i < a.length; i++) {
            // if (!a[i].matches("[01]+")) { // regex for binary string (commented due to faster check implemented)
            //     throw new IllegalArgumentException("Invalid argument!");
            // }
            if (a[i].length() == 0) { // empty string
                throw new IllegalArgumentException("Invalid argument!");
            }
            TNode current = root;
            for (int j = 0; j < a[i].length(); j++) {
                if (current != null && current.data != null) { // non leaf node with data
                    throw new IllegalArgumentException("Prefix condition violated!");
                }
                if (a[i].charAt(j) == '0') {
                    if (current.left == null) { // create new node if null, else traverse
                        current.left = new TNode(null, null, null);
                    }
                    current = current.left;
                } else if (a[i].charAt(j) == '1') {  
                    if (current.right == null) { // create new node if null, else traverse
                        current.right = new TNode(null, null, null);
                    }
                    current = current.right;
                }
                else { // invalid character
                    throw new IllegalArgumentException("Invalid argument!");
                }
            }
            if (current.data != null || current.left != null || current.right != null) {
                // child exists: path has been traversed before (prefix), if current node has data: duplicate
                throw new IllegalArgumentException("Prefix condition violated!");
            }
            current.data = "c" + i;
        }
    }

    public void optimize() {
        /* If the tree is not a full binary tree (i.e., where each
        internal node has two children), the lengths of some codewords can be reduced by
        removing some bits, while the prefix-free condition is maintained. This method
        checks if the tree is full and if it is not, it performs these changes (i.e., it reduced
        codewords and adapts the tree) until the tree becomes full. */
        return;
    }

    public ArrayList<String> getCodewords() {
        /* returns an ArrayList<String>
        object that stores the codewords in lexicographical order1. Each item in the list is
        a codeword (a string of 0’s and 1’s). */
        ArrayList<String> codewords = new ArrayList<String>();
        return codewords; // TODO 

    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(TNode t) {
        if (t != null) {
            printTree(t.left);
            if (t.data == null) {
                System.out.print("I ");
            } else {
                System.out.print(t.data + " ");
            }
            printTree(t.right);
        }
    }

}
