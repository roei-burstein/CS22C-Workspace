package exercise6;

public class exercise62 {
	// Example of timing simple sort algorithms
	public static int MIN_SIZE = 5000;
	public static int MAX_SIZE = 40000;

	// PUT BUBBLE SORT AND SELECTION SORT HERE
	private static void selectionSort(Integer[] arrayOfInts2) {
		for (int i = 0; i < arrayOfInts2.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arrayOfInts2.length; j++) {
				if (arrayOfInts2[j] < arrayOfInts2[minIndex])
					minIndex = j;
			}

			int minValue = arrayOfInts2[minIndex];
			arrayOfInts2[minIndex] = arrayOfInts2[i];
			arrayOfInts2[i] = minValue;
		}
	}

	private static void bubbleSort(Integer[] arrayOfInts1) {
		int placeHolder;
		for (int i = 0; i < arrayOfInts1.length; i++) {
			for (int j = 1; j < arrayOfInts1.length - i; j++) {
				if (arrayOfInts1[j - 1] > arrayOfInts1[j]) { // if left integer is greater than right integer
					// swap
					placeHolder = arrayOfInts1[j - 1];
					arrayOfInts1[j - 1] = arrayOfInts1[j];
					arrayOfInts1[j] = placeHolder;
				}
			}
		}
	}

	// --------------- main ---------------
	public static void main(String[] args) {
		int k, // index for array
				arraySize; // size of array (changes)
		long startTime, stopTime; // for timing
		double elapsedTime = 0; // for timing
		Integer[] arrayOfInts1 = null; // for dynamic array
		Integer[] arrayOfInts2 = null; // copy of dynamic array elements
		for (arraySize = MIN_SIZE; arraySize <= MAX_SIZE; arraySize *= 2) {
			// allocate array and stuff will values
			arrayOfInts1 = new Integer[arraySize];
			arrayOfInts2 = new Integer[arraySize];
			for (k = 0; k < arraySize; k++) { // THIS USES AUTO-BOXING, SO YOU NEED AT LEAST Java 6 FOR THIS
				arrayOfInts1[k] = arrayOfInts2[k] = (int) (Math.random() * arraySize);
			}
			startTime = System.nanoTime(); // start timing bubble sort----------
			// sort using a bubble sort
			bubbleSort(arrayOfInts1);
			stopTime = System.nanoTime(); // stop timing ---------------------
			elapsedTime = (double) (stopTime - startTime) / 1000000.0;
			System.out.println("\nN: " + arraySize + " Bubble Sort Time: " + elapsedTime + " milliseconds.");
			startTime = System.nanoTime(); // start timing selection sort-------
			// sort using a bubble sort
			selectionSort(arrayOfInts2);
			stopTime = System.nanoTime(); // stop timing ---------------------
			elapsedTime = (double) (stopTime - startTime) / 1000000.0;
			System.out.println("\nN: " + arraySize + " Selection Sort Time: " + elapsedTime + " milliseconds.");
		} // end for
	}// end main
/*	
Answers to Questions:
1. For Bubble Sort, when the size doubled, my runtime averaged about 3.5x the previous. 
For Selection Sort, doubling the size the first time gave me 2.5x and then exponentially grew.

2. I did not expect the timing results to be so different from one another.

3. According to the Big-Oh analysis, they should have both had similar runtimes (both are O(n^2)). '
I also did not expect as constant of a time change in bubble sort, which was pretty consistent in its growth. 
Execution Results:

N: 5000 Bubble Sort Time: 154.531594 milliseconds.

N: 5000 Selection Sort Time: 25.827705 milliseconds.

N: 10000 Bubble Sort Time: 541.704002 milliseconds.

N: 10000 Selection Sort Time: 52.210531 milliseconds.

N: 20000 Bubble Sort Time: 1590.009164 milliseconds.

N: 20000 Selection Sort Time: 238.237152 milliseconds.

N: 40000 Bubble Sort Time: 5370.938869 milliseconds.

N: 40000 Selection Sort Time: 1038.07757 milliseconds.
 */
} // end class Exercise6_2
