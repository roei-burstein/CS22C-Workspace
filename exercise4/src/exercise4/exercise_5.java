package exercise4;

public class exercise_5 {
	public static int numCalls = 0;

	public static void main(String[] args) {
		int main = rabbit(10);
		System.out.println(main);
		System.out.println("Result of calling exercise5(10): " + exercise5(10));
	}

	public static int rabbit(int n) {
		numCalls++;
		System.out.println("n = " + n + " for call # " + numCalls);
		if (n <= 2)
			return 1;
		else
			return rabbit(n - 1) + rabbit(n - 2);

	} // end rabbit

	public static int exercise5(int n) {
		int previous = 1;
		int current = 1;
		int next = 1;

		for (int i = 3; i <= n; i++) {
			next = current + previous; // exercise5(i)
			previous = current; // Get ready for next iteration
			current = next;
		} // end for

		return next;
	} // end exercise5
/* Question Answers:
1. The base case is on line 3, returning line 4.
2. The recursive case is on line 6, returning line 7.
3. It returns the sum of the previous two integers to n.
4. It returns the nth number in the sequence where each number in the sequence is the sum of the previous two integers before it.
5. This is a very bad and inefficient use of recursion, and it can be done much better with iterating instead.
6. 109, which means when n is 10, the recursion pattern runs 109 times.
	
Execution Results:
n = 10 for call # 1
n = 9 for call # 2
n = 8 for call # 3
n = 7 for call # 4
n = 6 for call # 5
n = 5 for call # 6
n = 4 for call # 7
n = 3 for call # 8
n = 2 for call # 9
n = 1 for call # 10
n = 2 for call # 11
n = 3 for call # 12
n = 2 for call # 13
n = 1 for call # 14
n = 4 for call # 15
n = 3 for call # 16
n = 2 for call # 17
n = 1 for call # 18
n = 2 for call # 19
n = 5 for call # 20
n = 4 for call # 21
n = 3 for call # 22
n = 2 for call # 23
n = 1 for call # 24
n = 2 for call # 25
n = 3 for call # 26
n = 2 for call # 27
n = 1 for call # 28
n = 6 for call # 29
n = 5 for call # 30
n = 4 for call # 31
n = 3 for call # 32
n = 2 for call # 33
n = 1 for call # 34
n = 2 for call # 35
n = 3 for call # 36
n = 2 for call # 37
n = 1 for call # 38
n = 4 for call # 39
n = 3 for call # 40
n = 2 for call # 41
n = 1 for call # 42
n = 2 for call # 43
n = 7 for call # 44
n = 6 for call # 45
n = 5 for call # 46
n = 4 for call # 47
n = 3 for call # 48
n = 2 for call # 49
n = 1 for call # 50
n = 2 for call # 51
n = 3 for call # 52
n = 2 for call # 53
n = 1 for call # 54
n = 4 for call # 55
n = 3 for call # 56
n = 2 for call # 57
n = 1 for call # 58
n = 2 for call # 59
n = 5 for call # 60
n = 4 for call # 61
n = 3 for call # 62
n = 2 for call # 63
n = 1 for call # 64
n = 2 for call # 65
n = 3 for call # 66
n = 2 for call # 67
n = 1 for call # 68
n = 8 for call # 69
n = 7 for call # 70
n = 6 for call # 71
n = 5 for call # 72
n = 4 for call # 73
n = 3 for call # 74
n = 2 for call # 75
n = 1 for call # 76
n = 2 for call # 77
n = 3 for call # 78
n = 2 for call # 79
n = 1 for call # 80
n = 4 for call # 81
n = 3 for call # 82
n = 2 for call # 83
n = 1 for call # 84
n = 2 for call # 85
n = 5 for call # 86
n = 4 for call # 87
n = 3 for call # 88
n = 2 for call # 89
n = 1 for call # 90
n = 2 for call # 91
n = 3 for call # 92
n = 2 for call # 93
n = 1 for call # 94
n = 6 for call # 95
n = 5 for call # 96
n = 4 for call # 97
n = 3 for call # 98
n = 2 for call # 99
n = 1 for call # 100
n = 2 for call # 101
n = 3 for call # 102
n = 2 for call # 103
n = 1 for call # 104
n = 4 for call # 105
n = 3 for call # 106
n = 2 for call # 107
n = 1 for call # 108
n = 2 for call # 109
55
Result of calling exercise5(10): 55
*/
}