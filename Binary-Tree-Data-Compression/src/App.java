import bintreecompressor.BinTree;

public class App {
    public static void main(String[] args) throws Exception {
        TestConstructor();
    }

    private static void TestConstructor() {
        String[] a = {"0", "10", "11"};
        BinTree tree = new BinTree(a);
        tree.printTree();
        System.out.println();
        String[] a2 = {"11", "10", "111"};
        BinTree tree3 = new BinTree(a2);
        tree3.printTree();
        System.out.println();
        String[] b = {"0", "10", "011"};
        BinTree tree2 = new BinTree(b);
        tree2.printTree();
    }
}
