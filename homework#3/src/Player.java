/**
 * Program Name: Player.java
 * 
 * @author Roei Burstein 
 * Current Date: Thursday, February 15, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the player object and all of its
 * helper methods
 * <p>
 */

public class Player {
	private int id; // id number for the player
	private int total; // total points the player has
	private int currentValue; // value of the last wheel result they spun

	/**
	 * Default Player constructor
	 */
	public Player() {
	} // end Player

	/**
	 * Player constructor with 3 inputs
	 * 
	 * @param i
	 *            id number
	 * @param t
	 *            initial total score
	 * @param c
	 *            initial current value
	 */
	public Player(int i, int t, int c) {
		id = i;
		total = t;
		currentValue = c;
	} // end Player

	/**
	 * Mutator method for the id variable
	 * 
	 * @param i
	 *            id to change current one
	 */
	public void setId(int i) {
		id = i;
	} // end setId

	/**
	 * Mutator method for the total variable
	 * 
	 * @param t
	 *            total value to change current one
	 */
	public void setTotal(int t) {
		total = t;
	} // end setTotal

	/**
	 * Mutator method for the currentValue variable
	 * 
	 * @param c
	 *            value to change current one
	 */
	public void setCurrentValue(int c) {
		currentValue = c;
	} // end setCurrentValue

	/**
	 * Accessor method for the id variable
	 * 
	 * @return player id number
	 */
	public int getId() {
		return id;
	} // end getId

	/**
	 * Accessor method for the total variable
	 * 
	 * @return player's total points
	 */
	public int getTotal() {
		return total;
	} // end getTotal

	/**
	 * Accessor method for the currentValue variable
	 * 
	 * @return current value of last spin
	 */
	public int getCurrentValue() {
		return currentValue;
	} // end getCurrentValue

	/**
	 * Checks if two player objects equal each other
	 * 
	 * @return true if object equals local object, false otherwise
	 */
	public boolean equals(Object param) {
		if (param instanceof Player)
			return this.id == ((Player) param).id;
		return false;
	} // end equals

	/**
	 * Prints out player information
	 * 
	 * @return String with player information
	 */
	public String toString() {
		return "Player: ID# " + id + ", Total = " + total + ", Current value = " + currentValue;
	} // end toString

} // end Player class
