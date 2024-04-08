package lab5;

public class KnapsackDP {
	private int num; // number of items
	private int targetWeight; // target weight
	private int [] value; // value of each item
	private int [] weight; // weight of each item
	private int [] [] x; // 2D array to store the solution
	private int [] [] totalValue; // 2D array to store the total value
	private int [][] totalWeight; // 2D array to store the total weight
	
	
	public KnapsackDP(String s) {
		String [] splitInputString = s.split(" "); // split the input string by space character to get the individual parts

		num = Integer.parseInt(splitInputString[0]); // parse the first part of the input string to get the number of items
		targetWeight = Integer.parseInt(splitInputString[1]); // parse the second part of the input string to get the target weight
		value = new int [num]; // create an array to store the value of each item
		weight = new int [num]; // create an array to store the weight of each item
		
		// loop through the rest of the input string to get the value and weight of each item
		for (int i = 0; i < num; i++) {

			/*Value Index Calculation:

			The valueIndex is calculated as 2 + 2 * i.
			i represents the current iteration of the loop, starting from 0.
			Multiplying 2 * i ensures that we skip every other element in the split input array, 
			as the values are located at even indices (starting from index 2).
			Adding 2 ensures that we start from the correct index in the split input array. */
			int valueIndex = 2 + 2 * i;

			/*
			 * Weight Index Calculation:

			The weightIndex is calculated as 3 + 2 * i.
			Similar to the valueIndex, 2 * i is multiplied to skip every other element.
			However, we add 3 to ensure that we start from the index after the value for each item.
			 */
			int weightIndex = 3 + 2 * i;
			
			if (valueIndex < splitInputString.length) { // check if the value index is within the bounds of the split input array
				value[i] = Integer.parseInt(splitInputString[valueIndex]); // parse the value at the value index and store it in the value array
			}
			if (weightIndex < splitInputString.length) { // check if the weight index is within the bounds of the split input array
				weight[i] = Integer.parseInt(splitInputString[weightIndex]); // parse the weight at the weight index and store it in the weight array
			}
		}
		
		// 2D array just to save the values and weight
		x = new int [num+1][targetWeight + 1]; // create a 2D array to store the solution for each item and weight combination (initialized to 0)
		totalWeight = new int [num+1][targetWeight +1]; // create a 2D array to store the total weight for each item and weight combination (initialized to 0)
		totalValue = new int [num+1] [targetWeight+1]; // create a 2D array to store the total value for each item and weight combination (initialized to 0)
	} 
	
	public static String recMemo(String s) {
		/*
		 * solves the 0-1 knapsack prob-
		lem specified by the input string in O(nW ) time using recursion with memoiza-
		tion and outputs a string that represents the solution; the method should first
		create a KnapsackDP object that corresponds to the problem specified by the
		input, then solve the problem for this particular object by invoking a private
		instance method to perform the recursion with memoization.
		 */

		KnapsackDP myknapsackDP = new KnapsackDP(s);
		// call the recursive method 
		myknapsackDP.recursiveMemoization(myknapsackDP.num, myknapsackDP.targetWeight); 
		return myknapsackDP.constructOutput(); // return the constructOutput as a string
	}
	
	private void recursiveMemoization (int j, int k) {
		// base case
		if(k == 0 || j == 0) { // if the weight is 0 or no items left, the values are 0 (j represents the number of items, k represents the target weight)
			updateMatrix(j,k,0,0,0); // update the matrix with 0 values
			return;
		}
		// if the value is not 0 then return or the weight is not 0 then return as the problem is already solved
		if(totalValue[j][k] != 0 || totalWeight[j][k] != 0) { // if the value and weight are not 0, the problem is already solved
			return;
		}
		
		recursiveMemoization(j-1,k); // recursive call to solve the subproblem with one less item
		
		if(k >= weight [j-1]) { // if the weight being targeted is greater than or equal to the weight of the current item
			
			recursiveMemoization(j-1, k-weight[j-1]); // recursive call to solve the subproblem with one less item and reduced weight
			
			if(totalValue[j-1][k] < totalValue[j-1][k-weight[j-1]] + value[j-1]) { 
				// if the value without the current item is less than the value with the current item
				updateMatrix(j,k,1,totalValue[j-1][k-weight[j-1]]+ value[j-1], totalWeight[j-1][k-weight[j-1]] + weight[j-1]);
			}
			
			else { // if the value without the current item is greater than or equal to the value with the current item
				updateMatrix(j,k,0,totalValue[j-1][k], totalWeight[j-1][k]); // update the matrix with the values without the current item
			}
			
		}else { // if the weight being targeted is less than the weight of the current item
			updateMatrix(j,k,0,totalValue[j-1][k],totalWeight[j-1][k]); // update the matrix with the values without the current item
		}
	
	}
	

	private void updateMatrix (int j, int k, int v, int inputTotalValue, int inputTotalWeight ) { //method that updates the values and weight in the matrix
	
		x[j][k] = v; // update the matrix with the value
		totalWeight[j][k] = inputTotalWeight; // update the matrix with the weight
		totalValue[j][k] = inputTotalValue; // update the matrix with the total value 
		
	}


   	public static String nonRec(String s) {
		/*
		 * solves the 0-1 knapsack problem
		specified by the input string nonrecursively in O(nW ) time (using bottom-
		up dynamic programming) and outputs a string that represents the solution;
		the method should first create a KnapsackDP object that corresponds to the
		problem specified by the input, then solve the problem for this particular
		object
		 */
		KnapsackDP myKnapsackDP = new KnapsackDP(s);
		myKnapsackDP.dp(); // call the dynamic programming method
		return myKnapsackDP.constructOutput();
	}

	private void dp() { // method that solves the 0-1 knapsack problem using bottom-up dynamic programming
		for(int i = 0; i<=num; i++) { // loop through the items
			
			for(int j =0; j<= targetWeight; j++) { // loop through the target weights
				 
				if(j == 0 || i == 0) { // if the weight is 0 or no items left, the values are 0
					x[i][j] = 0; // set the value in the matrix to 0
					totalWeight[i][j] = 0; // set the weight in the matrix to 0
					totalValue[i][j] = 0; // set the total value in the matrix to 0
				}
				
			else { // if the weight is not 0 or there are items left to consider 
			
				if(j >= weight[i-1]) { // if the weight being targeted is greater than or equal to the weight of the current item
					// the data structure is fixed properly if the current item being included is ideal
		    		if(totalValue[i-1][j] < totalValue[i-1] [j-weight[i-1]] + value[i-1]) { // if the value without the current item is less than the value with the current item
		    			x[i][j] = 1; // the item is included
		    			totalValue[i][j] = totalValue[i-1][j-weight[i-1]] + value[i-1]; // the total value is updated
		    			totalWeight [i][j] = totalWeight[i-1][j-weight[i-1]] + weight[i-1]; // the total weight is updated
		    		}
		    		
		    		else { // if the value without the current item is greater than or equal to the value with the current item
		    			x[i][j] = 0; // the item is not included
		    			totalWeight[i][j] = totalWeight[i-1][j]; // the total weight is updated
		    			totalValue[i][j] = totalValue[i-1][j]; // the total value is updated
		    		}
		    		// the data structure is fixed properly if the current item being included is not ideal
				}else {	 // if the weight being targeted is less than the weight of the current item
					x[i][j] = 0; // the item is not included
	    			totalWeight[i][j] = totalWeight[i-1][j]; // the total weight is updated
	    			totalValue[i][j] = totalValue[i-1][j]; // the total value is updated
				}
			}
		}
	}
}
   private String constructOutput() {
	   String outputStr = "";
	   int [] LocationArray = new int [num];
	   int j = targetWeight;
	   
	   for(int i = num; i>=1; i--) { // loop through the items
		   LocationArray[i-1] = x[i][j];
		   
		   if(x[i][j] == 1) { // if the item is included
			   j -= weight[i-1]; // reduce the weight
		   }
	   }
	 
	   for(int k = 0; k <num; k++) { // loop through the items
		   outputStr += LocationArray[k]; // add the item to the output string
		  
		   if(k!= num-1) { // if not the last item
			   outputStr += ", ";
		   }
	   }
	
	   outputStr += ", total value = " + totalValue[num][targetWeight]; // add the total value to the output string
	   outputStr += ", total weight = " + totalWeight[num][targetWeight]; // add the total weight to the output string
	   return outputStr; // return the output string
   }





}
