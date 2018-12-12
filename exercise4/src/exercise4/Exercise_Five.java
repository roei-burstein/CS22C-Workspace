package exercise4;

public class Exercise_Five {
	LinkedQueue<String> queue = new LinkedQueue<String>();

	public static double prefixEvaluate(LinkedQueue q) {
		String currentToken;
		double left, right;
		if (!q.isEmpty()) {
			currentToken = (String) q.dequeue();
			if ("+-/*".contains(currentToken)) { // if the token is an operator
				left = prefixEvaluate(q);
				right = prefixEvaluate(q);
				switch (currentToken.charAt(0)) {
				case '+':
					return left + right;
				case '-':
					return left - right;
				case '*':
					return left * right;
				case '/':
					return left / right;
				default:
					throw new ArithmeticException("Illegal Operator");
				}
			} else
				return Double.parseDouble(currentToken);
		} else
			return 0;

	}
}
