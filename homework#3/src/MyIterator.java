/**
 * Program Name: MyIterator.java
 * 
 * @author Roei Burstein 
 * Current Date: Thursday, February 15, 2018 
 * Computer Operating System: Mac OS High Sierra 
 * Version 10.13.2 
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is the iterator interface
 * <p>
 */
public interface MyIterator<T> {
	public void startIterator();

	public boolean hasNext();

	public T next();
}
