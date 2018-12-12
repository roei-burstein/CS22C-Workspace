/**
 * Program Name: YearVisitor.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the Visitor object that visits each date object's year variable.
 * <p>
 */

public class YearVisitor implements Visitor<Date>{

	/**
	 * Visits date object's year variable
	 */
	@Override
	public void visit(Date obj) {
		System.out.println(obj.getYear() + "-" + obj.getMonth() + "-" + obj.getDay());
	}

}
