
//Example of an Employee class (to be used in main below)
class Employee {
	public static final int MAX_LEN = 50;

	private String name;
	private int ss;

	public Employee(String name, int ss) {
		this();
		setName(name);
		setSS(ss);
	}

	public Employee() {
		name = "undefined";
		ss = 0;
	}

	String getName() {
		return name;
	}

	int getSS() {
		return ss;
	}

	boolean setName(String name) {
		if (name == null)
			return false;
		if (name.length() > MAX_LEN)
			return false;
		this.name = name;
		return true;
	}

	boolean setSS(int ss) {
		if (ss < 0 || ss > 999999999)
			return false;
		this.ss = ss;
		return true;
	}

	public String toString() {
		return name + " (" + ss + ")";
	}

	public boolean equals(Object rhs) // UPDATED!
	{
		return name.equalsIgnoreCase(((Employee) rhs).name);
	}

	public int hashCode() {
		return ss;
		// return name.hashCode(); // another possibility
	}
};

public class HashTableTester {

	// ------- main --------------
	public static void main(String[] args) throws Exception {
		// first set of tests --------------------
		HashSC<Employee> hashTable = new HashSC<Employee>();

		Employee e1 = new Employee("Jane Austin", 1), e2 = new Employee("Rene Descartes", 2),
				e3 = new Employee("John Locke", 3);

		if (hashTable.insert(e1))
			System.out.println("inserted " + e1);
		if (hashTable.insert(e1))
			System.out.println("inserted " + e1);
		if (hashTable.insert(e2))
			System.out.println("inserted " + e2);
		if (hashTable.insert(e2))
			System.out.println("inserted " + e2);
		if (hashTable.insert(e3))
			System.out.println("inserted " + e3);
		if (hashTable.insert(e3))
			System.out.println("inserted " + e3);

		System.out.println(hashTable.size());

		if (hashTable.contains(e3))
			System.out.println("contains " + e3);

		if (hashTable.remove(e1))
			System.out.println("removed " + e1);
		if (hashTable.remove(e1))
			System.out.println("removed " + e1);
		if (hashTable.remove(e2))
			System.out.println("removed " + e2);
		if (hashTable.remove(e2))
			System.out.println("removed " + e2);
		if (hashTable.remove(e3))
			System.out.println("removed " + e3);
		if (hashTable.remove(e3))
			System.out.println("removed " + e3);
		System.out.println(hashTable.size());

		if (hashTable.contains(e3))
			System.out.println("contains " + e3);

		// second set of tests --------------------

		HashSC<String> hashTable2 = new HashSC<String>();
		String substrate = "asdlkfj asdoiBIUYVuwer slkdjLJfwoe89)B)(798rjMG0293lkJLJ42lk3j)(*";
		String[] strArray = new String[500];
		int k, limit;

		substrate = substrate + substrate;

		for (k = 0; k < substrate.length() - 6; k++)
			strArray[k] = substrate.substring(k, k + 5);
		limit = k;

		hashTable2.setMaxLambda(.5);
		for (k = 0; k < limit; k++)
			if (hashTable2.insert(strArray[k]))
				System.out.println("inserted #" + k + " " + strArray[k]);
		System.out.println("\n#strings generated: " + limit + "\n#elements in ht: " + hashTable2.size());

		// third set of tests --------------------
		HashQP<Location> hashTable3 = new HashQP<Location>();
		Location[] locations = { new Location("DeAnza", "21250 Stevens Creek", 37.3192, -122.0453),
				new Location("Foothill", "12345 El Monte Rd", 37.3613, -122.1289),
				new Location("San Jose State", "1 Washington Square", 37.3353, -121.8813),
				new Location("West Valley College", "14000 Fruitvale Av", 37.2634, -122.0100) };
		System.out.println();
		for (int i = 0; i < locations.length; ++i) {
			if (hashTable3.insert(locations[i]))
				System.out.println("Inserted " + locations[i]);
			if (hashTable3.insert(locations[i]))
				System.out.println("Inserted " + locations[i]);
		}
		System.out.println(hashTable3.size());
		System.out.println("Number of collisions = " + hashTable3.getNumCollisions());

		if (hashTable3.contains(locations[0]))
			System.out.println("contains " + locations[0]);
		for (Location locElem : locations) {
			if (hashTable3.remove(locElem))
				System.out.println("removed " + locElem);
			if (hashTable3.remove(locElem))
				System.out.println("removed " + locElem);
		}
		System.out.println(hashTable3.size());
		if (hashTable3.contains(locations[0]))
			System.out.println("contains " + locations[0]);
		
		// fourth set of tests --------------------
		HashQP<String> hashTable4 = new HashQP<String>();
		String substrate2 = "asdlkfj asdoiBIUYVuwer slkdjLJfwoe89)B)(798rjMG0293lkJLJ42lk3j)(*";
		String[] strArray2 = new String[500];
		int k2, limit2;

		substrate2 = substrate2 + substrate2;

		for (k2 = 0; k2 < substrate2.length() - 6; k2++)
			strArray2[k2] = substrate2.substring(k2, k2 + 5);
		limit2 = k2;

		hashTable4.setMaxLambda(.5);
		for (k2 = 0; k2 < limit2; k2++) {
			if (hashTable4.insert(strArray2[k2]))
				System.out.println("inserted #" + k2 + " " + strArray2[k2]);
		}
		System.out.println("Number of collisions = " + hashTable4.getNumCollisions());

		System.out.println("\n#strings generated: " + limit2 + "\n#elements in ht: " + hashTable4.size());

	} // end main

}
