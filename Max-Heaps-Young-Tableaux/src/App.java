import heapyt.MaxBinHeap;
import heapyt.YoungT;

public class App {
    public static void main(String[] args) throws Exception {
        Newyt();
        //TestMaxBinHeap();
        // TestYoungT();
        NewTestHeap();
    }

    public static void TestYoungT() {
        //data for testing:
		int[] arr1= new int[] { 1, 5, 4, 3,20, 18, 16, 14, 10, 12};   //int array arr1
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		int[] arr3={11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};  //int array arr2

                //data for testing YoungT:
		int[][] d1 = {{1, 2, 20}, {5, 6, 7}, {9, 10, 11}};
		int[][] d2 = {{110, 2, 3, 4}, {20, 6, 7, 8}, {9, 10, 11, 12}};

		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );	                  
		
		System.out.println("\n--------------Testing YoungT  class--------------------------------");
		System.out.println('\n');
		System.out.println("--------Test 7------------" );
		
		System.out.println("Testing YoungT constructor 1 for empty YoungT:");  
		YoungT t1 = new YoungT(3, 3, 90);   
		System.out.println(" \nprinting empty tableaux: \n"+ t1.toString());
		
		System.out.println(" \n Testing isEmpty method: "); 
		System.out.println(" is the tableaux empty: " + t1.isEmpty()); 
		System.out.println(" Expected : true" );
		
		System.out.println(" \n Testing getInfinity method: "); 
		System.out.println(" value of infinity: " + t1.getInfinity()); 
		System.out.println(" Expected value of infinity: = 100" );

		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 8------------" );
		
		System.out.println("Testing YoungT constructor 2 and insert method using d1: "); 
		YoungT t2= new YoungT(d1);  
		
		System.out.println(" #finite integers: " + t2.getNumElem()); 
		System.out.println(" Expected #finite integers = 9" );
		
		System.out.println(" value of infinity: " + t2.getInfinity()); 
		System.out.println(" Expected: 200" );
		
		System.out.println(" printing items stored in the tableau: \n"+ t2.toString());  
		String p1=" 1, 2, 7 , 5, 6, 11, 9, 10, 20";
		String p2=" 1, 6, 7, 2, 9, 10, 5, 11, 20";

		System.out.print(" This test assumes that your constructor scans the input array in raster scan order and inserts the current element in the tableau using the insert method).\n");
        System.out.print(" There are two possibilities for the correct output depending on how you move (in insert) the input value x - if you move it up (option1) or to the left - when both neighbours are infinity.\n");

		System.out.println("Expected option 1: "+p1);
		System.out.println("Expected option 2: "+p2);

		System.out.println(" \n Testing isFull method: "); 
		System.out.println(" is the tableau Full: " + t2.isFull()); 
		System.out.println(" Expected : true" );
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 9------------" );
		System.out.println(" Testing YoungT readMin method: "); 
		System.out.println(" min value in the tableau: " + t2.readMin());
		System.out.println(" Expected min value = 1" );
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
			
		System.out.println("--------Test 11------------" );
		System.out.println("Testing YoungT deleteMin method: "); 
		int min_val = t2.deleteMin();                      // Extracting min value
		System.out.println("smallest value extracted using deleteMin: "+ min_val); // printing the min value
		System.out.println("Expected value :  1") ;
		
		System.out.println(" \n #items stored in the tableau after deleteMin: "+ t2.getNumElem()); 
		System.out.println(" Expected #items stored in the tableau after deleteMin = 8" );
		
		//Put the inf key on the smallest key's position and fix the Young tableau property
		System.out.println(" \nprinting items stored in the tableaux after deleteMin: \n" + t2.toString());  
		String p3=" 2, 6, 7, 5, 10, 11, 9, 20, inf";
		String p31=" 2, 6, 7, 5, 9, 10, 11, 20, inf";
		System.out.println("Expected (if the initial tableau was 1, 2, 7 , 5, 6, 11, 9, 10, 20: "+p3);
		System.out.println("Expected (if the initial tableau was 1, 6, 7, 2, 9, 10, 5, 11, 20: "+p31);
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		
		System.out.println("--------Test 12.1------------" );
		System.out.println(" Testing YoungT find method: ");                        
		//using d1 to test find method
		System.out.print("Finding 5 in the tableau:" + t2.find(5));
		System.out.println("\nExpected find result: true");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 2, 6, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 5\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 6, 1, 2, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5\n");
		
		
		
		
		System.out.println("--------Test 12.2------------\n" );
		System.out.print("Finding 12 in the tableau:" + t2.find(12));
		System.out.println("\nExpected find result: false");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 11, 20, 10\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 10, 20, 11\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 10, 20, 11\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5, 11, 20, 10\n");
		
		
		System.out.println("\n-----------------------------Testing Done for YoungT!-------------------------------------");
		System.out.println('\n');
    }

    public static void TestMaxBinHeap() {
        // Data for testing
        int[] arr1 = {1, 5, 4, 3, 20, 18, 16, 14, 10, 12};
        int[] arr2 = {5, 4, 3, 11, 35, 56};
        int[] arr3 = {11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};

        //----------------------------------- Test Begins Here ------------------------------------
        System.out.println("---------------------**TEST BEGINS**--------------------------------");
        System.out.println("\n--------------Testing MaxBinHeap class--------------------------------\n");

        // Test 1: Testing MaxBinHeap constructor for an empty Heap
        System.out.println("--------Test 1------------");
        System.out.println("Testing MaxBinHeap constructor for an empty Heap:");
        MaxBinHeap heap1 = new MaxBinHeap(10);
        System.out.println(" #keys stored in the empty heap: " + heap1.getSize());
        System.out.println(" Expected #keys stored in the empty heap = 0\n");

        // Test 2: Testing MaxBinHeap constructor with an array
        System.out.println("--------Test 2------------");
        System.out.println("Testing MaxBinHeap constructor with arr1:");
        MaxBinHeap heap2 = new MaxBinHeap(arr1);
        System.out.println(" #items stored in the heap: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 10");
        System.out.println("Heap: " + heap2 + "\n");
        System.out.println("Expected Heap: 20, 14, 18, 10, 12, 4, 16, 3, 1, 5\n");

        // Test 3: Testing readMax method
        System.out.println("--------Test 3------------");
        System.out.println("Testing readMax method for the heap:");
        System.out.println(" Max value in the heap: " + heap2.readMax());
        System.out.println(" Expected max value = 20\n");

        // Test 4: Testing insert method
        System.out.println("--------Test 4------------");
        heap2.insert(30);
        System.out.println(" Testing insert method by adding 30:");
        System.out.println(" #items stored in the heap after insertion: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 11");
        System.out.println("Heap after insertion: " + heap2 + "\n");

        // Test 5: Testing deleteMax method
        System.out.println("--------Test 5------------");
        int maxVal = heap2.deleteMax();
        System.out.println("Testing deleteMax method:");
        System.out.println(" Max value extracted using deleteMax: " + maxVal);
        System.out.println(" Expected value: 30");
        System.out.println(" #items stored in the heap after deleteMax: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap after deleteMax = 10");
        System.out.println("Heap after deleting max: " + heap2 + "\n");

        // Test 6: Testing sortArray method
        System.out.println("--------Test 6------------");
        System.out.println("Testing sortArray method with arr2:");
        MaxBinHeap.sortArray(arr2);
        System.out.print("Sorted arr2: ");
        for (int n : arr2) System.out.print(n + " ");
        System.out.println("\nExpected sorted array: 56, 35, 11, 5, 4, 3");

        System.out.println("\n-----------------------------Testing Done for MaxBinHeap!-------------------------------------");
    }

    public static void OldTestMaxBinHeap() {
        // Data for testing
        int[] arr1 = {1, 5, 4, 3, 20, 18, 16, 14, 10, 12};
        int[] arr2 = {5, 4, 3, 11, 35, 56};

        // Testing MaxBinHeap class
        System.out.println("---------------------**TEST BEGINS**--------------------------------");
        System.out.println("\n--------------Testing MaxBinHeap class--------------------------------");

        // Test 1: Testing MaxBinHeap constructor with an array
        System.out.println("\n--------Test 1------------");
        System.out.println("Testing MaxBinHeap constructor with arr1:");
        MaxBinHeap heap1 = new MaxBinHeap(arr1);
        System.out.println("Heap: " + heap1);
        System.out.println("Expected: 20, 14, 18, 10, 12, 5, 16, 3, 4, 1");

        // Test 2: Testing insert
        System.out.println("\n--------Test 2------------");
        System.out.println("Testing insert method by adding 22 to the heap:");
        heap1.insert(22);
        System.out.println("Heap after insertion: " + heap1);
        System.out.println("Expected: 22, 20, 18, 10, 14, 4, 16, 3, 1, 5, 12");

        // Test 3: Testing deleteMax
        System.out.println("\n--------Test 3------------");
        int max = heap1.deleteMax();
        System.out.println("Deleted max element: " + max);
        System.out.println("Heap after deleting max: " + heap1);
        System.out.println("Expected: 20, 14, 18, 10, 12, 5, 16, 3, 4, 1");

        // Test 4: Testing sortArray
        System.out.println("\n--------Test 4------------");
        System.out.println("Testing sortArray with arr2:");
        MaxBinHeap.sortArray(arr2);
        System.out.print("Sorted arr2: ");
        for (int n : arr2) System.out.print(n + " ");
        System.out.println("\nExpected: 56 35 11 5 4 3");
        
     // Test 5: Testing getSize()
        System.out.println("\n--------Test 5------------");
        System.out.println("Testing getSize method:");
        System.out.println("Current heap size: " + heap1.getSize());
        System.out.println("Expected heap size: 10");

        // Test 6: Testing readMax()
        System.out.println("\n--------Test 6------------");
        System.out.println("Testing readMax method:");
        try {
            System.out.println("Current max element: " + heap1.readMax());
            System.out.println("Expected max element: 20");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Test 7: Testing toString()
        System.out.println("\n--------Test 7------------");
        System.out.println("Testing toString method:");
        System.out.println("Heap representation: " + heap1);
        System.out.println("Expected representation: 20, 14, 18, 10, 12, 4, 16, 3, 1, 5");


        System.out.println("\n-----------------------------Testing Done for MaxBinHeap!-------------------------------------");
    }

    public static void NewTestHeap() {
        // Data for testing
        int[] arr1 = {1, 5, 4, 3, 20, 18, 16, 14, 10, 12};
        int[] arr2 = {5, 4, 3, 11, 35, 56};
        int[] arr3 = {11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};

        //----------------------------------- Test Begins Here ------------------------------------
        System.out.println("---------------------**TEST BEGINS**--------------------------------");
        System.out.println("\n--------------Testing MaxBinHeap class--------------------------------\n");

        // Test 1: Testing MaxBinHeap constructor for an empty Heap
        System.out.println("--------Test 1------------");
        System.out.println("Testing MaxBinHeap constructor for an empty Heap:");
        MaxBinHeap heap1 = new MaxBinHeap(10);
        System.out.println(" #keys stored in the empty heap: " + heap1.getSize());
        System.out.println(" Expected #keys stored in the empty heap = 0\n");

        // Test 2: Testing MaxBinHeap constructor with an array
        System.out.println("--------Test 2------------");
        System.out.println("Testing MaxBinHeap constructor with arr1:");
        MaxBinHeap heap2 = new MaxBinHeap(arr1);
        System.out.println(" #items stored in the heap: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 10");
        System.out.println("Heap: " + heap2 + "\n");
        System.out.println("Expected Heap: 20, 14, 18, 10, 12, 4, 16, 3, 1, 5\n");

        // Test 3: Testing readMax method
        System.out.println("--------Test 3------------");
        System.out.println("Testing readMax method for the heap:");
        System.out.println(" Max value in the heap: " + heap2.readMax());
        System.out.println(" Expected max value = 20\n");

        // Test 4: Testing insert method
        System.out.println("--------Test 4------------");
        heap2.insert(30);
        System.out.println(" Testing insert method by adding 30:");
        System.out.println(" #items stored in the heap after insertion: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap = 11");
        System.out.println("Heap after insertion: " + heap2 + "\n");

        // Test 5: Testing deleteMax method
        System.out.println("--------Test 5------------");
        int maxVal = heap2.deleteMax();
        System.out.println("Testing deleteMax method:");
        System.out.println(" Max value extracted using deleteMax: " + maxVal);
        System.out.println(" Expected value: 30");
        System.out.println(" #items stored in the heap after deleteMax: " + heap2.getSize());
        System.out.println(" Expected #keys stored in the heap after deleteMax = 10");
        System.out.println("Heap after deleting max: " + heap2 + "\n");

        // Test 6: Testing sortArray method
        System.out.println("--------Test 6------------");
        System.out.println("Testing sortArray method with arr2:");
        MaxBinHeap.sortArray(arr2);
        System.out.print("Sorted arr2: ");
        for (int n : arr2) System.out.print(n + " ");
        System.out.println("\nExpected sorted array: 56, 35, 11, 5, 4, 3");

        System.out.println("\n-----------------------------Testing Done for MaxBinHeap!-------------------------------------");
    }

    public static void Newyt() {
        //data for testing:
		int[] arr1= new int[] { 1, 5, 4, 3,20, 18, 16, 14, 10, 12};   //int array arr1
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		int[] arr3={11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};  //int array arr2

                //data for testing YoungT:
		int[][] d1 = {{1, 2, 20}, {5, 6, 7}, {9, 10, 11}};
		int[][] d2 = {{110, 2, 3, 4}, {20, 6, 7, 8}, {9, 10, 11, 12}};

		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );	                  
		
		System.out.println("\n--------------Testing YoungT  class--------------------------------");
		System.out.println('\n');
		System.out.println("--------Test 7------------" );
		
		System.out.println("Testing YoungT constructor 1 for empty YoungT:");  
		YoungT t1 = new YoungT(3, 3, 90);   
		//System.out.println(" \nprinting empty tableaux: \n"+ t1.toString());
		
		System.out.println(" \n Testing isEmpty method: "); 
		System.out.println(" is the tableaux empty: " + t1.isEmpty()); 
		System.out.println(" Expected : true" );
		
		System.out.println(" \n Testing getInfinity method: "); 
		System.out.println(" value of infinity: " + t1.getInfinity()); 
		System.out.println(" Expected value of infinity: = 100" );

		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 8------------" );
		
		System.out.println("Testing YoungT constructor 2 and insert method using d1: "); 
		YoungT t2= new YoungT(d1);  
		
		System.out.println(" #finite integers: " + t2.getNumElem()); 
		System.out.println(" Expected #finite integers = 9" );
		
		System.out.println(" value of infinity: " + t2.getInfinity()); 
		System.out.println(" Expected: 200" );
		
		System.out.println(" printing items stored in the tableau: \n"+ t2.toString());  
		String p1=" 1, 2, 7 , 5, 6, 11, 9, 10, 20";
		String p2=" 1, 6, 7, 2, 9, 10, 5, 11, 20";

		System.out.print(" This test assumes that your constructor scans the input array in raster scan order and inserts the current element in the tableau using the insert method).\n");
        System.out.print(" There are two possibilities for the correct output depending on how you move (in insert) the input value x - if you move it up (option1) or to the left - when both neighbours are infinity.\n");

		System.out.println("Expected option 1: "+p1);
		System.out.println("Expected option 2: "+p2);

		System.out.println(" \n Testing isFull method: "); 
		System.out.println(" is the tableau Full: " + t2.isFull()); 
		System.out.println(" Expected : true" );
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 9------------" );
		System.out.println(" Testing YoungT readMin method: "); 
		System.out.println(" min value in the tableau: " + t2.readMin());
		System.out.println(" Expected min value = 1" );
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
			
		System.out.println("--------Test 11------------" );
		System.out.println("Testing YoungT deleteMin method: "); 
		int min_val = t2.deleteMin();                      // Extracting min value
		System.out.println("smallest value extracted using deleteMin: "+ min_val); // printing the min value
		System.out.println("Expected value :  1") ;
		
		System.out.println(" \n #items stored in the tableau after deleteMin: "+ t2.getNumElem()); 
		System.out.println(" Expected #items stored in the tableau after deleteMin = 8" );
		
		//Put the inf key on the smallest key's position and fix the Young tableau property
		System.out.println(" \nprinting items stored in the tableaux after deleteMin: \n" + t2.toString());  
		String p3=" 2, 6, 7, 5, 10, 11, 9, 20, inf";
		String p31=" 2, 6, 7, 5, 9, 10, 11, 20, inf";
		System.out.println("Expected (if the initial tableau was 1, 2, 7 , 5, 6, 11, 9, 10, 20: "+p3);
		System.out.println("Expected (if the initial tableau was 1, 6, 7, 2, 9, 10, 5, 11, 20: "+p31);
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		
		System.out.println("--------Test 12.1------------" );
		System.out.println(" Testing YoungT find method: ");                        
		//using d1 to test find method
		System.out.print("Finding 5 in the tableau:" + t2.find(5));
		System.out.println("\nExpected find result: true");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 2, 6, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 5\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 6, 1, 2, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5\n");
		
		
		
		
		System.out.println("--------Test 12.2------------\n" );
		System.out.print("Finding 12 in the tableau:" + t2.find(12));
		System.out.println("\nExpected find result: false");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 11, 20, 10\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 10, 20, 11\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 10, 20, 11\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5, 11, 20, 10\n");
		
		
		System.out.println("\n-----------------------------Testing Done for YoungT!-------------------------------------");
		System.out.println('\n');
    }
}
