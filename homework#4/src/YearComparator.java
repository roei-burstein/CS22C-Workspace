/**
 * Program Name: YearComparator.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains YearComparator object used to compare Date objects based on their year.
 * <p>
 */
import java.util.Comparator;

public class YearComparator implements Comparator<Date> {
	/**
	 * Compares Date parameters based on the years
	 */
	@Override
	public int compare(Date o1, Date o2) {
		return o1.compareTo(o2);
	}

}
