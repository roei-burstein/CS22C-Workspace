/**
 * Program Name: MonthVisitor.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the Visitor object that visits each date object's month variable.
 * <p>
 */

public class MonthVisitor implements Visitor<Date>{

	/**
	 * Visits date object's month variable
	 */
	@Override
	public void visit(Date obj) {
		System.out.println((obj.toString()));
	}

}
