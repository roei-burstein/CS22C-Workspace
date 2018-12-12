public class GenericStack<E> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<>();

	public static void main(String[] args) {
		GenericStack<String> stringGenericStack = new GenericStack<String>();
		stringGenericStack.push("red");
		stringGenericStack.push("yellow");
		stringGenericStack.push("purple");
		System.out.println(stringGenericStack.toString());
		while (!stringGenericStack.isEmpty()) {
			stringGenericStack.pop();
		}
	}

	public int getSize() {
		return list.size();
	}

	public E peek() {
		return list.get(getSize() - 1);
	}

	public void push(E o) { // puts parameter at the end of the ArrayList
		list.add(o);
	}

	public E pop() { // removes the last item in the ArrayList
		E o = list.get(getSize() - 1);
		list.remove(getSize() - 1);
		return o;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		return "stack: " + list.toString();
	}
}