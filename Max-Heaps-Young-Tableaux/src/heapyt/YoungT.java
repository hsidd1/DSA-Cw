package heapyt;

public class YoungT {
    private int[][] youngTableau; // 2D array to store the integers of the Young tableau
    private int numFiniteInts; // number of finite integers in the Young tableau
    private int inf; // infinity value to be used in the Young tableau

    public YoungT(int k, int n, int infinity) {
        /* constructs an empty k×n YoungT.
        The last parameter indicates the value for ∞. If k < 10, it is reset to 10.
        If n < 10, it is reset to 10. If infinity < 100, it is set to 100. */
        if (k < 10) k = 10;
        if (n < 10) n = 10;
        if (infinity < 100) 
            inf = 100;
        else
            inf = infinity;

        youngTableau = new int[k][n];
        numFiniteInts = 0;
    }

    public YoungT(int[][] a) {
        /* constructs a YoungT object storing the integers in the input 2D array. 
        The Young tableau should have the same dimensions as the input array.
        The value for ∞ is reset to 10 times the largest array element. */
        if (a.length == 0 || a[0].length == 0) {
            throw new RuntimeException("The input array is empty.");
        }
        // find the maximum element in the array
        int max = a[0][0];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                }
            }
        }
        inf = max * 10;
        // insert into youngTableau: 
        /* the entries of each row are in
        nondecreasing order from left to right and the entries of each column are in nondecreasing
        order from top to bottom. Some of the entries of a Young tableau may be ∞, which
        we treat as a nonexistent element. */

        // initialize youngTableau with inf
        youngTableau = new int[a.length][a[0].length];
        for (int i = 0; i < youngTableau.length; i++) {
            for (int j = 0; j < youngTableau[i].length; j++) {
                youngTableau[i][j] = inf;
            }
        }
        // insert the elements of array a into youngTableau
        for(int row = 0; row < a.length; row++) {
			for(int col = 0; col < a[row].length; col++) {
                int value = a[row][col];
				insert(value);
			}
		}
    }

    public int getNumElem() {
        /* returns the number of finite integers stored in the
        tableau. */
        return numFiniteInts;
    }

    public int getInfinity() {
        /* returns the number that represents ∞ */
        return inf;
    }

    public boolean isEmpty() {
        /* returns true if the tableau is empty. */
        return numFiniteInts == 0;
    }

    public boolean isFull() {
        /* returns true if the tableau is full */
        // checks if the number of finite integers is equal to the total number of cells in the tableau
        return ((youngTableau.length * youngTableau[0].length) == numFiniteInts);
    }

    public boolean insert(int x) {
        /* inserts the integer x in the Young tableau. If
        x is larger than or equal to the value that represents ∞ or if the tableau is full, no
        insertion is performed and false is returned. Otherwise, the insertion is performed
        and true is returned. */
        
        /* error handling */
		if (x >= inf || isFull()) { // if x is larger than or equal to infinity or if the tableau is full (very useful comment)
            return false;
        }
       
        /* intializations */
        int colsize = youngTableau[0].length;
		int row = numFiniteInts / colsize;  // row = number of finite integers / number of columns (account for infinities)
		int col = numFiniteInts % colsize;  // col = number of finite integers % number of columns (modulus to wrap around the columns if needed)
		
        /* find row */
		while(row > 0) { // until the last row (from bottom to top)
			if(youngTableau[row - 1][col] > x) { // if the integer above is larger than x
				youngTableau[row][col] = youngTableau[row - 1][col]; // move the integer above down
				row--; // go up
			} else { // if the integer above is smaller than x, stop
				break;
			}
		}
		
        /* find col */
		while(col > 0) { // until the last column (from right to left)
			if(youngTableau[row][col - 1] > x) { // if the integer to the left is larger than x
				youngTableau[row][col] = youngTableau[row][col - 1]; // move the integer to the left to the right
				col--; // go left
			} else { // if the integer to the left is smaller than x, stop
				break;
			}
		}
		
        /* insert and stuff */
		youngTableau[row][col] = x; // place x in the correct position
		numFiniteInts++; // increase the number of finite integers
		return true; // return true if the insertion was successful
	}

    public int deleteMin() throws RuntimeException {
        /* returns the smallest element and removes it from the tableau. If the tableau is empty, it throws an
        exception with the message indicating that. */

        /* error handling */
		if(isEmpty()) { // nothing to be deleted
			throw new RuntimeException("The tableau is empty.");
		}
	    
        /* intializations */
		int minVal = youngTableau[0][0]; // Initialize the minimum value found in the tableau
        int currentRow = 0; // Current row index in the tableau
        int currentCol = 0; // Current column index in the tableau

        /* find the next smallest value */
        /* begins from the top left of tableau to find the next value that is smallest for deletion */
        while (currentRow < youngTableau.length - 1 || currentCol < youngTableau[0].length - 1) { // While not at the bottom right of the tableau
            int rVal = inf; // Value to the right of the current position
            int bVal = inf; // Value below the current position
            if (currentCol < youngTableau[0].length - 1) // Check if the value to the right exists
                rVal = youngTableau[currentRow][currentCol + 1]; // Store the value to the right

            if (currentRow < youngTableau.length - 1) // Check if the value below exists
                bVal = youngTableau[currentRow + 1][currentCol]; // Store the value below

            if (rVal == inf && bVal == inf) { // If both values are infinity, then the minimum value has been found
                break;
            }

            /* Place the smaller value in current row and col, and the larger one in the row below, or column to right */
            if (rVal < bVal) { // If the value to the right is less than the value below
                youngTableau[currentRow][currentCol] = rVal; // Replace the current position with the value to the right
                youngTableau[currentRow][currentCol + 1] = inf; // Replace the value to the right with infinity
                currentCol++; // Move to the right
            } else { // If the value below is less than or equal to the value to the right
                youngTableau[currentRow][currentCol] = bVal; // Replace the current position with the value below
                youngTableau[currentRow + 1][currentCol] = inf; // Replace the value below with infinity
                currentRow++; // Move down
            }
        }

        /* remove the last position */
        youngTableau[currentRow][currentCol] = inf; // Replace the last position with infinity
        numFiniteInts--;

        return minVal;
	}

    public boolean find(int x) throws RuntimeException {
        /* returns true if x is in the tableau and false otherwise. It also prints the sequence of elements (including ”infinity”) 
        that where compared with x (the element in each probed array
        position should appear only once in the sequence). If the tableau is empty or if
        x is larger than or equal to the value that represents ∞, the method throws an
        exception with a message indicating that.
        */

        /* error handling */
		if (isEmpty()) { // nothing to find here
            throw new RuntimeException("The tableau is empty.");
        } else if (x >= inf) { // if x is larger than or equal to infinity, it's not in the tableau
            throw new RuntimeException("x is larger or equal to 'infinity'.");
        }
		
        /* intializations */
		int currentRow = 0; // Initialize the row index to start from the top
        int currentCol = youngTableau[0].length - 1; // Initialize the column index to start from the right
        String sequence = ""; // Initialize an empty string to store the sequence of elements compared

        /* Loop until we find value x or if we have passed all values */
        while (currentRow < youngTableau.length && currentCol >= 0) {
            
            int currentElement = youngTableau[currentRow][currentCol]; // Store current element

            sequence += currentElement + ", "; // Append the current element to the sequence
            if (currentElement == x) { // value x has been found
                sequence = sequence.substring(0, sequence.length() - 2); // remove the last comma and space
                System.out.println(sequence.trim());
                return true;
            }

            /* Move to the next position */
            if (currentElement < x) { // search in the row below if less than x
                currentRow++;
            } else { // if greater or equal to x, search in the column to the left
                currentCol--;
            }
        }
        // cannot find x: print the sequence and return false
        sequence = sequence.substring(0, sequence.length() - 2); // remove the last comma and space
        System.out.println(sequence.trim());
        return false;
	}

    public int readMin() throws RuntimeException {
        /* returns the smallest element
        without removing it from the tableau. If the tableau is empty, it throws an excep-
        tion with a message indicating that. */

        /* error handling */
        if (isEmpty()) {
            throw new RuntimeException("The tableau is empty.");
        }
        
        return youngTableau[0][0]; // the smallest element is at the top left of the tableau
    }

    public String toString() {
        /* returns a String that lists all the numbers in the
        matrix including ”infinity” in raster scan order (each row left to right, starting
        with the row on top), separated by a comma and a space. */
        String result = "";
        for (int i = 0; i < youngTableau.length; i++) {
            for (int j = 0; j < youngTableau[i].length; j++) {
                if (youngTableau[i][j] == inf) {
                    result += "inf, ";
                }
                else
                result += youngTableau[i][j] + ", ";
            }
        }
        // remove the last comma and space
        result = result.substring(0, result.length() - 2);
        return result;
    }
}
