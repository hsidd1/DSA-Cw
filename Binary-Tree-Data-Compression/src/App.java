import java.util.ArrayList;
import java.util.Arrays;

import bintreecompressor.BinTree;

public class App {
    public static void main(String[] args) throws Exception {
        Tests();
        Test_optimize();
}

    private static void Test_optimize() {
        // Test 1
    System.out.println("Optimize Test 1");
    String[] test_optimize = {"1"};
    BinTree test1 = new BinTree(test_optimize);
    System.out.println("Input codes: " + Arrays.toString(test_optimize));
    System.out.println("Pre Optimize: " + test1.toString());
    test1.optimize();
    System.out.println("Print tree: ");
    test1.printTree();
    System.out.println("\n");
    System.out.println("Output: " + test1.toString() + "\n");

    // Test 2
    System.out.println("Optimize Test 2");
    String[] test_optimize2 = {"011", "001", "010"};
    BinTree test2 = new BinTree(test_optimize2);
    System.out.println("Input codes: " + Arrays.toString(test_optimize2));
    System.out.println("Pre Optimize: " + test2.toString());
    test2.optimize();
    System.out.println("Output: " + test2.toString() + "\n");

    // Test 3
    System.out.println("Optimize Test 3");
    String[] test_optimize3 = {"011", "001"};
    BinTree test3 = new BinTree(test_optimize3);
    System.out.println("Input codes: " + Arrays.toString(test_optimize3));
    System.out.println("Pre Optimize: " + test3.toString());
    test3.optimize();
    System.out.println("Output: " + test3.toString());

    // Test 4
    System.out.println("Optimize Test 4");
    String[] test_optimize4 = {"01010110111010101010110101111"};
    BinTree test4 = new BinTree(test_optimize4);
    System.out.println("Input codes: " + Arrays.toString(test_optimize4));
    System.out.println("Pre Optimize: " + test4.toString());
    test4.optimize();
    System.out.println("Output: " + test4.toString());

    // Test 5
    System.out.println("Optimize Test 5");
    String[] test_optimize5 = {"01010110111010101010110101111", "1", "011"};
    BinTree test5 = new BinTree(test_optimize5);
    System.out.println("Input codes: " + Arrays.toString(test_optimize5));
    System.out.println("Pre Optimize: " + test5.toString());
    test5.optimize();
    System.out.println("Output: " + test5.toString());
    }

    private static void Test_decode() {
        String[] test_encode = {"110", "0", "111", "10"};
        ArrayList<String> decoded = new ArrayList<>(Arrays.asList(new String[]{"c0", "c1", "c1", "c3", "c3"}));
        String expected_encode = "111001010";
        BinTree test = new BinTree(test_encode);
        try {
            String encoded = test.encode(decoded);
            System.out.println("Output: " + encoded);
            System.out.println("Expect: " + expected_encode);
            if (encoded.equals(expected_encode)) {
                System.out.println("Correct Encoding");
            } else {
                System.out.println("Failed Encoding");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void Tests() {
        double FinalMarks=0;
        
        //data for testing constructor-convert:
        //1) Valid, sorted
        String[] d1={"0", "10", "11"};
        String[] d2={"0", "10", "110", "111"};
        String[] d3={"0", "100", "101"};
        String[] d4={"0", "101", "1000", "1001"};
        String[] d5={"00","010", "011","100","101","11100","11101", "11111"};
        String[] d6={"0110"};
        
        //2) Valid, unsorted
        String[] d7={"1000", "0", "1001", "101"}; //unsorted d4
        String[] d8={"101","100","00", "11111", "011","11100","010","11101"};//unsorted d5
        String[] d9={"110","0", "111", "10"};//unsorted d2
        
        //3)InValid
        //3.1: Not binary. "Invalid argument!" expected
        String[] d10={"0", "109", "11b"}; //sorted
        String[] d11={"1b00", "11", "1081", "a01"}; //not sorted
        
        //3.2: Duplicate codeword. “Prefix condition violated!” expected
        String[] d12={"1000", "0", "1000", "101"};
        String[] d13={"00","010", "011","100","101", "11100", "11101", "11100", "11111"};
        
        //3.3: Not a prefix-free. “Prefix condition violated!” expected
        String[] d14={"0", "10", "11","01"};
        String[] d15={"0", "10", "100", "111"};
        String[] d16={"101","100","00", "11111", "011","11100","111"};
        String[] d17={"11111", "101","100","11100","00","1110", "011","111"};
        
        //data for testing encode and decode:
        //1) Valid sequence
        ArrayList<String> enc1= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c0","c2","c2","c0"} ));
        String dec1 = "111010011110";
        
        ArrayList<String> enc4= new ArrayList<>(Arrays.asList( new String[]{"c2","c3","c3", "c1", "c1","c0","c0","c0","c2","c2","c0","c3"} ));
        String dec4 = "1000100110011011010001000100001001";
        
        //d8={"101","100","00", "11111", "011","11100","010","11101"}
        ArrayList<String> enc8= new ArrayList<>(Arrays.asList( new String[]{"c2","c7","c0","c2","c6","c6", "c1", "c1","c5","c4","c3","c3","c0","c2","c2","c0","c5"} ));
        String dec8 = "001110110100010010100100111000111111111111101000010111100";
        
        
        //d9={"110","0", "111", "10"}
        ArrayList<String> enc9= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c3","c3","c0","c2","c2","c0","c3"} ));
        String dec9 = "11100101011011111111010";
        
        
        //2) inValid sequence
        //d3={"0", "100", "101"};
        ArrayList<String> ienc3= new ArrayList<>(Arrays.asList( new String[]{"c2", "c3", "c1","c4","c3","c0","c2","c2","c0","c3"} ));//c4 does not exist
        String idec3 = "111110100101";
        
        //d9={"110","0", "111", "10"}
        ArrayList<String> ienc9= new ArrayList<>(Arrays.asList( new String[]{"c2", "c1", "c1","c4","c3","c0","c2","c2","c0","c3"} ));//c4 does not exist
        String idec9 = "111110100101";
        
        //d4={"0", "101", "1000", "1001"};
        ArrayList<String> ienc4= new ArrayList<>(Arrays.asList( new String[]{"c2","c3","c5", "c1", "c1","c0","c0","c0","c2","c4","c0","c3"} ));
        String idec4 = "01011001111000";
        
        
        //----------------------------------- Test Starts Here------------------------------------
        System.out.println("---------------------**TEST BEGINS**--------------------------------" );
        System.out.println("\nSection1: Testing constructor; Tests 1-17" );
        System.out.println("**Part 1, Valid, Sorted**" );
        
        System.out.println("--------Test 1------------" );

        BinTree a1 = new BinTree(d1);
        System.out.println("Output: " );
        a1.printTree();
        System.out.println("\nExpect: \nc0 I c1 I c2" );
        
        System.out.println("--------Test 2------------" );

        BinTree a2 = new BinTree(d2);
        System.out.println("Output: " );
        a2.printTree();
        System.out.println("\nExpect: \nc0 I c1 I c2 I c3" );
        System.out.println("--------Test 3------------" );

        BinTree a3 = new BinTree(d3);
        System.out.println("Output: " );
        a3.printTree();
        System.out.println("\nExpect: \nc0 I c1 I c2 I" );
        
        
        System.out.println("--------Test 4------------" );
        
        BinTree a4 = new BinTree(d4);
        System.out.println("Output: " );
        a4.printTree();
        System.out.println("\nExpect: \nc0 I c2 I c3 I c1 I" );
        
        System.out.println("--------Test 5------------" );
        
        BinTree a5 = new BinTree(d5);
        System.out.println("Output: " );
        a5.printTree();
        System.out.println("\nExpect: \nc0 I c1 I c2 I c3 I c4 I I c5 I c6 I I c7" );
        
        
        System.out.println("--------Test 6------------" );

        BinTree a6 = new BinTree(d6);
        System.out.println("Output: " );
        a6.printTree();
        System.out.println("\nExpect: \nI I c0 I I" );
        
        
        //______________________________________________________________________________
        System.out.println("\n**Part 2, Valid, Unsorted**" );
        System.out.println("--------Test 7------------" );

        BinTree a7 = new BinTree(d7);
        System.out.println("Output: " );
        a7.printTree();
        System.out.println("\nExpect: \nc1 I c0 I c2 I c3 I" );
        
        System.out.println("--------Test 8------------" );

        BinTree a8 = new BinTree(d8);
        System.out.println("Output: " );
        a8.printTree();
        System.out.println("\nExpect: \nc2 I c6 I c4 I c1 I c0 I I c5 I c7 I I c3" );
        
        System.out.println("--------Test 9------------" );

        BinTree a9 = new BinTree(d9);
        System.out.println("Output: " );
        a9.printTree();
        System.out.println("\nExpect: \nc1 I c3 I c0 I c2" );
        
        
        
        //__________________________________________________________________________
        System.out.println("\n**Part 3, Invalid, Not binary**" );
        System.out.println("--------Test 10------------" );
        try {
        BinTree a10 = new BinTree(d10);
        System.out.println("Test 10 failed (-1); Did not detect non-binary" );
        }
        catch (Exception e) {
            System.out.println("Test 10 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("--------Test 11------------" );
        try {
        BinTree a10 = new BinTree(d11);
        System.out.println("Test 11 failed (-1); Did not detect non-binary" );
        }
        catch (Exception e) {
            System.out.println("Test 11 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        //**********
        System.out.println("--------Test 12------------" );
        try {
        BinTree a10 = new BinTree(d12);
        System.out.println("Test 12 failed (-1); Did not detect duplicate codes" );
        }
        catch (Exception e) {
            System.out.println("Test 12 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("--------Test 13------------" );
        try {
        BinTree a10 = new BinTree(d13);
        System.out.println("Test 13 failed (-1); Did not detect duplicate codes" );
        }
        catch (Exception e) {
            System.out.println("Test 13 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        
        //***********
        System.out.println("--------Test 14------------" );
        try {
        BinTree a10 = new BinTree(d14);
        System.out.println("Test 14 failed (-1); Did not detect prefix." );
        }
        catch (Exception e) {
            System.out.println("Test 14 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("--------Test 15------------" );
        try {
        BinTree a10 = new BinTree(d15);
        System.out.println("Test 15 failed (-1); Did not detect prefix." );
        }
        catch (Exception e) {
            System.out.println("Test 15 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("--------Test 16------------" );
        try {
        BinTree a10 = new BinTree(d16);
        System.out.println("Test 16 failed (-1); Did not detect prefix." );
        }
        catch (Exception e) {
            System.out.println("Test 16 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("--------Test 17------------" );
        try {
        BinTree a10 = new BinTree(d17);
        System.out.println("Test 17 failed (-1); Did not detect prefix." );
        }
        catch (Exception e) {
            System.out.println("Test 17 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing constructor finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection2: Testing getTreeHeight(); Tests 18-19" );
        System.out.println("--------Test 18------------" );
        try {
        int h1=a1.getTreeHeight();
        System.out.println("The height is: "+h1);
        if(h1==3||h1==2) {
            System.out.println("Test 18 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 18 fail (-1). Incorrect height!" );
        }
        
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        System.out.println("\n--------Test 19------------" );
        try {
        int h2=a8.getTreeHeight();
        System.out.print("The height is : "+h2);
        if(h2==6||h2==5) {
            System.out.println("\nTest 19 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 19 fail (-1). Incorrect height!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        //___________________________________________________________________
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing getTreeHeight() finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection 3: Testing getCodewords(); Tests 20-22" );
        System.out.println("--------Test 20------------" );
        try {
        String lt2=a2.getCodewords().toString();
        System.out.println("Output: "+lt2);
        String d222=Arrays.toString(d2);
        System.out.println("Expect: "+d222);
        if(lt2.equals(d222)) {
            System.out.println("Test 20 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 20 fail (-1). Incorrect Set!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }	
        
        
        
        System.out.println("--------Test 21------------" );
        try {
        String lt4=a4.getCodewords().toString();
        System.out.println("Output: "+lt4);
        //String d44=Arrays.toString(d4);
        String d44="[0, 1000, 1001, 101]";
        System.out.println("Expect: "+d44);
        if(lt4.equals(d44)) {
            System.out.println("Test 21 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
            
        else System.out.println("Test 21 fail (-1). Incorrect Set!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 22------------" );
        try {
        String lt8=a8.getCodewords().toString();
        System.out.println("Output: "+lt8);
        //String[] d8s=d8.clone();
        //Arrays.sort(d8s);
        String d88=Arrays.toString(d5);
        System.out.println("Expect: "+d88);
        if(lt8.equals(d88)) {
            System.out.println("Test 22 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 22 fail (-1). Incorrect Set!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        //___________________________________________________________________
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing getCodewords() finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection 4: Testing toArray(); Tests 23-26" );
        
        System.out.println("--------Test 23------------" );
        try {
        String[] con1=a1.toArray();
        System.out.println("Output: "+Arrays.toString(con1));
        String exp1="[null, I, c0, I, null, null, c1, c2]";
        System.out.println("Expect: "+exp1);
        if(Arrays.toString(con1).equals(exp1)) {
            System.out.println("Test 23 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
            
        else System.out.println("Test 23 fail (-1). Incorrect list!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        System.out.println("--------Test 24------------" );
        try {
        String[] con3=a3.toArray();
        System.out.println("Output: "+Arrays.toString(con3));
        String exp3="[null, I, c0, I, null, null, I, null, null, null, null, null, c1, c2, null, null]";
        System.out.println("Expect: "+exp3);
        if(Arrays.toString(con3).equals(exp3)) {
            System.out.println("Test 24 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        
        else System.out.println("Test 24 fail (-1). Incorrect list!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        System.out.println("--------Test 25------------" );
        try {
        String[] con4=a4.toArray();
        System.out.println("Output: "+Arrays.toString(con4));
        String exp4="[null, I, c0, I, null, null, I, null, null, null, null, null, I, c1, null, null, null, null, null, null, null, null, null, null, c2, c3, null, null, null, null, null, null]";
        System.out.println("Expect: "+exp4);
        if(Arrays.toString(con4).equals(exp4)) {
            System.out.println("Test 25 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        
        else System.out.println("Test 25 fail (-1). Incorrect list!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 26------------" );
        try {
        String[] con8=a8.toArray();
        System.out.println("Output: "+Arrays.toString(con8));
        String exp8="[null, I, I, I, c2, I, I, I, null, null, c6, c4, c1, c0, null, I, null, null, null, null, null, null, null, null, null, null, null, null, null, null, I, I, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, c5, c7, null, c3]";
        System.out.println("Expect: "+exp8);
        if(Arrays.toString(con8).equals(exp8)) {
            System.out.println("Test 26 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        
        else System.out.println("Test 26 fail (-1). Incorrect list!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        //String[] strArray = arrayList.toArray(new String[arrayList.size()]);
        //___________________________________________________________________
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing toArray() finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection 5: Testing encode(); Tests 27-33" );
        System.out.println("\n**Part1: Valid Sequence" );
        System.out.println("--------Test 27------------" );
        try {
        String encod1=a1.encode(enc1);
        System.out.println("Output: " +encod1);
        System.out.println("Expect: " +dec1);
        if(encod1.equals(dec1)) {
            System.out.println("Test 27 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        
        else System.out.println("Test 27 fail (-1). Incorrect encoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 28------------" );
        try {
        String encod4=a4.encode(enc4);
        System.out.println("Output: " +encod4);
        System.out.println("Expect: " +dec4);
        if(encod4.equals(dec4)) {
            System.out.println("Test 28 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 28 fail (-1). Incorrect encoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        System.out.println("--------Test 29------------" );
        try {
        String encod8=a8.encode(enc8);
        System.out.println("Output: " +encod8);
        System.out.println("Expect: " +dec8);
        if(encod8.equals(dec8)) {
            System.out.println("Test 29 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 29 fail (-1). Incorrect encoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        System.out.println("--------Test 30------------" );
        try {
        String encod9=a9.encode(enc9);
        System.out.println("Output: " +encod9);
        System.out.println("Expect: " +dec9);
        if(encod9.equals(dec9)) {
            System.out.println("Test 30 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 30 fail (-1). Incorrect encoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        
        System.out.println("\n**Part2: inValid Sequence" );
        System.out.println("--------Test 31------------" );
        try {
            String iencod3=a3.encode(ienc3);
            System.out.println("Test 31 failed; Did not detect non-existing alphabet!" );
            }
        catch (Exception e) {
            System.out.println("Test 31 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
            }
        
        System.out.println("--------Test 32------------" );
        try {
            String iencod4=a4.encode(ienc4);
            System.out.println("Test 32 failed; Did not detect non-existing alphabet!" );
            }
        catch (Exception e) {
            System.out.println("Test 32 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
            }
        
        System.out.println("--------Test 33------------" );
        try {
            String iencod9=a9.encode(ienc9);
            System.out.println("Test 33 failed; Did not detect non-existing alphabet!" );
            }
        catch (Exception e) {
            System.out.println("Test 33 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
            }
        
        //___________________________________________________________________
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing encode() finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection 6: Testing decode(); Tests 34-40" );
        System.out.println("\n**Part1: Valid Sequence" );
        System.out.println("--------Test 34------------" );
        try {
        ArrayList<String> decod1=a1.decode(dec1);
        System.out.println("Output: " +decod1.toString());
        System.out.println("Expect: " +enc1.toString());
        if(decod1.equals(enc1)) {
            System.out.println("Test 34 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 34 fail (-1). Incorrect decoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        

        System.out.println("--------Test 35------------" );
        try {
        ArrayList<String> decod4=a4.decode(dec4);
        System.out.println("Output: " +decod4.toString());
        System.out.println("Expect: " +enc4.toString());
        if(decod4.equals(enc4)) {
            System.out.println("Test 35 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 35 fail (-1). Incorrect decoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }

        System.out.println("--------Test 36------------" );
        try {
        ArrayList<String> decod8=a8.decode(dec8);
        System.out.println("Output: " +decod8.toString());
        System.out.println("Expect: " +enc8.toString());
        if(decod8.equals(enc8)) {
            System.out.println("Test 36 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 36 fail (-1). Incorrect decoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }

        System.out.println("--------Test 37------------" );
        try {
        ArrayList<String> decod9=a9.decode(dec9);
        System.out.println("Output: " +decod9.toString());
        System.out.println("Expect: " +enc9.toString());
        if(decod9.equals(enc9)) {
            System.out.println("Test 37 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 37 fail (-1). Incorrect decoding!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        
        //__________________________
        System.out.println("\n**Part2: inValid Sequence" );
        System.out.println("--------Test 38------------" );
        try {
            ArrayList<String> idecod3=a3.decode(idec3);
            System.out.println("Test 38 failed; Did not detect non-existing alphabet!" );
        }
        catch (Exception e) {
            System.out.println("Test 38 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }

        System.out.println("--------Test 39------------" );
        try {
            ArrayList<String> idecod4=a4.decode(idec4);
            System.out.println("Test 39 failed; Did not detect non-existing alphabet!" );
        }
        catch (Exception e) {
            System.out.println("Test 39 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }

        System.out.println("--------Test 40------------" );
        try {
            ArrayList<String> idecod9=a9.decode(idec9);
            System.out.println("Test 40 failed; Did not detect non-existing alphabet!" );
        }
        catch (Exception e) {
            System.out.println("Test 40 Pass. (+1)" );
            FinalMarks=FinalMarks+1;
            System.out.println("Message: "+e.getMessage());
        }
        
        //_________________________________________________________________________________________________________
        System.out.println("----------------------------------------------------" );
        System.out.println("Testing decode() finished!" );
        System.out.println("----------------------------------------------------" );
        System.out.println("\nSection 7: Testing toString(); Tests 41-44" );
        
        System.out.println("--------Test 41------------" );
        try {
        System.out.println("input string: " + Arrays.toString(d2));
        String s2=a2.toString();
        System.out.println("Output: " +s2);
        System.out.println("Expect: " +"0 10 110 111 ");
        if(s2.equals("0 10 110 111 ")) {
            System.out.println("Test 41 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 41 fail (-1). Incorrect toString()!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 42------------" );
        try {
        String s4=a4.toString();
        System.out.println("Output: " +s4);
        System.out.println("Expect: " +"0 1000 1001 101 ");
        if(s4.equals("0 1000 1001 101 ")) {
            System.out.println("Test 42 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 42 fail (-1). Incorrect toString()!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 43------------" );
        try {
        String s8=a8.toString();
        System.out.println("Output: " +s8);
        System.out.println("Expect: " +"00 010 011 100 101 11100 11101 11111 ");
        if(s8.equals("00 010 011 100 101 11100 11101 11111 ")) {
            System.out.println("Test 43 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 43 fail (-1). Incorrect toString()!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("--------Test 44------------" );
        try {
        String s9=a9.toString();
        System.out.println("Output: " +s9);
        System.out.println("Expect: " +"0 10 110 111 ");
        if(s9.equals("0 10 110 111 ")) {
            System.out.println("Test 44 Pass (+1)" );
            FinalMarks=FinalMarks+1;}
        else System.out.println("Test 44 fail (-1). Incorrect toString()!" );
        }
        catch (Exception e) {
            System.out.println("Test failed (-1). Error occured." );
        }
        
        System.out.println("\n-----------------------------Testing Done!-------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Total Mark is: "+FinalMarks+"/44. \nPlease check for 9 marks for constructor manually!" );
        System.out.println("-------------------------------------------------------------------------------");
        
    }
}

