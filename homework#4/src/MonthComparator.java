
/**
 * Program Name: MonthComparator.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains MonthComparator object used to compare Date objects based on their month.
 * <p>
 */

import java.util.Comparator;

public class MonthComparator implements Comparator<Date> {
	/**
	 * Compares Date parameters based on the months
	 */
	@Override
	public int compare(Date o1, Date o2) {
		if (o1.getMonth() == o2.getMonth()) {
			if (o1.getDay() == o2.getDay()) {
				return (o1.getYear() - o2.getYear());
			}
			return o1.getDay() - o2.getDay();
		}
		return o1.getMonth() - o2.getMonth();
	}

}
