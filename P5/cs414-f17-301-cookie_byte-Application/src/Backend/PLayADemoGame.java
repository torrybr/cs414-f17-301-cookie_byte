package Backend;

public class PLayADemoGame {
	
	public static void main(String args[])
	{
		
		// Create users
		User a = new User("a", "blank", "blank");
		User b = new User("b", "blank", "blank");
		
		// Gameplay object
		GameController play = new GameController(12345, a, b);
		
		// a gets to move 
		play.movePiece(3, 0, 2, 0);
		
		// b gets to move
		//play.movePiece(4, 6, 2, 6);
		
		// a
		//play.movePiece(2, 0, 2, 5);
		
		// b
		//play.movePiece(4, 4, 2, 4);
		
		// Try to move b again when it is a's turn
		//play.movePiece(2, 4, 2, 3);
		
		// Try to move a nowhere
		//play.movePiece(6, 0, 6, 0);
		
		// Try to move player a rook to corner
		//play.movePiece(7, 0, 10, 0);
		
		// Move player a
		//play.movePiece(7, 0, 9, 0);
		
		// Try to move a null piece
		//play.movePiece(3, 9, 4, 9);
	}

}
