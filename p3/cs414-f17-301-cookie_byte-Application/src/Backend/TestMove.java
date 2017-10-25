package Backend;

import static org.junit.Assert.*;

import org.junit.Test;

//Checks all possible moves in the game
public class TestMove {

	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	Game gme = new Game(1323, usr1, usr2);
	
			
	@Test
	public void testisMoveValid() {
		Move move = new Move(gme.CurrentTurn,gme);
		//Straight Line Check/In range of board check
		
			//True Condition
			assertTrue(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 4, 3));
			assertTrue(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 6, 3));
			
			//False Condition
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 1, 4));
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 9, 2));
			
		// Out of range of board check
			//False condition
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 11, 11));
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, -1, -1));
		
		//Is the piece moving to a corner & not a king check
			
			//top left
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 0, 0));
			
			//top right
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 7), 0, 7, 0, 10));
			
			//bottom left
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 10, 0));
			
			//bottom right
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 7), 10, 7, 10, 10));
			
		//check if there is a piece in the way
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 6, 3));
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 4, 3));
			
	}
	
	@Test
	public void testcapturePiece() {
		
		// Test out of bounds and If the piece is on the edge
	}

}
