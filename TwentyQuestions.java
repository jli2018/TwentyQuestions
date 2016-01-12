import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException; 

/**
 * TwentyQuestions class. 
 *
 * @author 	Jessica Li
 * @version	01/12/16	
 */ 
public class TwentyQuestions
{

	private BinaryTree<String> rootNode;
	private BinaryTree<String> currNode;

	/**
	 * Constructor for TwentyQuestions. Calls read() method. 
	 */
	public TwentyQuestions()
	{
		read(); 
	}

	/**
	 * Reads the file and transfers data into binary tree. Initializes class fields. 
	 * The first line of the txt file is the root node, the second is the right child, and the third is the left child. 
	 * Sets currNode to rootNode. 
	 */
	public void read()
	{
		String pathname = "20Questions.txt";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		//while( input.hasNextLine() )
		//{
			//String line = input.nextLine();
		//}

		String line = input.nextLine();
		rootNode = new BinaryTree<String>( line );
		String right = input.nextLine();
		rootNode.setRight( new BinaryTree<String>(right) ); 
		String left = input.nextLine();
		rootNode.setLeft( new BinaryTree<String>(left) );
		//by reference, right?
		currNode = rootNode; 
	}


	/**
	 * Experimental code for saving to/reading from txt file. Not complete. 
	 */ 
	/*public void traverse()
	{
		BinaryTree<String> node = rootNode;

		String line = input.nextLine();

		if ( line.equals( "(" ) )
		{
			currNode.setRight();
			traverse();
		}
		else if ( line.equals( "," ) )
		{
			currNode.setLeft();
		}
		else if ( line.equals( ")" ) )
		{

		}
	}*/

	/**
	 * Code for the algorithm of the game. 
	 * If the current node is not a leaf, prints its value (which is a question) and receives user input. If the user enters "yes", sets currNode equal
	 * to the right child and calls method again. Otherwise, sets currNode to the left child and calls method again. 
	 * If currNode is a leaf, asks if the user is thinking of the object contained in that leaf. If user replies yes, prints a command of victory. 
	 * Otherwise, prints a command recognizing defeat and asks for the object the user was thinking of. Stores the answer. Asks user for a question that
	 * differentiates his/her object from the guess. Stores that question. Stores value of currNode. Sets value of currNode to question. 
	 * Sets currNode's left child to currNode's original value and right child to the user's answer. 
	 * Asks the user to play again. If user responds yes, sets currNode to rootNode and calls gameCode() again. 
	 */ 
	public void gameCode()
	{
		Scanner input = new Scanner( System.in );

		if ( !currNode.isLeaf() )
		{
			System.out.println( currNode.value() );
			//get input!
			//System.out.println(  );
			String answer = input.nextLine();
			if ( answer.equals("yes") )
			{
				currNode = currNode.right();
				gameCode();
			}
			//maybe i should have this only work for no
			else
			{
				currNode = currNode.left();
				gameCode();
			}

		}
		else
		{
			System.out.println( "Are you thinking of a " + currNode.value() + "? " );
			String reply = input.nextLine();
			if ( reply.equals("yes") )
				System.out.println( "It's my win! I have furthered my goal of world conquest. " );
			else
			{
				System.out.println( "You won't be winning next time! What was the object of which you were thinking? " );
				String answer = input.nextLine();
				System.out.println( "Please enter a question that would differentiate my guess from your answer, with your answer as the positive. " );
				String question = input.nextLine();
				String currValue = currNode.value(); 
				currNode.setValue( question );
				BinaryTree<String> right = new BinaryTree<String>( answer ); 
				currNode.setRight( right );
				BinaryTree<String> left = new BinaryTree<String>( currValue ); 
				currNode.setLeft( left ); 
			}
			//does it need to play again??? trace back up?

			System.out.println( "Would you like to play again? ");
			String again = input.nextLine();
			if ( again.equals("yes") )
			{
				currNode = rootNode;
				gameCode();
			}

		}

	}


}