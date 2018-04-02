/**
 * Program Name: CircularLList2.java
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

public class CircularLList2<T> implements ListInterface<T>, MyIterator<T> {

	private Node firstNode; // Reference to first node of chain, NOT a dummy
	private int numberOfEntries; // number of entries in the linked list
	private Node currentNode; // for iterating

	/**
	 * CircularLList2 constructor, initializes the list
	 */
	public CircularLList2() {
		initializeDataFields();
	} // end default constructor

	/**
	 * clears the linked list
	 */
	@Override
	public void clear() {
		initializeDataFields();
	} // end clear

	/**
	 * adds a new node to the list with the entry parameter
	 * 
	 * @param newEntry
	 *            data portion of the new node
	 */
	@Override
	public void add(T newEntry) // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);

		if (isEmpty()) {
			firstNode = newNode;
			firstNode.setNextNode(firstNode);
			firstNode.setPrevNode(firstNode);
		} else // Add to end of non-empty list
		{
			Node lastNode = firstNode.getPrevNode(); // updated for Circular list
			newNode.setNextNode(firstNode);
			newNode.setPrevNode(lastNode);
			firstNode.setPrevNode(newNode);
			lastNode.setNextNode(newNode); // Make last node reference new node
		} // end if

		numberOfEntries++;
	} // end add

	/**
	 * adds a new node to the list with the entry parameter and the position of the
	 * new node
	 * 
	 * @param newPosition
	 *            location of the new node
	 * @param newEntry
	 *            data portion of the new node
	 */
	@Override
	public boolean add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);

			if (isEmpty()) // very first node
			{
				firstNode = newNode;
				firstNode.setNextNode(firstNode);
				firstNode.setPrevNode(firstNode);
			} // end if
			else if (newPosition == 1) // list isn't empty, insert at beginning
			{
				newNode.setNextNode(firstNode);
				newNode.setPrevNode(firstNode.getPrevNode());
				firstNode.setPrevNode(newNode);
				(firstNode.getPrevNode()).setNextNode(newNode);

				firstNode = newNode;
			} // end else if
			else // list is not empty
			{ // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				newNode.setPrevNode(nodeBefore);
				nodeAfter.setPrevNode(newNode);
				nodeBefore.setNextNode(newNode);
			} // end if

			numberOfEntries++;
			return true;
		} // end if
		else
			return false;
	} // end add

	/**
	 * removes node with data equal to the entry parameter from the linkedlist
	 * 
	 * @param anEntry
	 *            data of the node to be deleted
	 */
	@Override
	public boolean remove(T anEntry) {
		Node nodeToDelete;
		Node nodeBefore;
		Node nodeAfter;
		boolean found = false;

		nodeToDelete = getNode(anEntry);
		nodeBefore = nodeToDelete.getPrevNode();
		nodeAfter = nodeToDelete.getNextNode();
		if (nodeToDelete == null)
			return false;
		else if (getLength() == 1) {
			firstNode = null;
			currentNode = null;
		} // end else if
		else {
			nodeBefore.setNextNode(nodeAfter);
			nodeAfter.setPrevNode(nodeBefore);
			if (firstNode.getPrevNode().getNextNode() != firstNode)
				firstNode = nodeBefore;
		} // end else
		found = true;
		numberOfEntries--;

		return found;
	} // end remove

	/**
	 * removes node with position given by the parameter from the linkedlist
	 * 
	 * @param givenPosition
	 *            position of node to be deleted
	 */
	@Override
	public T remove(int givenPosition) {
		T result = null; // Return value

		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			if (givenPosition == 1) // Case 1: Remove first entry
			{
				result = firstNode.getData(); // Save entry to be removed
				if (numberOfEntries == 1) // One left?
				{
					firstNode = null;
					currentNode = null; // nothing left to iterate
				} else {
					Node lastNode = firstNode.getPrevNode();
					firstNode = firstNode.getNextNode(); // Remove entry
					lastNode.setNextNode(firstNode);
					firstNode.setPrevNode(lastNode);
				}
			} else // Case 2: Not first entry
			{
				Node nodeBefore = getNodeAt(givenPosition - 1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData(); // Save entry to be removed
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter); // Remove entry
				nodeAfter.setPrevNode(nodeBefore);
			} // end if

			numberOfEntries--; // Update count
			return result; // Return removed entry
		} else
			return null;
	} // end remove

	/**
	 * gets data from the node at a certain position
	 * 
	 * @param givenPosition
	 *            position of the node in the linkedlist
	 * @return data of the node at the position
	 */
	@Override
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			return getNodeAt(givenPosition).getData();
		} else
			return null;
	} // end getEntry

	/**
	 * gets data from the node with certain data
	 * 
	 * @param anEntry
	 *            data of the node in the linked list
	 * @return data of the node searched for
	 */
	public T getEntry(T anEntry) {
		if (getNode(anEntry) == null)
			return null;
		return getNode(anEntry).getData();
	} // end getEntry

	/**
	 * checks to see if the linkedlist contains a node with data as parameter
	 * 
	 * @param anEntry
	 *            data of the node in the linked list
	 * @return true if node with data is found, false otherwise
	 */
	@Override
	public boolean contains(T anEntry)// UPDATED FOR CIRCULAR DOUBLY LINKED LIST
	{
		boolean found = false;
		Node currentNode = firstNode;

		if (firstNode == null)
			return false; // UPDATED FOR CIRCULAR DOUBLY LINKED LIST

		do {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} while (!found && currentNode != firstNode);

		return found;
	} // end contains

	/**
	 * gets the number of entries in the list
	 * 
	 * @return number of nodes in the list
	 */
	@Override
	public int getLength() {
		return numberOfEntries;
	} // end getLength

	/**
	 * checks if there are no nodes in the list
	 * 
	 * @return true if there are no nodes in the list, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return numberOfEntries == 0;

	} // end isEmpty

	/**
	 * displays the linked list in a nice layout
	 */
	@Override
	public void display() {
		if (firstNode == null)
			return;

		Node currNode;

		currNode = firstNode;
		do {
			System.out.println(currNode.getData());
			currNode = currNode.getNextNode();
		} while (currNode != firstNode);
	} // end display

	/**
	 * Initializes the class's data fields to indicate an empty list
	 */
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	} // end initializeDataFields

	/**
	 * Returns a reference to the node at a given position; Precondition: The chain
	 * is not empty; 1 <= givenPosition <= numberOfEntries.
	 * 
	 * @param givenPosition
	 *            position to get the node at
	 * @return node at the given position
	 */
	private Node getNodeAt(int givenPosition) {
		if ((1 <= givenPosition) && (givenPosition <= numberOfEntries)) {
			Node currentNode = firstNode;

			// Traverse the chain to locate the desired node
			// (skipped if givenPosition is 1)
			for (int counter = 1; counter < givenPosition; counter++)
				currentNode = currentNode.getNextNode();

			return currentNode;
		} else
			return null;
	} // end getNodeAt

	/**
	 * get node with target entry
	 * 
	 * @param targetEntry
	 *            data of node to look for
	 * @return node being looked for
	 */
	private Node getNode(T targetEntry) {
		Node currNode = firstNode;

		for (int i = 0; i < numberOfEntries; ++i) {
			if (currNode.getData().equals(targetEntry))
				return currNode;
			currNode = currNode.getNextNode();
		}
		return null;
	} // end getNode

	/**
	 * begins iterating through the linkedlist
	 */
	@Override
	public void startIterator() {
		currentNode = firstNode;
	} // end startIterator

	/**
	 * checks to see if the list has a next node
	 * 
	 * @return true if list has next node, false otherwise
	 */
	@Override
	public boolean hasNext() {

		return (currentNode != null);
	} // end hasNext

	/**
	 * returns the data of currentNode and changes currentNode to the next node
	 * 
	 * @return currentNode
	 */
	@Override
	public T next() {
		if (currentNode != null) {
			T currentData = currentNode.getData();
			currentNode = currentNode.getNextNode();
			return currentData;
		}
		return null;
	} // end next

	/**
	 * looks at the data of the node before currentNode
	 * 
	 * @return data of previous node
	 */
	// ADDED to look backwards,
	public T peekPrev() {
		if (isEmpty())
			return null;
		return currentNode.getPrevNode().getData();
	}

	private class Node // DOUBLY LINKED LIST VERSION
	{
		private T data; // Entry in list
		private Node next; // Link to next node
		private Node prev; // Link to previous node

		/**
		 * Node constructor with data parameter.
		 * 
		 * @param dataPortion
		 *            Data of Node object.
		 */
		private Node(T dataPortion) {
			data = dataPortion;
			next = null;
		} // end constructor

		/**
		 * Node constructor with both data parameter and next Node Object.
		 * 
		 * @param dataPortion
		 *            Data of Node object.
		 * @param linkPortion
		 *            Link to next Node Object.
		 */
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} // end constructor

		/**
		 * Gets data of Node object.
		 * 
		 * @return Data of Node object.
		 */
		private T getData() {
			return data;
		} // end getData

		/**
		 * Sets the data variable of the Node object.
		 * 
		 * @param newData
		 *            Data object to save into variable.
		 */
		private void setData(T newData) {
			data = newData;
		} // end setData

		/**
		 * Gets the next Node in the linked list.
		 * 
		 * @return The next Node.
		 */
		private Node getNextNode() {
			return next;
		} // end getNextNode

		/**
		 * Sets the next Node in the list.
		 * 
		 * @param nextNode
		 *            Next Node in the list.
		 */
		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode

		/**
		 * Gets the previous Node in the linked list.
		 * 
		 * @return The previous Node.
		 */
		private Node getPrevNode() {
			return prev;
		}

		/**
		 * Sets the previous Node in the list.
		 * 
		 * @param prevNode
		 *            previous Node in the list.
		 */
		private void setPrevNode(Node prevNode) {
			prev = prevNode;
		}
	} // end Node

} // end CircularLList2
