package Backend;

import static org.junit.Assert.*;
import org.junit.Test;

//This test class tests space, board, and piece all together since they are all components of each other.
public class TestBoard {
	
	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	Game gme = new Game(1323, usr1, usr2);
	
	@Test
	public void testRemovePiece(){
		//test the remove function at the board level
		Piece p = gme.getBoard().getSpace(5,5).getPiece();
		if(p != null)
		{
			gme.board.removePiece(5,5);
			assertTrue(gme.getBoard().getSpace(5, 5).isEmpty());
		}
		
	}
	
	@Test
	public void testGetPiece(){
		//test if you can get the correct piece at the board level
		Piece p = gme.getBoard().getPiece(5, 5);
		assertTrue(p.type.equals(PieceType.KING));
	}
	
	@Test
	public void testSpaceIsEmpty(){
		
		assertTrue(gme.getBoard().getSpace(1, 1).isEmpty());
		assertFalse(gme.getBoard().getSpace(0, 5).isEmpty());
	}
	
	@Test
	public void testPiece(){
		//test getters and setters for Piece to contain full coverage
		User testUser1 = gme.getBoard().getPiece(5, 5).getPlayer();
		assertTrue(testUser1.equals(gme.getBoard().getPiece(3, 5).getPlayer()));
		assertTrue(gme.getBoard().getPiece(10,5).getType().equals(PieceType.ROOK));
	}
}
