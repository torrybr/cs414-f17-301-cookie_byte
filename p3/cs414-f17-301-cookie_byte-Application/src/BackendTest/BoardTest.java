package BackendTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import Backend.Board;
import Backend.Piece;
import Backend.PieceType;
import Backend.User;

public class BoardTest {
	
	// Testing adding piece to board
	@Test
	public void testAddPieceToBoard()
	{
		User u = new User("testID", "abc123", "test@test.cats");
		Piece p = new Piece(PieceType.ROOK, u);
		Board b = new Board();
		
		b.addPieceToBoard(4, 4, p.getType(), p.getPlayer());
		
		assertEquals(u, b.getPieceOwner(4, 4));
		assertEquals(p.getType(), b.getPieceType(4, 4));
	}
	
	// Testing removing piece from board
	@Test
	public void testRemovePiece()
	{
		User u = new User("testID", "abc123", "test@test.cats");
		Piece p = new Piece(PieceType.ROOK, u);
		Board b = new Board();
		
		b.addPieceToBoard(4, 4, p.getType(), p.getPlayer());
		
		assertEquals(u, b.getPieceOwner(4, 4));
		assertEquals(p.getType(), b.getPieceType(4, 4));
		
		b.removePiece(4, 4);
		
		assertEquals(null, b.getPieceOwner(4, 4));
		assertEquals(PieceType.NONE, b.getPieceType(4, 4));
	}
	
	// Tests moving a piece. Note that every space (to be used) on board must be initialized with a piece before movement
	@Test
	public void testMovePiece()
	{
		User u = new User("testID", "abc123", "test@test.cats");
		Piece p = new Piece(PieceType.ROOK, u);
		Piece empty = new Piece(PieceType.NONE, u);
		Board b = new Board();
		
		b.addPieceToBoard(4, 4, p.getType(), p.getPlayer());
		b.addPieceToBoard(6, 6, empty.getType(), empty.getPlayer());
		
		assertEquals(u, b.getPieceOwner(4, 4));
		assertEquals(p.getType(), b.getPieceType(4, 4));
		
		b.movePiece(4, 4, 6, 6);
		
		assertEquals(null, b.getPieceOwner(4, 4));
		assertEquals(PieceType.NONE, b.getPieceType(4, 4));
		assertEquals(u, b.getPieceOwner(6, 6));
		assertEquals(p.getType(), b.getPieceType(6, 6));
	}
	
	// Testing complete. Did not test getters or setters. 

}
