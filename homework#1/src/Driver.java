/**
 * Program Name: Driver.java
 * @author Roei Burstein
 * Current Date: Tuesday, January 23, 2018
 * Computer Operating System: Mac OS High Sierra Version 10.13.2
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program runs the PostfixExpression program, 
 * and has the execution results posted down below.
 * <p>
 */

public class Driver {
	/**
	 * Main method 
	 * @param args
	 */
	public static void main(String[] args) {
		PostfixExpression p = new PostfixExpression();
		p.userScanner = p.openInputFile();
		if (p.userScanner == null)
			System.out.println("ERROR: FILE NOT FOUND");
		else {
			while (p.userScanner.hasNextLine()) {
				boolean isValidExpression = p.setExp(p.userScanner.nextLine());
				if (isValidExpression) {
					System.out.println("The postfix expression: " + p.getWholeExp());
					System.out.println("The infix equivalent: " + p.postfixToInfix());
					System.out.println("The result of the evaluation: " + p.getResult() + "\n");
				} else
					System.out.println("NOT A VALID POSTFIX EXPRESSION");
			} //end while
			p.stackTester();
		} //end else
	} //end main
	
	/*
	 * EXECUTION RESULTS: 
________________________________________________________________________
Enter the input filename: HW1 Input.txt
The postfix expression: 5.0 -7.0 *
The infix equivalent: (5.0*-7.0)
The result of the evaluation: -35.0

The postfix expression: 4.0 30. / 2 +
The infix equivalent: ((4.0/30.)+2)
The result of the evaluation: 2.1333333333333333

NOT A VALID POSTFIX EXPRESSION
The postfix expression: 25. 5. / 3. 3. * -
The infix equivalent: ((25./5.)-(3.*3.))
The result of the evaluation: -4.0

The postfix expression: -54.32
The infix equivalent: -54.32
The result of the evaluation: -54.32

The postfix expression: 3.5 0.4 +
The infix equivalent: (3.5+0.4)
The result of the evaluation: 3.9

NOT A VALID POSTFIX EXPRESSION
The postfix expression: 100.0 5. / 7.0 3.0 + 2.0 4.0 * - +
The infix equivalent: ((100.0/5.)+((7.0+3.0)-(2.0*4.0)))
The result of the evaluation: 22.0


Testing the ArrayStack:
Pushed A 1, size is now= 1
Pushed B 1, size is now= 2
Pushed C 1, size is now= 3
Pushed D 1, size is now= 4
Pushed E 1, size is now= 5
Pushed F 1, size is now= 6
Pushed G 1, size is now= 7
Pushed H 1, size is now= 8
Pushed I 1, size is now= 9
Pushed J 1, size is now= 10
Trying to push A 2
Trying to push B 2
Trying to push C 2
Trying to push D 2
Trying to push E 2
Trying to push F 2
Out of space, removing E 2
Pushed F 2
Trying to push G 2
Out of space, removing F 2
Pushed G 2
Trying to push H 2
Out of space, removing G 2
Pushed H 2
Trying to push I 2
Out of space, removing H 2
Pushed I 2
Trying to push J 2
Out of space, removing I 2
Pushed J 2
The size of the ArrayStack is now 15
Popping J 2
Popping D 2
Popping C 2
Popping B 2
Popping A 2
The size of the ArrayStack is now 10
Pushed A 3, size is now= 11
Popping A 3
Popping J 1
Popping I 1
Popping H 1
Popping G 1
Popping F 1
Popping E 1
Popping D 1
Popping C 1
Popping B 1
Popping A 1
The size of the ArrayStack is now 0

Testing the LinkedStack:
The size of the LinkedStack is 10
Popping J 4
Popping I 4
Popping H 4
Popping G 4
Popping F 4
The size of the LinkedStack is now 5
Popping E 4
Popping D 4
Popping C 4
Popping B 4
Popping A 4
The size of the LinkedStack is now 0
_______________________________________________________
Enter the input filename: HW1 Test Input1
The postfix expression: 123.456 7.8 -
The infix equivalent: (123.456-7.8)
The result of the evaluation: 115.656

The postfix expression: 3 2 * 5 4 * +
The infix equivalent: ((3*2)+(5*4))
The result of the evaluation: 26.0

The postfix expression: 9.8 12.3 -7.6 + /
The infix equivalent: (9.8/(12.3+-7.6))
The result of the evaluation: 2.085106382978723

The postfix expression: 4343.0 0.0 /
The infix equivalent: (4343.0/0.0)
The result of the evaluation: Infinity

The postfix expression: 1928.3746
The infix equivalent: 1928.3746
The result of the evaluation: 1928.3746

The postfix expression: 78.0 901.0 - 234.0 56.0 + *
The infix equivalent: ((78.0-901.0)*(234.0+56.0))
The result of the evaluation: -238670.0


Testing the ArrayStack:
Pushed A 1, size is now= 1
Pushed B 1, size is now= 2
Pushed C 1, size is now= 3
Pushed D 1, size is now= 4
Pushed E 1, size is now= 5
Pushed F 1, size is now= 6
Pushed G 1, size is now= 7
Pushed H 1, size is now= 8
Pushed I 1, size is now= 9
Pushed J 1, size is now= 10
Trying to push A 2
Trying to push B 2
Trying to push C 2
Trying to push D 2
Trying to push E 2
Trying to push F 2
Out of space, removing E 2
Pushed F 2
Trying to push G 2
Out of space, removing F 2
Pushed G 2
Trying to push H 2
Out of space, removing G 2
Pushed H 2
Trying to push I 2
Out of space, removing H 2
Pushed I 2
Trying to push J 2
Out of space, removing I 2
Pushed J 2
The size of the ArrayStack is now 15
Popping J 2
Popping D 2
Popping C 2
Popping B 2
Popping A 2
The size of the ArrayStack is now 10
Pushed A 3, size is now= 11
Popping A 3
Popping J 1
Popping I 1
Popping H 1
Popping G 1
Popping F 1
Popping E 1
Popping D 1
Popping C 1
Popping B 1
Popping A 1
The size of the ArrayStack is now 0

Testing the LinkedStack:
The size of the LinkedStack is 10
Popping J 4
Popping I 4
Popping H 4
Popping G 4
Popping F 4
The size of the LinkedStack is now 5
Popping E 4
Popping D 4
Popping C 4
Popping B 4
Popping A 4
The size of the LinkedStack is now 0
________________________________________________
Enter the input filename: HW1 Test Input2.txt
The postfix expression: 33. 444.0 /  
The infix equivalent: (33./444.0)
The result of the evaluation: 0.07432432432432433

NOT A VALID POSTFIX EXPRESSION
The postfix expression: 55.	6. *	77. -  
The infix equivalent: ((55.*6.)-77.)
The result of the evaluation: 253.0

NOT A VALID POSTFIX EXPRESSION
The postfix expression: 8.8 7.7 6.6 5.5 - / 4.4 + *
The infix equivalent: (8.8*((7.7/(6.6-5.5))+4.4))
The result of the evaluation: 100.32000000000002

NOT A VALID POSTFIX EXPRESSION
The postfix expression: -54321.01
The infix equivalent: -54321.01
The result of the evaluation: -54321.01


Testing the ArrayStack:
Pushed A 1, size is now= 1
Pushed B 1, size is now= 2
Pushed C 1, size is now= 3
Pushed D 1, size is now= 4
Pushed E 1, size is now= 5
Pushed F 1, size is now= 6
Pushed G 1, size is now= 7
Pushed H 1, size is now= 8
Pushed I 1, size is now= 9
Pushed J 1, size is now= 10
Trying to push A 2
Trying to push B 2
Trying to push C 2
Trying to push D 2
Trying to push E 2
Trying to push F 2
Out of space, removing E 2
Pushed F 2
Trying to push G 2
Out of space, removing F 2
Pushed G 2
Trying to push H 2
Out of space, removing G 2
Pushed H 2
Trying to push I 2
Out of space, removing H 2
Pushed I 2
Trying to push J 2
Out of space, removing I 2
Pushed J 2
The size of the ArrayStack is now 15
Popping J 2
Popping D 2
Popping C 2
Popping B 2
Popping A 2
The size of the ArrayStack is now 10
Pushed A 3, size is now= 11
Popping A 3
Popping J 1
Popping I 1
Popping H 1
Popping G 1
Popping F 1
Popping E 1
Popping D 1
Popping C 1
Popping B 1
Popping A 1
The size of the ArrayStack is now 0

Testing the LinkedStack:
The size of the LinkedStack is 10
Popping J 4
Popping I 4
Popping H 4
Popping G 4
Popping F 4
The size of the LinkedStack is now 5
Popping E 4
Popping D 4
Popping C 4
Popping B 4
Popping A 4
The size of the LinkedStack is now 0

	 */
}
