/**
 * Program Name: RomanValueComparator.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the comparator for two RomanNumeral objects.
 * <p>
 */

import java.util.Comparator;

public class RomanValueComparator implements Comparator<RomanNumeral> {

	/**
	 * Compares the two RomanNumeralObjects
	 * 
	 * @param RomanNumeral
	 *            object 1
	 * @param RomanNumeral
	 *            object 2
	 * @return difference of the RomanNumeral values
	 */
	@Override
	public int compare(RomanNumeral o1, RomanNumeral o2) {
		return o1.getValue() - o2.getValue();
	}

}
