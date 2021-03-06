/**
 * Program Name: Hasher.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the interface for the hashing functions.
 * <p>
 */

public interface Hasher<E> {
	/**
	 * method that returns that hash code for an object
	 * 
	 * @param elem
	 *            item whose hash code is being found
	 * 
	 * @return an integer with the hash code
	 */
	public int hash(E elem);
}
