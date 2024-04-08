package lab5;

public class KnapsackGreedy {
	private int highestWeightPossible;
	private int numItems;
	private int [] valueArr;
	private int []  weightArr;
	
	
	public KnapsackGreedy (String input) { // constructor to initialize the KnapsackGreedy object
		int [] parsedInts = parseIntArray(input); // save is the array of integers that are parsed from the input string
		
		numItems = parsedInts[0]; // number of items
		highestWeightPossible = parsedInts[1]; // highest weight possible
		
		valueArr = new int[numItems]; // array to store the values of the items
		weightArr = new int [numItems]; // array to store the weights of the items
	
		for(int i = 0; i < numItems; i++) { // loop through the items to get the value and weight of each item
			valueArr[i] = parsedInts[2*i+2]; // value of the item
			weightArr[i] = parsedInts[3+2*i]; // weight of the item
		}
	}

	private int [] parseIntArray (String input) {
	
		int i = 0;
	
		int v = 0;
	
		int[] parsedIntsForAns = new int [input.length()/2+1]; // this is an array to store the parsed integers from the input string
		
		for(int k = 0; k< input.length();k++) {
			char ch = input.charAt(k);
			
			if(Character.isDigit(ch)) {
				v = v*10+(ch - '0'); // convert the character to integer and save it
				}
				// check if the character is a space and the previous character is a digit
			else if (ch == ' ' && k > 0 && Character.isDigit(input.charAt(k-1))){
				parsedIntsForAns[i++] = v; // save the integer value
				v = 0; // reset the value to 0
			}
		}
		
		parsedIntsForAns[i] = v; // save the last integer value
		return parsedIntsForAns; // return the array of parsed integers
	}
	
	public static String fractional (String input) {
		/*solves the fractional
		knapsack problem specified by the input string using the greedy algorithm
		and outputs a string that represents the solution; the method should first
		create a KnapsackGreedy object that corresponds to the problem specified by
		the input, then solve the problem for this particular object. */

		
		KnapsackGreedy myKnapSackDP = new KnapsackGreedy(input);
	
		int [] indicesofItemsArr = new int[myKnapSackDP.numItems]; // array to store the index of the items
		double [] valtoWeight = new double [myKnapSackDP.numItems]; // array to store the ratio of value to weight for each item
		
		
		for(int a = 0; a <myKnapSackDP.numItems; a++) { // loop through the items to calculate the ratio of value to weight for each item
			valtoWeight[a] = (double) myKnapSackDP.valueArr[a]/myKnapSackDP.weightArr[a]; // ratio of value to weight
			indicesofItemsArr[a] = a; // save the index of the item
		}
		
		for(int a = 0; a<myKnapSackDP.numItems-1; a++) { // sort the items in decreasing order based on the ratio of value to weight
			int i = a+1; // next item
			while(i< myKnapSackDP.numItems) { // loop through the items
				if(valtoWeight[indicesofItemsArr[a]] < valtoWeight[indicesofItemsArr[i]]) { // check if the ratio of value to weight is less
					int now = indicesofItemsArr[a]; // save the index of the item
					indicesofItemsArr[a] = indicesofItemsArr[i]; // swap the indices
					indicesofItemsArr[i] = now; // swap the indices
				}
			i++; // increment the index
			}
		}
	 
		double newweight = 0; // new weight
		double newvalue = 0; // new value
	
		double [] frractional = new double[myKnapSackDP.numItems]; // array to store the fractional values of the items
		
	
		for(int a = 0; a<myKnapSackDP.numItems; a++) { // loop through the items
			int i = indicesofItemsArr[a];
		
			if(myKnapSackDP.weightArr[i] <= myKnapSackDP.highestWeightPossible - newweight) { // check if the weight of the item is less than the highest weight possible
				frractional[i]= 1;
				newvalue += myKnapSackDP.valueArr[i];
				newweight += myKnapSackDP.weightArr[i];
			}
			else { // if the weight of the item is greater than the highest weight possible
				 
				frractional[i] = (myKnapSackDP.highestWeightPossible - newweight)/ (double) myKnapSackDP.weightArr[i];
				newvalue += frractional[i]*myKnapSackDP.valueArr[i];
				newweight += frractional[i]*myKnapSackDP.weightArr[i];
				break;
			}
		
		}
		
		String finalOut = ""; // string to store the answer
		int j =0; // index
		while(j< frractional.length) {			
			finalOut += ((double) frractional[j]);
		
			if(j < frractional.length-1) { // check if the index is not the last element
				finalOut += ", ";
			}
		j++;
		}
 
		finalOut += ", total value = ";
		finalOut += newvalue;
		finalOut += ", total weight = ";
		finalOut += (double) newweight;
		
		return finalOut; // return the answer

	}
	
	public static String greedy01(String input) {
		/*
		 * implements a greedy al-
		gorithm for the 0-1 knapsack problem specified by the input string and out-
		puts a string that represents the result; the method should first create a
		KnapsackGreedy object that corresponds to the problem specified by the input,
		then solve the problem for this particular object.
		 */
		
		KnapsackGreedy myKnapacks = new KnapsackGreedy(input);
	
		int [] TArr = new int[myKnapacks.numItems];
		double [] valtoWeights = new double [myKnapacks.numItems];
				
		
		for(int a = 0; a <myKnapacks.numItems; a++) { // loop through the items to calculate the ratio of value to weight for each item
			valtoWeights[a] = (double) myKnapacks.valueArr[a]/myKnapacks.weightArr[a];
			TArr[a] = a;
		}
	
		for(int a = 0; a<myKnapacks.numItems-1; a++) { // sort the items in decreasing order based on the ratio of value to weight
			int i = a+1; 	
			while(i< myKnapacks.numItems) { 
				if(valtoWeights[TArr[a]] < valtoWeights[TArr[i]]) { 
					int temp = TArr[a];
					TArr[a] = TArr[i];
					TArr[i] = temp;
				}
			i++;
			}
		}

		int newvalue = 0;
		int newweight = 0;
		
		int [] frac = new int[myKnapacks.numItems];
		
		
		for(int a = 0; a<myKnapacks.numItems; a++) {
			int i = TArr[a];
			
			if(myKnapacks.weightArr[i] <= myKnapacks.highestWeightPossible - newweight) { // check if the weight of the item is less than the highest weight possible
				frac[i]= 1;
				newvalue += myKnapacks.valueArr[i];
				newweight += myKnapacks.weightArr[i];
			}
			
		
		}


		String finalOut = "";
		int j =0;
		while(j< frac.length) {			
			finalOut += ((double) frac[j]);
			
			if(j < frac.length-1) {
				finalOut += ", ";
			}
		j++;
		}

		
		finalOut += ", total value = ";
		finalOut += (double)newvalue;
		finalOut += ", total weight = ";
		finalOut += (double) newweight;
		
		return finalOut;

	}
	
	
	}
