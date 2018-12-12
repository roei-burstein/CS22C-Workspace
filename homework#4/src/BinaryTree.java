/**
 * Program Name: BinaryTree.java
 * 
 * @author Roei Burstein 
 * Current Date: Wednesday, February 28, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program contains the methods and variables
 * regarding the BinaryTree.
 * <p>
 */

import java.io.*;

public abstract class BinaryTree<E> implements TreeInterface<E> {
	protected BinaryNode<E> root = null; // reference to the root
	protected int size = 0; // number of nodes in the tree

	/**
	 * Default Constructor, does nothing
	 */
	public BinaryTree() {
	}

	/** Clears the whole tree */
	public void clear() {
		root = null;
		size = 0;
	}

	public boolean areTreesIdentical(BinaryTree bt){
	    BinaryNode<E> thisNode = root;
	    BinaryNode<E> paramNode = bt.root;
	    while()
	    if(!thisNode.getData().equals(paramNode.getData())) {
            return false;
        }
	    return true;
    }
	/**
	 * Main purpose to call the writeTree function
	 */
	public void writeIndentedTree(PrintStream writer) {
		writeTree(writer, root, 1, "");
	}

	/**
	 * Prints an tree with the correct indentations into a readable format
	 * 
	 * @param writer
	 * @param root
	 *            Root node of the tree (or subtree)
	 * @param level
	 *            Level of particular node
	 * @param indent
	 *            Amount of indentation for the node in the printed tree
	 */
	protected void writeTree(PrintStream writer, BinaryNode<E> root, int level, String indent) {
		if (root == null)
			return;
		writeTree(writer, root.getRightChild(), level + 1, indent + "\t");
		System.out.println(indent + level + ". " + root.getData());
		writeTree(writer, root.getLeftChild(), level + 1, indent + "\t");
	}

	@Override /** Preorder traversal from the root */
	public void preorder(Visitor<E> visitor) {
		preorder(root, visitor);
	}

	@Override /** Inorder traversal from the root */
	public void inorder(Visitor<E> visitor) {
		inorder(root, visitor);
	}

	@Override /** Postorder traversal from the root */
	public void postorder(Visitor<E> visitor) {
		postorder(root, visitor);
	}

	@Override /** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	@Override /** Return true if the tree is empty */
	public boolean isEmpty() {
		return getSize() == 0;
	}

	/** Preorder traversal from a subtree */
	protected void preorder(BinaryNode<E> root, Visitor<E> visitor) {
		if (root == null)
			return;
		visitor.visit(root.getData());
		preorder(root.getLeftChild(), visitor);
		preorder(root.getRightChild(), visitor);
	}

	/** Inorder traversal from a subtree */
	protected void inorder(BinaryNode<E> root, Visitor<E> visitor) {
		if (root == null)
			return;
		inorder(root.getLeftChild(), visitor);
		visitor.visit(root.getData());
		inorder(root.getRightChild(), visitor);
	}

	/** Posorder traversal from a subtree */
	protected void postorder(BinaryNode<E> root, Visitor<E> visitor) {
		if (root == null)
			return;
		postorder(root.getLeftChild(), visitor);
		postorder(root.getRightChild(), visitor);
		visitor.visit(root.getData());
	}
} // end abstract BinaryTree class
