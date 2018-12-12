/**
 * Program Name: Date.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the methods and variables
 * regarding the Date object.
 * <p>
 */
public class Date implements Comparable<Date> {

	static final int MIN_MONTH = 1; // First calendar month
	static final int MAX_MONTH = 12; // Last calendar month
	static final int MIN_YEAR = 1000; // First year allowed in tree
	static final int MAX_YEAR = 9999; // Last year allowed in tree
	static final int[] DAYS_IN_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // Array that represents
																								// amount of days in
																								// every month of the
																								// year

	private int month = 1; // initial month
	private int day = 1; // initial day
	private int year = 1000; // initial year

	/**
	 * Default constructor, does nothing
	 */
	public Date() {
	}

	/**
	 * Primary constructor, sets the global variables of Date
	 * 
	 * @param m
	 *            Month being set in Date
	 * @param d
	 *            Day being set in Date
	 * @param y
	 *            Year being set in Date
	 */
	public Date(int m, int d, int y) {
		setDate(m, d, y); // else leave default values
	}

	/**
	 * Checks whether or not a year is a leap year
	 * 
	 * @param y
	 *            Year being checked
	 * @return True if year is leap year, false otherwise
	 */
	public static boolean isLeapYear(int y) {
		return (y % 4 == 0 && y % 100 != 0 || y % 400 == 0);
	}

	/**
	 * Sets the date and changes month, day, and year variables
	 * 
	 * @param m
	 *            Month being set in Date
	 * @param d
	 *            Day being set in Date
	 * @param y
	 *            Year being set in Date
	 * @return True if valid date, false if invalid date
	 */
	public boolean setDate(int m, int d, int y) {
		int isLeap = 0;

		if (y >= MIN_YEAR && y <= MAX_YEAR && m >= MIN_MONTH && m <= MAX_MONTH) {
			if (m == 2 && isLeapYear(y))
				isLeap = 1;
			if (d >= 1 && d <= (DAYS_IN_MONTH[m] + isLeap)) {
				month = m;
				day = d;
				year = y;
				return true;
			}
		}
		return false; // leaves instance vars. as they were before
	} // end setDate

	/**
	 * Gets the month variable for a certain Date
	 * @return The month
	 */
	public int getMonth() {
		return month;
	}
	
	/**
	 * Gets the Day variable for a certain Date
	 * @return The day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets the year variable for a certain Date
	 * @return the Year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Prints out the date in a readable format
	 */
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	/**
	 * Compares the Date object parameter with this date
	 * @return A negative integer, zero, or a positive integer as this Date is less than, equal to, or greater than the specified Date. 
	 */
	@Override
	public int compareTo(Date param) {
		if (getYear() == param.getYear()) {
			if (getMonth() == param.getMonth()) {
				return (getDay() - param.getDay());
			}
			return (getMonth() - param.getMonth());
		}
		return (getYear() - param.getYear());
	}
}
