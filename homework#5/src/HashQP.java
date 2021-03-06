/**
 * Program Name: HashQP.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the open addressing implementation of a HashTable.
 * <p>
 */

// HashQP Class Definition
import java.util.*;

import org.omg.PortableInterceptor.INACTIVE;

// HashQP class ------------------------
public class HashQP<E> extends HashTable<E> {
	protected static final int ACTIVE = 0; // index in array has object inside
	protected static final int EMPTY = 1; // index in array is empty
	protected static final int DELETED = 2; // index previously had object inside, but was deleted

	static final int INIT_TABLE_SIZE = 97; // Initial size of HashTable
	static final double INIT_MAX_LAMBDA = 0.49; // Initial ration between items in array and arraySize

	protected HashEntry<E>[] mArray; // HashTable of objects
	protected int mSize; // number of items in HashTable
	protected int mLoadSize;
	protected int mTableSize; // size of the array
	protected double mMaxLambda; // maximum lambda value

	/**
	 * HashQP Constructor with table size, hasher object, and comparator object
	 * 
	 * @param tableSize
	 *            size of initial HashTable
	 * @param hasher
	 *            Hasher object (representing specific hashing methods)
	 * @param comparator
	 *            Comparator object
	 */
	public HashQP(int tableSize, Hasher<E> hasher, Comparator<E> comparator) {
		super(hasher, comparator);
		mLoadSize = mSize = 0;
		if (tableSize < INIT_TABLE_SIZE)
			mTableSize = INIT_TABLE_SIZE;
		else
			mTableSize = nextPrime(tableSize);

		allocateArray(); // uses mTableSize;
		mMaxLambda = INIT_MAX_LAMBDA;
	}

	/**
	 * Default constructor, runs constructor with initial table size
	 * 
	 * @param hasher
	 *            Hasher object (representing specific hashing methods)
	 * @param comparator
	 *            Comparator object
	 */
	public HashQP(Hasher<E> hasher, Comparator<E> comparator) {
		this(INIT_TABLE_SIZE, hasher, comparator);
	}

	/**
	 * Inserts item into HashTable
	 * 
	 * @param x
	 *            item to be pushed into HashTable
	 * @return true if insertion was successful, false otherwise
	 */
	public boolean insert(E x) {
		int bucket = findPos(x);

		if (mArray[bucket].state == ACTIVE)
			return false;

		mArray[bucket].data = x;
		mArray[bucket].state = ACTIVE;
		mSize++;

		// check load factor
		if (++mLoadSize > mMaxLambda * mTableSize)
			rehash();

		return true;
	}

	/**
	 * Removes item from HashTable
	 * 
	 * @param x
	 *            item to be removed
	 * @return true if removal was successful, false otherwise
	 */
	public boolean remove(E x) {
		int bucket = findPos(x);

		if (mArray[bucket].state != ACTIVE)
			return false;

		mArray[bucket].state = DELETED;
		mSize--; // mLoadSize not dec'd because it counts any non-EMP location
		return true;
	}

	/**
	 * Checks if HashTable contains parameter element
	 * 
	 * @param x
	 *            item to be checked in the HashTable
	 * @return true if item is in HashTable, false if item is not in HashTable
	 */
	public boolean contains(E x) {
		return mArray[findPos(x)].state == ACTIVE;
	}

	/**
	 * Returns the number of elements in the HashTable
	 * 
	 * @return number of items in HashTable
	 */
	public int size() {
		return mSize;
	}

	/**
	 * Makes the HashTable empty
	 */
	public void makeEmpty() {
		int k, size = mArray.length;

		for (k = 0; k < size; k++)
			mArray[k].state = EMPTY;
		mSize = mLoadSize = 0;
	}

	/**
	 * Sets the maximum lambda variable
	 * 
	 * @param lam
	 *            lambda value to replace maximum lambda
	 * @return true if value is in range, false otherwise
	 */
	public boolean setMaxLambda(double lam) {
		if (lam < .1 || lam > INIT_MAX_LAMBDA)
			return false;
		mMaxLambda = lam;
		return true;
	}

	/**
	 * Additional tester statistics
	 */
	public void displayStatistics() {
		System.out.println("\nIn the HashQP class:\n");
		System.out.println("Table Size = " + mTableSize);
		;
		System.out.println("Number of entries = " + mSize);
		System.out.println("Load factor = " + (double) mSize / mTableSize);
		System.out.println("Number of collisions = " + this.numCollisions);
		System.out.println("Longest Collision Run = " + this.longestCollisionRun);
	}

	/**
	 * Gets the entry in the HashTable
	 * 
	 * @param target
	 *            item to be retrieved in the HashTable
	 * @return data of target entry
	 */
	@Override
	public E getEntry(E target) {
		int bucket = findPos(target);

		if (mArray[bucket].state != ACTIVE) {
			return null;
		} else {
			return mArray[bucket].data;
		}
	}

	/**
	 * Finds the index in the HashTable of the parameter object
	 * 
	 * @param x
	 *            object whose index is being searched
	 * @return index of parameter in HashTable
	 */
	protected int findPos(E x) {
		int kthOddNum = 1;
		int index = myHash(x);
		int localNumCollisions = 0;

		while (mArray[index].state != EMPTY && comparator.compare(mArray[index].data, x) != 0) {
			index += kthOddNum; // k squared = (k-1) squared + kth odd #
			kthOddNum += 2; // compute next odd #
			if (index >= mTableSize)
				index -= mTableSize;
			++numCollisions;
			++localNumCollisions;
		}

		longestCollisionRun = Math.max(longestCollisionRun, localNumCollisions);

		return index;
	}

	/**
	 * Change the size of HashTable array
	 */
	protected void rehash() {
		numCollisions = 0;
		longestCollisionRun = 0;

		// we save old list and size then we can reallocate freely
		HashEntry<E>[] oldArray = mArray;
		int k, oldTableSize = mTableSize;
		;

		mTableSize = nextPrime(2 * oldTableSize);

		// allocate a larger, empty array
		allocateArray(); // uses mTableSize;

		// use the insert() algorithm to re-enter old data
		mSize = mLoadSize = 0;
		for (k = 0; k < oldTableSize; k++)
			if (oldArray[k].state == ACTIVE)
				insert(oldArray[k].data);
	}

	/**
	 * Gets the number of collisions
	 * 
	 * @return number of collisions
	 */
	protected int getNumCollisions() {
		return numCollisions;
	}

	/**
	 * Hashing function for HashQP
	 * 
	 * @param x
	 *            item whose hash code is being returned
	 * @return hash code of parameter object
	 */
	protected int myHash(E x) {
		int hashVal;
		hashVal = hasher.hash(x) % mTableSize;

		if (hashVal < 0)
			hashVal += mTableSize;

		return hashVal;
	}

	/**
	 * Gets the next prime number
	 * 
	 * @param n
	 *            number whose next prime is being calculated
	 * @return prime number after the input one
	 */
	protected static int nextPrime(int n) {
		int k, candidate, loopLim;

		// loop doesn't work for 2 or 3
		if (n <= 2)
			return 2;
		else if (n == 3)
			return 3;

		for (candidate = (n % 2 == 0) ? n + 1 : n; true; candidate += 2) {
			// all primes > 3 are of the form 6k +/- 1
			loopLim = (int) ((Math.sqrt((double) candidate) + 1) / 6);

			// we know it is odd. check for divisibility by 3
			if (candidate % 3 == 0)
				continue;

			// now we can check for divisibility of 6k +/- 1 up to sqrt
			for (k = 1; k <= loopLim; k++) {
				if (candidate % (6 * k - 1) == 0)
					break;
				if (candidate % (6 * k + 1) == 0)
					break;
			}
			if (k > loopLim)
				return candidate;
		}
	}

	/**
	 * Allocates the array, fills HashTable array with blank HashEntry<E> objects
	 */
	void allocateArray() {
		int k;

		mArray = new HashEntry[mTableSize];
		for (k = 0; k < mTableSize; k++)
			mArray[k] = new HashEntry<E>();
	}

	class HashEntry<E> {
		public E data;
		public int state;

		/**
		 * HashEntry constructor
		 * 
		 * @param x
		 *            item to save into data
		 * @param st
		 *            item to save into state
		 */
		public HashEntry(E x, int st) {
			data = x;
			state = st;
		}

		/**
		 * Default HashEntry constructor, inputs default values into main constructor
		 */
		public HashEntry() {
			this(null, HashQP.EMPTY);
		}
	}

	/**
	 * Prints out the table in a readable format
	 */
	@Override
	public void displayTable() {
		for (int i = 0; i < mArray.length; i++) {
			System.out.print(i + ": ");
			if (mArray[i].state != ACTIVE) {
				System.out.print("----");
			} else {
				System.out.print(mArray[i].data);
			}

			System.out.println();
		}
	}
}
