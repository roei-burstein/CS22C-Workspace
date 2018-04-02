
/**
 * Program Name: BST.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the methods and variables
 * regarding the BinarySearchTree.
 * <p>
 */

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {

	private boolean foundNode; // helper variable
	private Comparator<E> comparator; // private comparator instance variable

	/** Create a default binary tree */
	public BST(Comparator<E> c)// SEE HW#4 FOR WHAT TO CHANGE HERE
	{
		comparator = c;
	}

	/** Create a binary tree from an array of objects */
	public BST(Comparator<E> c, E[] objects) // SEE HW#4 FOR WHAT TO CHANGE HERE

	{
		comparator = c;
		for (int i = 0; i < objects.length; i++)
			insert(objects[i]);

	}

	@Override /** Returns true if the element is in the tree */
	public boolean contains(E e) {
		BinaryNode<E> current = root; // Start from the root

		while (current != null) {
			if (comparator.compare(e, current.getData()) < 0) {
				current = current.getLeftChild();
			} else if (comparator.compare(e, current.getData()) > 0) {
				current = current.getRightChild();
			} else // element matches current.getData()
				return true; // Element is found
		}

		return false;
	}

	@Override
	/**
	 * Returns the data of the Node that equals the parameter, null if not found.
	 */
	public E getEntry(E e) // YOU WRITE FOR HW#4
	{
		BinaryNode<E> node = _findNode(root, e);
		if (node != null)
			return node.getData();
		return null;
	}

	/**
	 * Finds particular node inside tree
	 * 
	 * @param node
	 *            Current node being compared
	 * @param e
	 *            Data of node being looked for
	 * @return Node being searched for
	 */
	private BinaryNode<E> _findNode(BinaryNode<E> node, E e) {
		if (node == null)
			return null;
		else if (comparator.compare(e, node.getData()) < 0) // *****CHANGE THIS for HW#4*****
			return _findNode(node.getLeftChild(), e);
		else if (comparator.compare(e, node.getData()) > 0) // *****CHANGE THIS for HW#4*****
			return _findNode(node.getRightChild(), e);
		else // found it!
			return node;
	}

	@Override
	/**
	 * Insert element e into the binary tree Return true if the element is inserted
	 * successfully
	 */
	public boolean insert(E e) {
		root = _insert(root, e); // calls private insert that YOU write for HW#4
		size++;
		return true; // Element inserted successfully
	}

	/**
	 * Private recursive method that returns an updated "root" node from where
	 * current node is
	 * 
	 * @param node
	 *            Current node being compared
	 * @param e
	 *            Data to be placed into node added to tree
	 * @return The node being added to tree
	 */
	private BinaryNode<E> _insert(BinaryNode<E> node, E e) {

		if (node == null) {
			return new BinaryNode<E>(e);
		} else if (comparator.compare(e, node.getData()) < 0) { // <, so go left
			node.setLeftChild(_insert(node.getLeftChild(), e));// recursive call
		} else { // >, so go right
			node.setRightChild(_insert(node.getRightChild(), e));// recursive call
		}
		return node;
	}

	@Override
	/**
	 * Delete an element from the binary tree. Return true if the element is deleted
	 * successfully Return false if the element is not in the tree
	 */
	public boolean delete(E e) {
		foundNode = false; // initialize boolean instance variable
		root = _delete(root, e); // call private method to do actual deletion

		if (foundNode) {
			size--;// Element deleted successfully
		}
		return foundNode;
	}

	/**
	 * Removes node from tree
	 * 
	 * @param node
	 *            Node being compared and iterated through the tree
	 * @param e
	 *            Data of node to remove
	 * @return Deleted node
	 */
	private BinaryNode<E> _delete(BinaryNode<E> node, E e) {
		if (node == null) {
			return null;
		}
		if (comparator.compare(e, node.getData()) < 0) // <, so go left
			node.setLeftChild(_delete(node.getLeftChild(), e));// recursive call
		else if (comparator.compare(e, node.getData()) > 0) // >, so go right
			node.setRightChild(_delete(node.getRightChild(), e));// recursive call
		else { // FOUND THE NODE
			foundNode = true;
			node = _deleteNode(node);
		}
		return node;
	} // end _delete

	/**
	 * Private method that either "moves up" the left or right child, OR replaces
	 * the data in the nodeToDelete with the data in the rightmost child of the
	 * nodeToDelete's left child, then "removes" that node
	 * 
	 * @param nodeToDelete
	 *            Node being searched for in the list and deleted
	 * @return Deleted node
	 */

	private BinaryNode<E> _deleteNode(BinaryNode<E> nodeToDelete) {
		if (nodeToDelete.isLeaf()) // node to delete has no children
		{
			return null;
		}
		if (!nodeToDelete.hasLeftChild()) // node to delete has no LEFT child
		{
			return nodeToDelete.getRightChild();
		}
		if (!nodeToDelete.hasRightChild()) // node to delete has no RIGHT child
		{
			return nodeToDelete.getLeftChild();
		}
		// must have left and right children
		// Locate the rightmost node in the left subtree of
		// the node to delete and also its parent
		BinaryNode<E> parentOfRightMost = nodeToDelete;
		BinaryNode<E> rightMost = nodeToDelete.getLeftChild();

		while (rightMost.getRightChild() != null) {
			parentOfRightMost = rightMost;
			rightMost = rightMost.getRightChild(); // Keep going to the right
		}

		// Replace the element in nodeToDelete by the element in rightMost
		nodeToDelete.setData(rightMost.getData()); // don't really delete the node, just change the data

		// Eliminate rightmost node
		if (parentOfRightMost.getRightChild() == rightMost)
			parentOfRightMost.setRightChild(rightMost.getLeftChild());
		else
			// Special case: nodeToDelete's leftChild has no rightChild
			parentOfRightMost.setLeftChild(rightMost.getLeftChild());

		return nodeToDelete;
	} // end private _deleteNode

	/**
	 * Gets the first (leftmost leaf) node in tree
	 * 
	 * @return First node in list
	 */
	public E getFirst()// you finish (part of HW#4)
	{
		if (root == null)
			return null;
		BinaryNode<E> iteratorNode = root;
		while (iteratorNode.hasLeftChild())
			iteratorNode = iteratorNode.getLeftChild();
		return iteratorNode.getData();
	}

	/**
	 * Gets the last (rightmost leaf) node in tree
	 * 
	 * @return Last node in list
	 */
	public E getLast()// you finish (part of HW#4)
	{
		// If the tree is empty, return null
		// FIND THE RIGHT-MOST RIGHT CHILD
		// WHEN you can't go RIGHT anymore, return the node's data to last Item
		if (root == null)
			return null;
		BinaryNode<E> iteratorNode = root;
		while (iteratorNode.hasRightChild())
			iteratorNode = iteratorNode.getRightChild();
		return iteratorNode.getData();
	}

} // end class BST
