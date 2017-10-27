package Backend;

public class PLayADemoGame {
	
	public static void main(String args[])
	{
		// Create board
		Board board1 = new Board();
		
		// Create users
		User a = new User("a");
		User b = new User("b");
		
		// Gameplay object
		Gameplay play = new Gameplay(board1, a, b);
		
		// Setup MakeMove object
		MakeMove mke = new MakeMove(play, a, b);
		
		// Check gameboard
		for(int i = 0; i< 11; i++)
		{
			for(int j = 0; j < 11; j++)
			{
				System.out.println("[" + i + "]" + "[" + j + "] type " + board1.getPieceType(i, j) + " owned by: " + board1.getPieceOwner(i, j));
			}
		}
		
		// a gets to move 
		mke.movePiece(3, 0, 2, 0);
		
		// b gets to move
		mke.movePiece(4, 6, 2, 6);
		
		// a
		mke.movePiece(2, 0, 2, 5);
		
		// b
		mke.movePiece(5, 3, 4, 3);
	}

}
