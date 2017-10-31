package Backend;

public class PLayADemoGame {
	
	public static void main(String args[])
	{
		
		// Create users
		User a = new User("a", "blank", "blank");
		User b = new User("b", "blank", "blank");
		
		// Gameplay object
		GameController play = new GameController(12345, a, b);
		
		// Setup MakeMove object
		MakeMove mke = new MakeMove(play, a, b);
		
		// a gets to move 
		mke.movePiece(3, 0, 2, 0);
		
		// b gets to move
		mke.movePiece(4, 6, 2, 6);
		
		// a
		mke.movePiece(2, 0, 2, 5);
		
		// b
		mke.movePiece(4, 4, 2, 4);
		
		// Try to move b again when it is a's turn
		mke.movePiece(2, 4, 2, 3);
		
		// Try to move a nowhere
		mke.movePiece(6, 0, 6, 0);
		
		// Try to move player a rook to corner
		mke.movePiece(7, 0, 10, 0);
		
		// Move player a
		mke.movePiece(7, 0, 9, 0);
		
		// Try to move a null piece
		mke.movePiece(3, 9, 4, 9);
	}

}
