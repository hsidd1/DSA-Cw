package bintreecompressor;

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

        if (isPrefixorEqual(a)) { // TODO: abolish this and check during construction
            throw new IllegalArgumentException("Prefix condition violated!");
        }

        root = new TNode(null, null, null);
        for (int i = 0; i < a.length; i++) {
            if (!a[i].matches("[01]+")) { // regex for binary string
                throw new IllegalArgumentException("Invalid argument!");
            }
            TNode current = root;
            for (int j = 0; j < a[i].length(); j++) {
                if (a[i].charAt(j) == '0') {
                    if (current.left == null) { // create new node if null, else traverse
                        current.left = new TNode(null, null, null);
                    }
                    current = current.left;
                } else {  // a[i].charAt(j) == '1'
                    if (current.right == null) { // create new node if null, else traverse
                        current.right = new TNode(null, null, null);
                    }
                    current = current.right;
                }
            }
            if (current.data != null) { // prefix condition violated
                throw new IllegalArgumentException("Prefix condition violated!");
            }
            current.data = "c" + i;
        }
    }

    private Boolean isPrefixorEqual(String[] a) {
        /*
         * returns true if any element in the array is a prefix of another element, false otherwise.
         */
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length && j != i; j++) {
                if (a[i].startsWith(a[j])) {
                    return true;
                }
            }
        }
        return false;
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
