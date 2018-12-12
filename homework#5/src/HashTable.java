/**
 * Program Name: HashTable.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the Abstract class with methods for HashSC and HashQP.
 * <p>
 */

import java.util.*;

public abstract class HashTable<E> {
	   protected int numCollisions; 
	   protected int longestCollisionRun;
	   protected Hasher<E> hasher;
	   protected Comparator<E> comparator;
	   
	   public HashTable(Hasher<E> h, Comparator<E> c)
	   {
		   hasher = h;
		   comparator = c;
	   }
	   
	   public abstract E getEntry(E target);
	   public abstract boolean contains( E x);
	   public abstract void makeEmpty();

	public abstract boolean insert( E x);
	   public abstract boolean remove( E x);
	   public abstract int size();
	   public abstract boolean setMaxLambda( double lam );
	   public abstract void displayTable();
	   public abstract void displayStatistics();
}
