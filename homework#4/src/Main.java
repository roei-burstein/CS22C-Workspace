/**
 * Program Name: Main.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program runs the testers from all the other classes.
 * <p>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static Scanner userScanner = new Scanner(System.in); // Scanner that contains the file information
	static BST<Date> bst1 = new BST<Date>(new YearComparator()); // Year ordered Binary Search Tree
	static BST<Date> bst2 = new BST<Date>(new MonthComparator()); // Month ordered Binary Search Tree

	/**
	 * Main method, runs all the testers
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		userScanner = openInputFile();
		if (userScanner == null)
			System.exit(1);
		Date date = readFromFile(userScanner);
		System.out.println("\nYear-ordered tree has: ");
		bst1.inorder(new YearVisitor());

		System.out.println("\nMonth-ordered tree has: ");
		bst2.inorder(new MonthVisitor());

		System.out.println("\nYear-ordered indented tree: ");
		bst1.writeIndentedTree(System.out);

		System.out.println("\nMonth-ordered indented tree: ");
		bst2.writeIndentedTree(System.out);

		System.out.println("Student Testing Year-ordered tree: ");
		displayMethod(bst1, date);

		System.out.println("Student Testing Month-ordered tree: ");
		displayMethod(bst2, date);

		System.out.println("\nPostorder traversal of updated Year-ordered tree: ");
		bst1.postorder(new YearVisitor());

		System.out.println("\nNow the updated Month-ordered indented tree has");
		bst2.writeIndentedTree(System.out);

		testMore(bst1);
		testMore(bst2);
	}

	/**
	 * Tester method which uses bst methods
	 * 
	 * @param bst
	 *            Binary search tree used in testing
	 * @param date
	 *            Date object used in testing
	 */
	public static void displayMethod(BST<Date> bst, Date date) {
		System.out.println("\nDate parameter: " + date);
		Date date2 = date;
		System.out.println("getEntry() passing a copy of the parameter returns: " + bst.getEntry(date2));
		Date almostLastDate = new Date(bst.getLast().getMonth(), bst.getLast().getDay(), bst.getLast().getYear() + 1);
		System.out.println("Created a copy of the last Date, with year+1: " + almostLastDate);
		System.out
				.println("getEntry(); passing a copy of the last with year+1 returns: " + bst.getEntry(almostLastDate));
		System.out.println("insert() passing the copy of the last with year+1 returns: " + bst.insert(almostLastDate));
	}

	/**
	 * Reads the scanner, puts information into date objects, and pushes objects to
	 * bsts
	 * 
	 * @param sc
	 *            Scanner with the file information
	 * @return Last date read from the scanner
	 */
	private static Date readFromFile(Scanner sc) {
		if (sc == null)
			System.out.println("ERROR: FILE NOT FOUND");
		Date date = null;
		while (sc.hasNext()) {
			date = new Date();
			int[] dateArray = new int[3];
			for (int i = 0; i < dateArray.length; i++) {
				dateArray[i] = sc.nextInt();
			}
			if (date.setDate(dateArray[0], dateArray[1], dateArray[2])) {
				bst1.insert(date);
				bst2.insert(date);
			} else {
				System.out.println("Invalid date in file: " + dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2]);
			}
		}
		return date;
	}

	/**
	 * Opens a text file for input
	 * 
	 * @return Scanner with file data
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
	}

	/**
	 * Additional testing method
	 * 
	 * @param tree
	 *            BST used in testing
	 */
	public static void testMore(BST<Date> tree) {
		Date firstDate, lastDate = null;
		Date testDate1 = new Date(12, 31, 1900);
		Date testDate2 = new Date(1, 2, 2000);

		System.out.println("\nClearing tree:");
		firstDate = tree.getFirst();
		while (firstDate != null) {
			lastDate = tree.getLast();
			if (tree.delete(firstDate))
				System.out.println("Removed " + firstDate);
			if (firstDate != lastDate && tree.delete(lastDate))
				System.out.println("Removed " + lastDate);
			firstDate = tree.getFirst();
		} //
		System.out.println("\nTree is cleared, now writeIndentedTree displays: ");
		tree.writeIndentedTree(System.out);
		System.out.println("End writing tree\nNow adding back last 2 retrieved:");
		tree.insert(testDate1);
		tree.insert(testDate2);
		System.out.println("Now the tree has: ");
		tree.writeIndentedTree(System.out);
	} // end testMore()
	/* OUTPUT:
Enter the input filename: HW4 Test Input1.txt
Invalid date in file: 4-31-2009

Year-ordered tree has: 
1923-12-10
1933-11-10
1947-4-9
1949-11-15
1951-6-5
1960-2-1
1963-8-4
1972-2-25
1979-6-5

Month-ordered tree has: 
2/1/1960
2/25/1972
4/9/1947
6/5/1951
6/5/1979
8/4/1963
11/10/1933
11/15/1949
12/10/1923

Year-ordered indented tree: 
		3. 6/5/1979
	2. 2/25/1972
		3. 8/4/1963
			4. 2/1/1960
1. 6/5/1951
			4. 11/15/1949
		3. 4/9/1947
	2. 11/10/1933
		3. 12/10/1923

Month-ordered indented tree: 
		3. 12/10/1923
			4. 11/15/1949
	2. 11/10/1933
			4. 8/4/1963
		3. 6/5/1979
1. 6/5/1951
		3. 4/9/1947
	2. 2/25/1972
		3. 2/1/1960
Student Testing Year-ordered tree: 

Date parameter: 2/1/1960
getEntry() passing a copy of the parameter returns: 2/1/1960
Created a copy of the last Date, with year+1: 6/5/1980
getEntry(); passing a copy of the last with year+1 returns: null
insert() passing the copy of the last with year+1 returns: true
Student Testing Month-ordered tree: 

Date parameter: 2/1/1960
getEntry() passing a copy of the parameter returns: 2/1/1960
Created a copy of the last Date, with year+1: 12/10/1924
getEntry(); passing a copy of the last with year+1 returns: null
insert() passing the copy of the last with year+1 returns: true

Postorder traversal of updated Year-ordered tree: 
1923-12-10
1949-11-15
1947-4-9
1933-11-10
1960-2-1
1963-8-4
1980-6-5
1979-6-5
1972-2-25
1951-6-5

Now the updated Month-ordered indented tree has
			4. 12/10/1924
		3. 12/10/1923
			4. 11/15/1949
	2. 11/10/1933
			4. 8/4/1963
		3. 6/5/1979
1. 6/5/1951
		3. 4/9/1947
	2. 2/25/1972
		3. 2/1/1960

Clearing tree:
Removed 12/10/1923
Removed 6/5/1980
Removed 11/10/1933
Removed 6/5/1979
Removed 4/9/1947
Removed 2/25/1972
Removed 11/15/1949
Removed 8/4/1963
Removed 6/5/1951
Removed 2/1/1960

Tree is cleared, now writeIndentedTree displays: 
End writing tree
Now adding back last 2 retrieved:
Now the tree has: 
	2. 1/2/2000
1. 12/31/1900

Clearing tree:
Removed 2/1/1960
Removed 12/10/1924
Removed 2/25/1972
Removed 12/10/1923
Removed 4/9/1947
Removed 11/15/1949
Removed 6/5/1951
Removed 11/10/1933
Removed 6/5/1979
Removed 8/4/1963

Tree is cleared, now writeIndentedTree displays: 
End writing tree
Now adding back last 2 retrieved:
Now the tree has: 
1. 12/31/1900
	2. 1/2/2000
____________________________________________________
Enter the input filename: HW4 Test Input2.txt
Invalid date in file: 2-29-1941

Year-ordered tree has: 
1932-2-17
1934-5-11
1935-9-2
1936-1-9
1937-12-24
1940-4-16
1942-4-1
1953-5-28
1953-9-13
1955-8-25
1955-9-7
1962-1-17
1972-9-23
1973-3-18
1977-8-10
1977-8-11
1978-10-14
1984-7-30
1984-9-30
1987-4-28
1990-5-11
1994-1-21
1994-5-27
1994-11-8
1995-2-9
2000-8-13
2002-3-31
2004-12-17
2008-8-6
2010-7-4
2014-9-11
2017-8-22

Month-ordered tree has: 
1/9/1936
1/17/1962
1/21/1994
2/9/1995
2/17/1932
3/18/1973
3/31/2002
4/1/1942
4/16/1940
4/28/1987
5/11/1934
5/11/1990
5/27/1994
5/28/1953
7/4/2010
7/30/1984
8/6/2008
8/10/1977
8/11/1977
8/13/2000
8/22/2017
8/25/1955
9/2/1935
9/7/1955
9/11/2014
9/13/1953
9/23/1972
9/30/1984
10/14/1978
11/8/1994
12/17/2004
12/24/1937

Year-ordered indented tree: 
				5. 8/22/2017
			4. 9/11/2014
		3. 7/4/2010
			4. 8/6/2008
					6. 12/17/2004
				5. 3/31/2002
	2. 8/13/2000
				5. 2/9/1995
			4. 11/8/1994
		3. 5/27/1994
					6. 1/21/1994
				5. 5/11/1990
			4. 4/28/1987
				5. 9/30/1984
1. 7/30/1984
				5. 10/14/1978
			4. 8/11/1977
					6. 8/10/1977
				5. 3/18/1973
		3. 9/23/1972
					6. 1/17/1962
						7. 9/7/1955
				5. 8/25/1955
					6. 9/13/1953
			4. 5/28/1953
				5. 4/1/1942
					6. 4/16/1940
	2. 12/24/1937
				5. 1/9/1936
			4. 9/2/1935
		3. 5/11/1934
			4. 2/17/1932

Month-ordered indented tree: 
		3. 12/24/1937
					6. 12/17/2004
				5. 11/8/1994
						7. 10/14/1978
					6. 9/30/1984
			4. 9/23/1972
					6. 9/13/1953
							8. 9/11/2014
						7. 9/7/1955
							8. 9/2/1935
				5. 8/25/1955
					6. 8/22/2017
	2. 8/13/2000
		3. 8/11/1977
				5. 8/10/1977
			4. 8/6/2008
1. 7/30/1984
			4. 7/4/2010
		3. 5/28/1953
	2. 5/27/1994
				5. 5/11/1990
			4. 5/11/1934
		3. 4/28/1987
						7. 4/16/1940
					6. 4/1/1942
				5. 3/31/2002
					6. 3/18/1973
						7. 2/17/1932
							8. 2/9/1995
								9. 1/21/1994
			4. 1/17/1962
				5. 1/9/1936
Student Testing Year-ordered tree: 

Date parameter: 12/17/2004
getEntry() passing a copy of the parameter returns: 12/17/2004
Created a copy of the last Date, with year+1: 8/22/2018
getEntry(); passing a copy of the last with year+1 returns: null
insert() passing the copy of the last with year+1 returns: true
Student Testing Month-ordered tree: 

Date parameter: 12/17/2004
getEntry() passing a copy of the parameter returns: 12/17/2004
Created a copy of the last Date, with year+1: 12/24/1938
getEntry(); passing a copy of the last with year+1 returns: null
insert() passing the copy of the last with year+1 returns: true

Postorder traversal of updated Year-ordered tree: 
1932-2-17
1936-1-9
1935-9-2
1934-5-11
1940-4-16
1942-4-1
1953-9-13
1955-9-7
1962-1-17
1955-8-25
1953-5-28
1977-8-10
1973-3-18
1978-10-14
1977-8-11
1972-9-23
1937-12-24
1984-9-30
1994-1-21
1990-5-11
1987-4-28
1995-2-9
1994-11-8
1994-5-27
2004-12-17
2002-3-31
2008-8-6
2018-8-22
2017-8-22
2014-9-11
2010-7-4
2000-8-13
1984-7-30

Now the updated Month-ordered indented tree has
			4. 12/24/1938
		3. 12/24/1937
					6. 12/17/2004
				5. 11/8/1994
						7. 10/14/1978
					6. 9/30/1984
			4. 9/23/1972
					6. 9/13/1953
							8. 9/11/2014
						7. 9/7/1955
							8. 9/2/1935
				5. 8/25/1955
					6. 8/22/2017
	2. 8/13/2000
		3. 8/11/1977
				5. 8/10/1977
			4. 8/6/2008
1. 7/30/1984
			4. 7/4/2010
		3. 5/28/1953
	2. 5/27/1994
				5. 5/11/1990
			4. 5/11/1934
		3. 4/28/1987
						7. 4/16/1940
					6. 4/1/1942
				5. 3/31/2002
					6. 3/18/1973
						7. 2/17/1932
							8. 2/9/1995
								9. 1/21/1994
			4. 1/17/1962
				5. 1/9/1936

Clearing tree:
Removed 2/17/1932
Removed 8/22/2018
Removed 5/11/1934
Removed 8/22/2017
Removed 9/2/1935
Removed 9/11/2014
Removed 1/9/1936
Removed 7/4/2010
Removed 12/24/1937
Removed 8/6/2008
Removed 4/16/1940
Removed 12/17/2004
Removed 4/1/1942
Removed 3/31/2002
Removed 5/28/1953
Removed 8/13/2000
Removed 9/13/1953
Removed 2/9/1995
Removed 8/25/1955
Removed 11/8/1994
Removed 9/7/1955
Removed 5/27/1994
Removed 1/17/1962
Removed 1/21/1994
Removed 9/23/1972
Removed 5/11/1990
Removed 3/18/1973
Removed 4/28/1987
Removed 8/10/1977
Removed 9/30/1984
Removed 8/11/1977
Removed 7/30/1984
Removed 10/14/1978

Tree is cleared, now writeIndentedTree displays: 
End writing tree
Now adding back last 2 retrieved:
Now the tree has: 
	2. 1/2/2000
1. 12/31/1900

Clearing tree:
Removed 1/9/1936
Removed 12/24/1938
Removed 1/17/1962
Removed 12/24/1937
Removed 1/21/1994
Removed 12/17/2004
Removed 2/9/1995
Removed 11/8/1994
Removed 2/17/1932
Removed 10/14/1978
Removed 3/18/1973
Removed 9/30/1984
Removed 3/31/2002
Removed 9/23/1972
Removed 4/1/1942
Removed 9/13/1953
Removed 4/16/1940
Removed 9/11/2014
Removed 4/28/1987
Removed 9/7/1955
Removed 5/11/1934
Removed 9/2/1935
Removed 5/11/1990
Removed 8/25/1955
Removed 5/27/1994
Removed 8/22/2017
Removed 5/28/1953
Removed 8/13/2000
Removed 7/4/2010
Removed 8/11/1977
Removed 7/30/1984
Removed 8/10/1977
Removed 8/6/2008

Tree is cleared, now writeIndentedTree displays: 
End writing tree
Now adding back last 2 retrieved:
Now the tree has: 
1. 12/31/1900
	2. 1/2/2000
*/
}
