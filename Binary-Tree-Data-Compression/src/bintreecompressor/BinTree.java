package bintreecompressor;

import java.util.ArrayList;

public class BinTree {
    private TNode root;

    public BinTree(String[] a) throws IllegalArgumentException {
        /*
        --------------------------------------------------------------------------------------------
        Time complexity: O(n x m): Iterates through all n elements in array a, and for each element, 
        iterates through all m characters in each string
        Space complexity: O(n x m): Creates a new node for all m characters in the codeword, 
        which is done for all n elements in array a
        --------------------------------------------------------------------------------------------
        Description: constructs a BinTree that represents the prefix-free code stored in the String array a. 
        Each String in the array a is a binary sequence (contains only 0’s and 1’s) representing
        a codeword. The codeword stored in a[i] corresponds to the alphabet symbol ci.
        Thus, the tree leaf corresponding to this codeword must store the String: "c"
        + i. If array a contains two identical codewords, or a codeword that is a pre-
        fix of another one, then the set of codewords is not prefix-free and the constructor
        should throw an IllegalArgumentException with the message “Prefix condition
        violated!”. If any element in the array is not a binary string, the method should
        throw an IllegalArgumentException with the message ”Invalid argument!”.
        --------------------------------------------------------------------------------------------
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
                if (a[i].charAt(j) == '0') { // 0s go left
                    if (current.left == null) { // create new node if null, else traverse
                        current.left = new TNode(null, null, null);
                    }
                    current = current.left;
                } else if (a[i].charAt(j) == '1') {  // 1s go right
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
            current.data = "c" + i; // set data to codeword
        }
    }

    public void optimize() {
        /* 
        --------------------------------------------------------------------------------------------
        Time complexity: O(n): Goes to all nodes when determining how to reassign nodes
        Space complexity: O(n): Creates n recursive calls for each node
        --------------------------------------------------------------------------------------------
        Description: If the tree is not a full binary tree (i.e., where each
        internal node has two children), the lengths of some codewords can be reduced by
        removing some bits, while the prefix-free condition is maintained. This method
        checks if the tree is full and if it is not, it performs these changes (i.e., it reduced
        codewords and adapts the tree) until the tree becomes full. 
        --------------------------------------------------------------------------------------------
        */
        root = optimizeTraversal(root);
        if (root.data != null) { 
            // case where we optimize all the way into the root and store codeword there (single codeword optimized)
            root.data = null;
            root.left = new TNode("0", null, null); // assign it arbitrary 0 so that it has a valid code
        }
    }

    private TNode optimizeTraversal(TNode current) {
        /* private method to optimize tree by reassigning nodes recursively if they do not have 2 children 
        or they are not leaf nodes, meaning bit/edge can be removed */
        if (current == null) {
            return null;
        }
        
        // Reassign left and right children recursively
        current.left = optimizeTraversal(current.left);
        current.right = optimizeTraversal(current.right);
        
        // checks for nodes that do not have two children:

        if (current.left == null && current.right != null) {
            // If only right child exists, return that child to shift it up
            return current.right;
        }
        if (current.left != null && current.right == null) {
            // If only left child exists, return that child to shift it up
            return current.left;
        }

        return current;
    }
        

    public ArrayList<String> getCodewords() {
        /* 
        --------------------------------------------------------------------------------------------
        Time complexity: O(n): Goes to all n nodes so that their codewords can be added to the array
        Space complexity: O(n): auxialiry complexity is n for n reursive calls for all n nodes worst case
        Note: Creates an array of size n for the codewords (each edge stored)
        --------------------------------------------------------------------------------------------
        Description: returns an ArrayList<String> object that stores the codewords in lexicographical order1. 
        Each item in the list is a codeword (a string of 0’s and 1’s).
        --------------------------------------------------------------------------------------------
        */
        ArrayList<String> codeWordsArray = new ArrayList<String>();
        lexicographicalTraversal(root, "", codeWordsArray);
        return codeWordsArray;
    }

    private void lexicographicalTraversal(TNode current, String codeWordString, ArrayList<String> codeWordsArray) {
        /* lexicographical traversal modifying array of codewords from input tree root, 
        left first for 0s, then right for 1s, to get lexicographical order with 0s before 1s.
         */
        if (current != null) {
            // Left, Visit, Right
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
        /* returns the height of the tree by recursively going to all nodes and returning 
        the max height of the left and right subtrees + 1 */
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(treeHeight(current.left), treeHeight(current.right)); // 1 + max of left and right subtrees
    }

    public int getTreeHeight() {
        /* getter for treeHeight (height of the tree) */
        return treeHeight(root);
    }

    public String[] toArray() {
        /* 
        --------------------------------------------------------------------------------------------
        Time complexity: O(n): Goes to all nodes so that their codewords can be added to the array
        Space complexity: O(n): Creates n recursive calls for each node auxiliary space worst case
        Note: Creates a string array of size 2^height
        --------------------------------------------------------------------------------------------
        Description: returns an array representation of the binary tree.
        You have to use the convention given in COMP ENG 2SI3 for the representation of
        binary trees using arrays. The first array element stores null. Any array element
        corresponding to a missing tree node stores null. Any array element corresponding
        to an internal node stores "I". Any array element corresponding to a leaf stores
        the string corresponding to that leaf. 
        --------------------------------------------------------------------------------------------
        */
        int size = (int) Math.pow(2, treeHeight(root)); // size of array is 2^height
        String[] BinTreeArray = new String[size];
        BinTreeArray[0] = null;
        toArrayTraversal(root, BinTreeArray, 1); // 0th element is null
        return BinTreeArray;
    }

    private void toArrayTraversal(TNode current, String[] BinTreeArray, int index) {
        /* traversal modifying array of tree from input tree root
         * Wrapped by toArray()
         */
        if (current != null) {
            if (current.data == null) { // internal node
                BinTreeArray[index] = "I"; // mark with I for internal node

                toArrayTraversal(current.left, BinTreeArray, 2*index); // left child stored at 2i
                toArrayTraversal(current.right, BinTreeArray, 2*index + 1); // right child stored at 2i + 1
            }
            else { // leaf node, add codeword
                BinTreeArray[index] = current.data;
            }
        }
    }

    public String encode(ArrayList<String> a) {
        /* 
        --------------------------------------------------------------------------------------------
        Time complexity: O(n x m): Goes through all n elements in a and for each element,
        calls encodedSymbol() which iterates through all m characters in each string
        Space complexity: O(n): auxialiry complexity is n for n reursive calls for all n elements in a
        Note: creates a string of length n for the encoded string
        --------------------------------------------------------------------------------------------
        Description: The input list a represents a sequence of alphabet symbols. Each list item is a String object 
        representing a symbol, i.e., a string consisting of letter ’c’ followed by a number that is at 
        most equal to n-1, where n is the number of leaf nodes. This method encodes the input and outputs 
        the corresponding bitstream. You may assume that each string in the list is a valid alphabet symbol. 
        --------------------------------------------------------------------------------------------
        */

        if (a.size() == 0) { // empty array
            throw new IllegalArgumentException("Invalid argument!");
        }
        
        String encodedString = ""; // output string
        for (String symbol : a) {
            // recursively find target symbol in tree and add codeword to output string
            String nextSymbol = encodedSymbol(root, symbol, "");
            if (nextSymbol == null) { // symbol not found, invalid input
                throw new IllegalArgumentException("Invalid argument!");
            }
            encodedString += nextSymbol;
        }
        return encodedString;
    }

    private String encodedSymbol(TNode current, String symbol, String codeWordString) {
        /* preorder traversal modifying array of codewords from input tree root
         * Wrapped by encode()
         */
        if (current == null) return null;

        if (current.data != null) { // add codeword if leaf and matches symbol
            if (current.data.equals(symbol)) {
                return codeWordString;
            }
        }

        String ret = encodedSymbol(current.left, symbol, codeWordString + "0"); // 0s for left
        if (ret != null) return ret;


        String ret2 = encodedSymbol(current.right, symbol, codeWordString + "1"); // 1s for right
        if (ret2 != null) return ret2;

        return null;
    }

    public ArrayList<String> decode(String s) throws IllegalArgumentException {
        /*
        --------------------------------------------------------------------------------------------
        Time complexity: O(n): Iterates through all n characters in string s
        Space complexity: O(n): n recursive calls for each character in string s worst case auxiliary space
        Note: Creates space for an array of length n for the decoded output
        --------------------------------------------------------------------------------------------
        Description: The input string s contains only 0’s and 1’s). This method outputs the sequence of
        alphabet symbols obtained by decoding the bit sequence s. Each alphabet symbol
        has to be stored as a separate item in the list. The order of items is important
        here. Assume that s is a concatenation of codewords, so it can be decoded. 
        The method throws an exception if the input stream is not binary or of it is binary, but
        cannot be parsed int a sequence of codewords.
        --------------------------------------------------------------------------------------------
        */

        // validation of input 
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid argument!");
        }

        TNode current = root;
        ArrayList<String> DecodedOutput = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) { // iterate through string
            if (s.charAt(i) == '0') { // 0s go left
                if (current.left == null) {
                    throw new IllegalArgumentException("Illegal Argument!");
                }
                current = current.left;
            }
            else if (s.charAt(i) == '1') { // 1s go right
                if (current.right == null) {
                    throw new IllegalArgumentException("Illegal Argument!");
                }
                current = current.right;
            }
            // invalid character
            else { throw new IllegalArgumentException("Illegal Argument!"); }

            if (current.left == null && current.right == null) { // leaf node
                DecodedOutput.add(current.data); // add codeword to output
                current = root;
            }
        }
        if (current != root) { // if we are not back at the root, we have not decoded all the way
            throw new IllegalArgumentException("Illegal Argument!");
        }
        return DecodedOutput;
    }

    public String toString() {
        /*
        --------------------------------------------------------------------------------------------
        Time complexity: O(n): Goes to all nodes so that their codewords can be added to the string
        Space complexity: O(n): Creates a string of length n for the encoded string
        Note: Creates a string of length n for the encoded string
        --------------------------------------------------------------------------------------------
        returns the string representation of the prefix-free
        code as the sequence of codewords in lexicographical order separated by empty
        spaces and ending with an empty space. For our example, this string is “0 10 110
        111 ”. */
        String BinTreeString = "";
        ArrayList<String> codeWordsArray = getCodewords();
        for (String codeWord : codeWordsArray) {
            BinTreeString += codeWord + " "; // add codewords to string separated by space
        }
        return BinTreeString;
    }

    public void printTree() {
        /* prints the tree in the format used in the assignment description. */
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
