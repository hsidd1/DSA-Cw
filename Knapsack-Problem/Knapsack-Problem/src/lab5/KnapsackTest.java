package lab5;

public class KnapsackTest {

	public static void main(String[] args) {
		// n is 11 so then there are 11 pairs. The second number is the weight.
		//This gives the greedy optimal
		System.out.println("This gives the optimal solution ");
		String testscode1 = "11 50 60 10 100 20 120 30 70 40 90 50 50 60 30 70 20 80 10 90 20 100 30 110"; 
		System.out.println("The string is " + testscode1);

		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(testscode1));
		
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(testscode1));
		
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(testscode1));
		
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(testscode1));
		
		// This gives the greedy suboptimal
		System.out.println("This gives the sub-optimal solution ");
		System.out.println();

		// First number is how many v and w. Second is weight.
		String testscode2 = "10 70 20 10 30 15 25 20 35 25 45 30 20 35 25 40 15 45 30 50 10 55";
		System.out.println("The string is " + testscode2);

		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(testscode2));
		
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(testscode2));
		
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(testscode2));
		
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(testscode2));

		// This uses all the weights 
		// First number is how many v and w. Second is weight.
		System.out.println();
		System.out.println("This uses all the weights ");
		String testscode3 = "10 80 15 10 25 20 35 15 20 10 30 25 20 30 10 40 25 50 15 30 30 25"; 
		System.out.println();

		System.out.println("The string is " + testscode3);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(testscode3));
		
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(testscode3));
		
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(testscode3));
		
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(testscode3));
		
		// This does not use all the weights 
		// First number is how many v and w. Second is weight.
		System.out.println();
		System.out.println("This does not use all the weights ");
		System.out.println();

		String testscode4 = "10 40 5 10 25 12 35 12 10 7 30 25 20 30 10 40 25 50 5 3 40 25";
		System.out.println("The string is " + testscode4);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(testscode4));
		
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(testscode4));
		
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(testscode4));
		
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(testscode4));
		
		

		
		
    }

	
}
