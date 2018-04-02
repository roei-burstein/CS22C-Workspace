/**
 * Program Name: RomanStringComparator.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, March 11, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the comparator between two RomanNumeral Strings.
 * <p>
 */

import java.util.Comparator;

public class RomanStringComparator implements Comparator<RomanNumeral> {

	/**
	 * Compares the values of the two RomanNumeral parameter objects
	 * 
	 * @param o1
	 *            first RomanNumeral object
	 * @param o2
	 *            second RomanNumeral object
	 * @return 0 if items are the same
	 */
	@Override
	public int compare(RomanNumeral o1, RomanNumeral o2) {
		return o1.getRomNumStr().compareToIgnoreCase(o2.getRomNumStr());
	}

}
