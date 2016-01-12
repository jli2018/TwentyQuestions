import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException; 

/**
 * BinaryTreePostOrderIterator class.
 *
 * @author Jessica Li
 * @version 12/18/15
 */
public class BinaryTreePostOrderIterator<E> implements Iterator<E>
{
	private Queue<E> values;

	/**
	 * Constructor for BinaryTreePostOrderIterator class. 
	 * Initializes Queue values and calls helper method addToQueue(). 
	 *
	 * @param t 	the BinaryTree to be iterated through
	 */ 
	public BinaryTreePostOrderIterator( BinaryTree<E> t )
	{
		values = new LinkedList<E>(); 
		addToQueue( t );
	}

	/**
	 * Helper method for constructor, recursively adds all elements of the binary tree to Queue values.
	 * Checks in case of nulls and calls left, right, root. 
	 *
	 * @param t 	the BinaryTree to be iterated through
	 */ 
	public void addToQueue( BinaryTree<E> t )
	{
		if ( t.left() != null )
			addToQueue( t.left() );
		if ( t.right() != null )
			addToQueue( t.right() );
		values.offer( t.value() );
	}

	/**
	 * Returns whether or not the tree has any more elements left to iterate through. Checks if Queue values is empty. 
	 * If yes, returns false. Otherwise, returns true. 
	 *
	 * @return 	true if there are more elements in the tree to iterate through, false otherwise
	 */ 
	public boolean hasNext()
	{
		if ( values.isEmpty() )
			return false;
		return true; 
	}

	/**
	 * Returns the next value in the tree. 
	 * If hasNext() is false, throws exception. Otherwise, returns and removes the top value from Queue values. 
	 *
	 * @return 		the next value in the binary tree
	 */ 
	public E next()
	{
		if ( !hasNext() )
			throw new NoSuchElementException( "No elements left to iterate." );
		return values.poll(); 
	}

}