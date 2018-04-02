
/**
 * Program Name: WheelGame.java
 * @author Roei Burstein
 * Current Date: Thursday, February 15, 2018
 * Computer Operating System: Mac OS High Sierra Version 10.13.2
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program runs the Wheel Game and tests the circular Linked List
 * <p>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WheelGame {
	private ArrayList<Integer> wheelArrayList = new ArrayList<Integer>();
	private int currentIndex; // index of the list item for the wheel
	private int currentValue; // current wheel result
	private int numPlayers; // number of players in game
	private int maxSpins; // maximum spins before game times out
	private CircularLList2<Player> playerList = new CircularLList2<Player>(); // list of player objects
	public static Scanner userScanner = new Scanner(System.in); // scanner that holds the file contents

	/**
	 * main method calls the game and list tester methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		userScanner = openInputFile();
		if (userScanner == null)
			System.exit(1);

		WheelGame wg = new WheelGame(userScanner, 4);
		wg.playGame();
		testCircularList();
	} // end main

	/**
	 * WheelGame constructor creates a new wheel game
	 * 
	 * @param sc
	 *            scanner that contains file contents
	 * @param numPlyrs
	 *            number of players in the game
	 */
	public WheelGame(Scanner sc, int numPlyrs) {
		numPlayers = numPlyrs;
		maxSpins = (numPlyrs * 10);
		readWheelFromFile(sc);
		createCircularLList2();
	} // end WheelGame

	/**
	 * Spins the wheel by selecting a random index and getting a wheel result with
	 * that index
	 */
	public void spinWheel() {
		currentIndex = (int) (Math.random() * wheelArrayList.size());
		currentValue = wheelArrayList.get(currentIndex);
	} // end spinWheel

	/**
	 * Accessor method for the currentValue
	 * 
	 * @return currentValue of the wheel spin
	 */
	public int getCurrentValue() {
		return currentValue;
	} // end getCurrentValue

	/**
	 * Accessor method for the numPlayers
	 * 
	 * @return number of players in the game
	 */
	public int getNumPlayers() {
		return numPlayers;
	} // end getNumPlayers

	/**
	 * Plays the wheel game and runs all of the iterations
	 */
	public void playGame() {
		int spinCounter = 0;
		playerList.startIterator();
		Player winner = null;
		while (numPlayers > 1 && spinCounter < maxSpins) {
			Player previousPlayer = playerList.peekPrev();
			Player currentPlayer = playerList.next();
			System.out.print("Spinning Wheel for ID # " + currentPlayer.getId() + "........ ");
			spinWheel();
			System.out.print("and it's " + getCurrentValue() + "!\n");
			if (getCurrentValue() < 0) {
				System.out.println("Reducing the previous Player's total by this amount");
				previousPlayer.setTotal(previousPlayer.getTotal() + getCurrentValue()); // add negative amount
				System.out.println("The previous player with ID# " + previousPlayer.getId() + " now has a total of "
						+ previousPlayer.getTotal() + "\n");
				if (previousPlayer.getTotal() < 0) {
					System.out.println("!!!!! Removing player with ID# " + previousPlayer.getId() + "!!!!!");
					playerList.remove(previousPlayer);
					numPlayers--;
				} // end if
			} // end if
			else if (getCurrentValue() == 0) {
				System.out.println("Subtracting the last of this Player's value from the Player's total");
				currentPlayer.setTotal(currentPlayer.getTotal() - currentPlayer.getCurrentValue());
				System.out.println("This player now has " + currentPlayer.getTotal() + "\n");
				if (currentPlayer.getTotal() < 0) {
					System.out.println("!!!!! Removing player with ID# " + currentPlayer.getId() + "!!!!!");
					playerList.remove(currentPlayer);
					numPlayers--;
				} // end if
			} // end else if
			else {
				System.out.println("Adding the wheel's current value to this Player");
				currentPlayer.setTotal(currentPlayer.getTotal() + getCurrentValue());
				System.out.println("This player now has " + currentPlayer.getTotal() + "\n");
			} // end else
			currentPlayer.setCurrentValue(currentValue);
			spinCounter++;
			if (numPlayers == 1)
				winner = currentPlayer;
		} // end while
		if (numPlayers == 1)
			System.out.println("Winner: " + winner.toString());
		else if (spinCounter == maxSpins)
			System.out.println("timed out, no winner");

	} // end playGame

	/**
	 * reads the scanner and saves contents into ArrayList
	 * 
	 * @param sc
	 *            scanner that contains file contents
	 */
	private void readWheelFromFile(Scanner sc) {
		wheelArrayList.clear();
		if (sc == null)
			System.out.println("ERROR: FILE NOT FOUND");
		else {
			while (sc.hasNextLine()) {
				wheelArrayList.add(Integer.parseInt((sc.nextLine())));
			} // end while
		} // end else
	} // end readWheelFromFile

	/**
	 * Creates the CircularLinkedList of players
	 */
	private void createCircularLList2() {
		for (int i = 1; i <= numPlayers; i++) {
			Player p = new Player(i, 500, 0);
			playerList.add(p);
		} // end for
	} // end createCircularLList2

	/**
	 * Opens and parses the input file.
	 * 
	 * @return Scanner object with the contents of the file.
	 */
	public static Scanner openInputFile() {
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try {
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe) {
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	} // end openInputFile

	/**
	 * Tests the Circular Linked List and all of its methods
	 */
	public static void testCircularList() {
		String strArray[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		CircularLList2<String> list;
		String tempStr1;

		// TEST LIST OF STRINGS
		list = new CircularLList2<String>();
		for (int i = 0; i < strArray.length; ++i) {
			list.add(i + 1, strArray[i]);
		} // end for
		System.out.println("\nThe test list has:");
		list.display();
		tempStr1 = list.getEntry("Tuesday");
		if (tempStr1 != null)
			System.out.println("Found " + tempStr1 + " in the list!");
		else
			System.out.println("Error in the program: Tuesday wasn't found");
		if (list.remove("Monday"))
			System.out.println("Monday was successfully removed from the list!");
		else
			System.out.println("Error in the program: Monday couldn't be removed");
		if (list.remove("Sunday"))
			System.out.println("Sunday was successfully removed from the list!");
		else
			System.out.println("Error in the program: Sunday couldn't be removed");
		if (list.remove("Thursday"))
			System.out.println("Thursday was successfully removed from the list!");
		else
			System.out.println("Error in the program: Thursday couldn't be removed");
		System.out.println("\nNow the test list has:");
		list.display();
		tempStr1 = list.getEntry("Mardi");
		if (tempStr1 != null)
			System.out.println("Found " + tempStr1 + " in the list????");
		else
			System.out.println("Good: Mardi wasn't found");

		list.add(1, "Monday");

		System.out.println("Testing iterator after adding a new node in front: ");
		list.startIterator();
		int max = list.getLength() * 2;
		for (int count = 0; count < max; ++count)
			System.out.println(list.next());

		// TEST LIST OF PLAYERS
		CircularLList2<Player> list2 = new CircularLList2<>();
		for (int i = 0; i < 7; ++i)
			list2.add(new Player(i, i + 100, i));
		System.out.println("Displaying test list of Players");
		list2.display();
		Player[] tempPlayers = new Player[4];
		for (int i = 0; i < tempPlayers.length; ++i)
			tempPlayers[i] = new Player(i * 2, 0, 0);
		Player foundPlayer;
		for (int i = 0; i < tempPlayers.length; ++i) {
			foundPlayer = list2.getEntry(tempPlayers[i]);
			if (foundPlayer != null)
				System.out.println(
						"Found " + foundPlayer + " which should be the same as \n      " + list2.getEntry(i * 2 + 1));
			else
				System.out.println(tempPlayers[i] + " NOT found (error in program).");
		} // end for i
	} // end testCircularList()

	/* EXECUTION RESULTS:
GAME TIME OUT
Enter the input filename: HW3TestInput1.txt
Spinning Wheel for ID # 1........ and it's 700!
Adding the wheel's current value to this Player
This player now has 1200

Spinning Wheel for ID # 2........ and it's 100!
Adding the wheel's current value to this Player
This player now has 600

Spinning Wheel for ID # 3........ and it's -500!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of 100

Spinning Wheel for ID # 4........ and it's -650!
Reducing the previous Player's total by this amount
The previous player with ID# 3 now has a total of -150

!!!!! Removing player with ID# 3!!!!!
Spinning Wheel for ID # 1........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has 500

Spinning Wheel for ID # 2........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has 0

Spinning Wheel for ID # 4........ and it's -650!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of -650

!!!!! Removing player with ID# 2!!!!!
Spinning Wheel for ID # 1........ and it's 50!
Adding the wheel's current value to this Player
This player now has 550

Spinning Wheel for ID # 4........ and it's 50!
Adding the wheel's current value to this Player
This player now has 550

Spinning Wheel for ID # 1........ and it's 250!
Adding the wheel's current value to this Player
This player now has 800

Spinning Wheel for ID # 4........ and it's -650!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of 150

Spinning Wheel for ID # 1........ and it's 900!
Adding the wheel's current value to this Player
This player now has 1050

Spinning Wheel for ID # 4........ and it's 900!
Adding the wheel's current value to this Player
This player now has 1450

Spinning Wheel for ID # 1........ and it's -500!
Reducing the previous Player's total by this amount
The previous player with ID# 4 now has a total of 950

Spinning Wheel for ID # 4........ and it's -650!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of 400

Spinning Wheel for ID # 1........ and it's 900!
Adding the wheel's current value to this Player
This player now has 1300

Spinning Wheel for ID # 4........ and it's 800!
Adding the wheel's current value to this Player
This player now has 1750

Spinning Wheel for ID # 1........ and it's 350!
Adding the wheel's current value to this Player
This player now has 1650

Spinning Wheel for ID # 4........ and it's 400!
Adding the wheel's current value to this Player
This player now has 2150

Spinning Wheel for ID # 1........ and it's 550!
Adding the wheel's current value to this Player
This player now has 2200

Spinning Wheel for ID # 4........ and it's 700!
Adding the wheel's current value to this Player
This player now has 2850

Spinning Wheel for ID # 1........ and it's -650!
Reducing the previous Player's total by this amount
The previous player with ID# 4 now has a total of 2200

Spinning Wheel for ID # 4........ and it's 700!
Adding the wheel's current value to this Player
This player now has 2900

Spinning Wheel for ID # 1........ and it's 900!
Adding the wheel's current value to this Player
This player now has 3100

Spinning Wheel for ID # 4........ and it's 550!
Adding the wheel's current value to this Player
This player now has 3450

Spinning Wheel for ID # 1........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has 2200

Spinning Wheel for ID # 4........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has 2900

Spinning Wheel for ID # 1........ and it's 850!
Adding the wheel's current value to this Player
This player now has 3050

Spinning Wheel for ID # 4........ and it's 400!
Adding the wheel's current value to this Player
This player now has 3300

Spinning Wheel for ID # 1........ and it's 300!
Adding the wheel's current value to this Player
This player now has 3350

Spinning Wheel for ID # 4........ and it's 450!
Adding the wheel's current value to this Player
This player now has 3750

Spinning Wheel for ID # 1........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has 3050

Spinning Wheel for ID # 4........ and it's 550!
Adding the wheel's current value to this Player
This player now has 4300

Spinning Wheel for ID # 1........ and it's -500!
Reducing the previous Player's total by this amount
The previous player with ID# 4 now has a total of 3800

Spinning Wheel for ID # 4........ and it's 400!
Adding the wheel's current value to this Player
This player now has 4200

Spinning Wheel for ID # 1........ and it's 250!
Adding the wheel's current value to this Player
This player now has 3300

Spinning Wheel for ID # 4........ and it's 150!
Adding the wheel's current value to this Player
This player now has 4350

Spinning Wheel for ID # 1........ and it's 50!
Adding the wheel's current value to this Player
This player now has 3350

Spinning Wheel for ID # 4........ and it's -600!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of 2750

Spinning Wheel for ID # 1........ and it's 550!
Adding the wheel's current value to this Player
This player now has 3300

timed out, no winner
___________________________________________________________
PREVIOUS PLAYER ELIMINATED
Enter the input filename: HW3TestInput2.txt
Spinning Wheel for ID # 1........ and it's 380!
Adding the wheel's current value to this Player
This player now has 880

Spinning Wheel for ID # 2........ and it's -2200!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of -1320

!!!!! Removing player with ID# 1!!!!!
Spinning Wheel for ID # 3........ and it's 400!
Adding the wheel's current value to this Player
This player now has 900

Spinning Wheel for ID # 4........ and it's 400!
Adding the wheel's current value to this Player
This player now has 900

Spinning Wheel for ID # 2........ and it's 240!
Adding the wheel's current value to this Player
This player now has 740

Spinning Wheel for ID # 3........ and it's 400!
Adding the wheel's current value to this Player
This player now has 1300

Spinning Wheel for ID # 4........ and it's 500!
Adding the wheel's current value to this Player
This player now has 1400

Spinning Wheel for ID # 2........ and it's 280!
Adding the wheel's current value to this Player
This player now has 1020

Spinning Wheel for ID # 3........ and it's -4400!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of -3380

!!!!! Removing player with ID# 2!!!!!
Spinning Wheel for ID # 4........ and it's 380!
Adding the wheel's current value to this Player
This player now has 1780

Spinning Wheel for ID # 3........ and it's 3200!
Adding the wheel's current value to this Player
This player now has 4500

Spinning Wheel for ID # 4........ and it's 800!
Adding the wheel's current value to this Player
This player now has 2580

Spinning Wheel for ID # 3........ and it's -5200!
Reducing the previous Player's total by this amount
The previous player with ID# 4 now has a total of -2620

!!!!! Removing player with ID# 4!!!!!
Winner: Player: ID# 3, Total = 4500, Current value = -5200
___________________________________________________________
CURRENT PLAYER ELIMINATED
Enter the input filename: HW3TestInput2.txt
Spinning Wheel for ID # 1........ and it's 600!
Adding the wheel's current value to this Player
This player now has 1100

Spinning Wheel for ID # 2........ and it's 580!
Adding the wheel's current value to this Player
This player now has 1080

Spinning Wheel for ID # 3........ and it's 600!
Adding the wheel's current value to this Player
This player now has 1100

Spinning Wheel for ID # 4........ and it's -5200!
Reducing the previous Player's total by this amount
The previous player with ID# 3 now has a total of -4100

!!!!! Removing player with ID# 3!!!!!
Spinning Wheel for ID # 1........ and it's 500!
Adding the wheel's current value to this Player
This player now has 1600

Spinning Wheel for ID # 2........ and it's 1800!
Adding the wheel's current value to this Player
This player now has 2880

Spinning Wheel for ID # 4........ and it's 1600!
Adding the wheel's current value to this Player
This player now has 2100

Spinning Wheel for ID # 1........ and it's 200!
Adding the wheel's current value to this Player
This player now has 1800

Spinning Wheel for ID # 2........ and it's 600!
Adding the wheel's current value to this Player
This player now has 3480

Spinning Wheel for ID # 4........ and it's 600!
Adding the wheel's current value to this Player
This player now has 2700

Spinning Wheel for ID # 1........ and it's 1400!
Adding the wheel's current value to this Player
This player now has 3200

Spinning Wheel for ID # 2........ and it's 3200!
Adding the wheel's current value to this Player
This player now has 6680

Spinning Wheel for ID # 4........ and it's -3600!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of 3080

Spinning Wheel for ID # 1........ and it's 400!
Adding the wheel's current value to this Player
This player now has 3600

Spinning Wheel for ID # 2........ and it's -3600!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of 0

Spinning Wheel for ID # 4........ and it's 1400!
Adding the wheel's current value to this Player
This player now has 4100

Spinning Wheel for ID # 1........ and it's 0!
Subtracting the last of this Player's value from the Player's total
This player now has -400

!!!!! Removing player with ID# 1!!!!!
Spinning Wheel for ID # 2........ and it's 500!
Adding the wheel's current value to this Player
This player now has 3580

Spinning Wheel for ID # 4........ and it's -4400!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of -820

!!!!! Removing player with ID# 2!!!!!
Winner: Player: ID# 4, Total = 4100, Current value = -4400
_____________________________________________________________________
WINNER
Enter the input filename: HW3TestInput2.txt
Spinning Wheel for ID # 1........ and it's -1200!
Reducing the previous Player's total by this amount
The previous player with ID# 4 now has a total of -700

!!!!! Removing player with ID# 4!!!!!
Spinning Wheel for ID # 2........ and it's -4000!
Reducing the previous Player's total by this amount
The previous player with ID# 1 now has a total of -3500

!!!!! Removing player with ID# 1!!!!!
Spinning Wheel for ID # 3........ and it's -2000!
Reducing the previous Player's total by this amount
The previous player with ID# 2 now has a total of -1500

!!!!! Removing player with ID# 2!!!!!
Winner: Player: ID# 3, Total = 500, Current value = -2000
_____________________________________________________________________
The test list has:
Monday
Tuesday
Wednesday
Thursday
Friday
Saturday
Sunday
Found Tuesday in the list!
Monday was successfully removed from the list!
Sunday was successfully removed from the list!
Thursday was successfully removed from the list!

Now the test list has:
Saturday
Tuesday
Wednesday
Friday
Good: Mardi wasn't found
Testing iterator after adding a new node in front: 
Monday
Monday
Monday
Monday
Monday
Monday
Monday
Monday
Monday
Monday
Displaying test list of Players
Player: ID# 0, Total = 100, Current value = 0
Player: ID# 1, Total = 101, Current value = 1
Player: ID# 2, Total = 102, Current value = 2
Player: ID# 3, Total = 103, Current value = 3
Player: ID# 4, Total = 104, Current value = 4
Player: ID# 5, Total = 105, Current value = 5
Player: ID# 6, Total = 106, Current value = 6
Found Player: ID# 0, Total = 100, Current value = 0 which should be the same as 
      Player: ID# 0, Total = 100, Current value = 0
Found Player: ID# 2, Total = 102, Current value = 2 which should be the same as 
      Player: ID# 2, Total = 102, Current value = 2
Found Player: ID# 4, Total = 104, Current value = 4 which should be the same as 
      Player: ID# 4, Total = 104, Current value = 4
Found Player: ID# 6, Total = 106, Current value = 6 which should be the same as 
      Player: ID# 6, Total = 106, Current value = 6
	 */
}
