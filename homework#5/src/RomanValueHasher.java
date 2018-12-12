/**
 * Program Name: RomanValueHasher.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the Hashing function for RomanNumeral objects.
 * <p>
 */

public class RomanValueHasher implements Hasher<RomanNumeral> {

	@Override
	public int hash(RomanNumeral r) {
		return (Integer) r.getValue();
	}

}
