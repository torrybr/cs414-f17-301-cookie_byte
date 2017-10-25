package Backend;

import static org.junit.Assert.*;

import org.junit.Test;

//Checks all possible moves in the game
public class TestMove {

	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	Game gme = new Game(1323, usr1, usr2);
	Move move = new Move(gme.CurrentTurn, gme);
			
	@Test
	public void testisMoveValid() {
		
		//Straight Line Check/In range of board check
		
			//True Condition
		
			//going up
			assertTrue(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 4, 3)); 
			assertTrue(move.isMoveValid(move.game.board.getPiece(1, 5), 1, 5, 1, 10));
			
			//going down
			assertTrue(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 6, 3));
			assertTrue(move.isMoveValid(move.game.board.getPiece(1, 5), 1, 5, 1, 0));
			
			//False Condition
			
			//going up
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 1, 4));
			assertFalse(move.isMoveValid(move.game.board.getPiece(4, 4), 4, 4, 4, 7));
			
			//going down
		    assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, 9, 2));
	        assertFalse(move.isMoveValid(move.game.board.getPiece(4, 6), 4, 6, 4, 3));
	
		// Out of range of board check
			//False condition
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 0, 3, 11, 11));
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), 10, 3, -1, -1));
			assertFalse(move.isMoveValid(move.game.board.getPiece(0, 3), 11, 11, 0, 3));
			assertFalse(move.isMoveValid(move.game.board.getPiece(10, 3), -1, -1, 10, 3));
		
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
		
		//makes fake piece to place on board to test capture conditions
		Piece offence1 = new Piece (PieceType.ROOK, usr2);
		Piece offence2 = new Piece (PieceType.ROOK, usr2);
		Piece defence = new Piece (PieceType.ROOK, usr1);
		
		//Test top right corner
		move.game.board.getSpace(2, 0).setPiece(offence1);
		move.game.board.getSpace(1, 0).setPiece(defence);
		assertTrue(move.capturePiece(1,0));
		move.game.board.getSpace(1, 0).setPiece(null);
		
		move.game.board.getSpace(0, 2).setPiece(offence1);
		move.game.board.getSpace(0, 1).setPiece(defence);
		assertTrue(move.capturePiece(0,1));
		move.game.board.getSpace(0, 1).setPiece(null);
		
		//Test top left corner
		move.game.board.getSpace(0, 8).setPiece(offence1);
		move.game.board.getSpace(0, 9).setPiece(defence);
		assertTrue(move.capturePiece(0,9));
		move.game.board.getSpace(0, 9).setPiece(null);
		
		move.game.board.getSpace(2, 10).setPiece(offence1);
		move.game.board.getSpace(1, 10).setPiece(defence);
		assertTrue(move.capturePiece(1,10));
		move.game.board.getSpace(1, 10).setPiece(null);
		
		//Test bottom left Corner
		move.game.board.getSpace(8, 0).setPiece(offence1);
		move.game.board.getSpace(9, 0).setPiece(defence);
		assertTrue(move.capturePiece(9,0));
		move.game.board.getSpace(9, 0).setPiece(null);
		
		move.game.board.getSpace(10, 2).setPiece(offence1);
		move.game.board.getSpace(10, 1).setPiece(defence);
		assertTrue(move.capturePiece(10,1));
		move.game.board.getSpace(10, 1).setPiece(null);
		
		//Test bottom right Corner
		move.game.board.getSpace(8, 10).setPiece(offence1);
		move.game.board.getSpace(9, 10).setPiece(defence);
		assertTrue(move.capturePiece(9,10));
		move.game.board.getSpace(9, 10).setPiece(null);
		
		move.game.board.getSpace(10, 8).setPiece(offence1);
		move.game.board.getSpace(10, 9).setPiece(defence);
		assertTrue(move.capturePiece(10,9));
		move.game.board.getSpace(10, 9).setPiece(null);
		
		// Test out of bounds and If the piece is on the edge in a true condition
		move.game.board.getSpace(1, 0).setPiece(offence1);
		move.game.board.getSpace(2, 0).setPiece(defence);
		move.game.board.getSpace(3, 0).setPiece(offence2);
		assertFalse(move.capturePiece(2,0));
		
		move.game.board.getSpace(2, 2).setPiece(offence1);
		move.game.board.getSpace(3, 2).setPiece(defence);
		move.game.board.getSpace(4, 2).setPiece(offence2);
		assertTrue(move.capturePiece(3,2));
		
		move.game.board.getSpace(3, 1).setPiece(offence1);
		move.game.board.getSpace(3, 2).setPiece(defence);
		move.game.board.getSpace(3, 3).setPiece(offence2);
		assertTrue(move.capturePiece(3,2));
}
	
	@Test
	public void testmovePiece() {
		
		//makes fake piece to place on board to test capture conditions
				Piece offence1 = new Piece (PieceType.ROOK, usr2);
				Piece offence2 = new Piece (PieceType.ROOK, usr2);
				Piece defence = new Piece (PieceType.ROOK, usr1);
				move.game.board.getSpace(2, 1).setPiece(offence1);
			
				
		//test a valid move made by user Checks to make sure that attributes are good
		move.movePiece(offence1, 2, 1, 2, 5);
		assertFalse(move.game.board.getSpace(2, 1).isEmpty());
		assertTrue(move.game.board.getSpace(2, 5).isEmpty());
		
		//test if a valid move is made by user and the piece is captured
		move.movePiece(defence, 2, 1, 2, 4);
		move.movePiece(offence2, 3, 0, 3, 4);
		
	}

}
