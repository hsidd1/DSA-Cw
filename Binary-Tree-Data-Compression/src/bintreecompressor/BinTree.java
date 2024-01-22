package bintreecompressor;

import java.util.ArrayList;

import org.w3c.dom.Node;

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
        root = optimizeTraversal(root);
        if (root.data != null) {
            root.data = null;
            root.left = new TNode("0", null, null);
        }
    }

    private TNode optimizeTraversal(TNode current) {
        if (current == null) {
            return null;
        }
        
        current.left = optimizeTraversal(current.left);
        current.right = optimizeTraversal(current.right);
        
        if (current.left == null && current.right != null) {
            //current.data = current.right.data;
            return current.right;
        }

        if (current.left != null && current.right == null) {
            //current.data = current.left.data;
            return current.left;
        }

        return current;
    }
        

    public ArrayList<String> getCodewords() {
        /* returns an ArrayList<String> object that stores the codewords in lexicographical order1. 
        Each item in the list is a codeword (a string of 0’s and 1’s). */
        ArrayList<String> codeWordsArray = new ArrayList<String>();
        lexicographicalTraversal(root, "", codeWordsArray);
        return codeWordsArray;
    }

    private void lexicographicalTraversal(TNode current, String codeWordString, ArrayList<String> codeWordsArray) {
        /* inorder lexicographical traversal modifying array of codewords from input tree root
         * Wrapped by getCodewords()
         */
        if (current != null) {
            // Left, Root, Right
            lexicographicalTraversal(current.left, codeWordString + "0", codeWordsArray); // 0s for left

            if (current.data != null) { // add codeword if leaf
                codeWordsArray.add(codeWordString);
            }

            lexicographicalTraversal(current.right, codeWordString + "1", codeWordsArray); // 1s for right
        }
    }

    // public int getTreeSize() {
    //     /* getter for treeSize (number of nodes in the tree) */
    //     return treeSize(root);
    // }
    // private int treeSize(TNode current) {
    //     /* returns the number of nodes in the tree. */
    //     if (current == null) {
    //         return 0;
    //     }
    //     return 1 + treeSize(current.left) + treeSize(current.right);
    // }

    private int treeHeight(TNode current) {
        /* returns the height of the tree. */
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(treeHeight(current.left), treeHeight(current.right));
    }

    public int getTreeHeight() {
        /* getter for treeHeight (height of the tree) */
        return treeHeight(root);
    }

    public String[] toArray() {
        /* returns an array representation of the binary tree.
        You have to use the convention given in COMP ENG 2SI3 for the representation of
        binary trees using arrays. The first array element stores null. Any array element
        corresponding to a missing tree node stores null. Any array element corresponding
        to an internal node stores "I". Any array element corresponding to a leaf stores
        the string corresponding to that leaf. */
        int size = (int) Math.pow(2, treeHeight(root)); // size of array is 2^height
        String[] BinTreeArray = new String[size];
        BinTreeArray[0] = null;
        toArrayTraversal(root, BinTreeArray, 1); // 0th element null
        return BinTreeArray;
    }

    private void toArrayTraversal(TNode current, String[] BinTreeArray, int index) {
        /* level order traversal modifying array of tree from input tree root
         * Wrapped by toArray()
         */
        if (current != null) {
            if (current.data == null) { // internal node
                BinTreeArray[index] = "I";

                toArrayTraversal(current.left, BinTreeArray, 2*index);
                toArrayTraversal(current.right, BinTreeArray, 2*index + 1);
            }
            else { // leaf node
                BinTreeArray[index] = current.data;
            }
        }
    }

    public String encode(ArrayList<String> a) {
        /* The input list a represents a
        sequence of alphabet symbols. Each list item is a String object representing a
        symbol, i.e., a string consisting of letter ’c’ followed by a number that is at most
        equal to n-1, where n is the number of leaf nodes. This method encodes the input
        and outputs the corresponding bitstream. You may assume that each string in the
        list is a valid alphabet symbol. */

        if (a.size() == 0) { // empty array
            throw new IllegalArgumentException("Invalid argument!");
        }
        
        String encodedString = "";
        for (String symbol : a) {
            String nextSymbol = encodedSymbol(root, symbol, "");
            if (nextSymbol == null) {
                throw new IllegalArgumentException("Invalid argument!");
            }
            encodedString += nextSymbol;
        }
        return encodedString;
    }

    private String encodedSymbol(TNode current, String symbol, String codeWordString) {
        /* inorder traversal modifying array of codewords from input tree root
         * Wrapped by encode()
         */
        if (current == null) return null;

        if (current.data != null) { // add codeword if leaf
            if (current.data.equals(symbol)) {
                return codeWordString;
            }
        }

        String ret = encodedSymbol(current.left, symbol, codeWordString + "0");
        if (ret != null) return ret;


        String ret2 = encodedSymbol(current.right, symbol, codeWordString + "1");
        if (ret2 != null) return ret2;

        return null;
    }

    // private int strToInt(String str) {
    //     if (str == null || str.length() == 0) {
    //         throw new IllegalArgumentException("Invalid argument!");
    //     }
    //     // make sure its actually a number 
    //     for (int i = 0; i < str.length(); i++) {
    //         if (str.charAt(i) < '0' || str.charAt(i) > '9') { // not a number
    //             throw new IllegalArgumentException("Invalid argument!");
    //         }
    //     }
    //     int i = 0;
    //     int num = 0;
    //     // boolean isNeg = false;

    //     if (str.charAt(0) == '-') {
    //         throw new IllegalArgumentException("Invalid argument!");
    //     }

    //     // Process each character of the string;
    //     while (i < str.length()) {
    //         num *= 10;
    //         num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
    //     }

    //     // if (isNeg)
    //     //     num = -num;

    //     return num;
    // }

    public ArrayList<String> decode(String s) throws IllegalArgumentException {
        // validation of input 
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid argument!");
        }

        TNode current = root;
        ArrayList<String> DecodedOutput = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (current.left == null) {
                    throw new IllegalArgumentException("Illegal Argument!");
                }
                current = current.left;
            }
            else if (s.charAt(i) == '1') {
                if (current.right == null) {
                    throw new IllegalArgumentException("Illegal Argument!");
                }
                current = current.right;
            }
            else { throw new IllegalArgumentException("Illegal Argument!"); }

            if (current.left == null && current.right == null) {
                DecodedOutput.add(current.data);
                current = root;
            }
        }
        if (current != root) {
            throw new IllegalArgumentException("Illegal Argument!");
        }
        return DecodedOutput;
    }

    public String toString() {
        /* - returns the string representation of the prefix-free
        code as the sequence of codewords in lexicographical order separated by empty
        spaces and ending with an empty space. For our example, this string is “0 10 110
        111 ”. */
        String BinTreeString = "";
        ArrayList<String> codeWordsArray = getCodewords();
        for (String codeWord : codeWordsArray) {
            BinTreeString += codeWord + " ";
        }
        return BinTreeString;
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
