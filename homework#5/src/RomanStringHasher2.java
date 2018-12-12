/**
 * Program Name: RomanStringHasher2.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the other hash function for the HashTable object.
 * <p>
 */

public class RomanStringHasher2 implements Hasher<RomanNumeral>{

	/**
	 * Hashing function
	 * 
	 * @param r
	 *            RomanNumeral object to return the hash code of
	 * @return hash code of the object
	 */
	@Override
	public int hash(RomanNumeral r) {
		int k, retVal = 0;

		for (k = 0; k < r.getRomNumStr().length(); k++)
			retVal = 31 * retVal + r.getRomNumStr().charAt(k);

		return retVal;
	}

}
