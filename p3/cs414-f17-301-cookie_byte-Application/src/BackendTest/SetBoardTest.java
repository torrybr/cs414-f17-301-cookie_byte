package BackendTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Backend.Board;
import Backend.PieceType;
import Backend.SetBoard;
import Backend.User;

public class SetBoardTest {
	
	@Test
	public void testSetBoard()
	{
		User off = new User("a", "pass1", "a@a.a");
		User def = new User("b", "pass2", "b@b.b");
	    User none = new User("nullUser", "nullUser", "null@null.null");
		Board b = new Board();
		SetBoard sb = new SetBoard(b, off, def);
		
		sb.setBoard();
		
		// Offence top
		assertEquals(off, b.getPieceOwner(0, 3));
		assertEquals(PieceType.ROOK, b.getPieceType(0, 3));
		assertEquals(off, b.getPieceOwner(0, 4));
		assertEquals(PieceType.ROOK, b.getPieceType(0, 4));
		assertEquals(off, b.getPieceOwner(0, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(0, 5));
		assertEquals(off, b.getPieceOwner(0, 6));
		assertEquals(PieceType.ROOK, b.getPieceType(0, 6));
		assertEquals(off, b.getPieceOwner(0, 7));
		assertEquals(PieceType.ROOK, b.getPieceType(0, 7));
		assertEquals(off, b.getPieceOwner(1, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(1, 5));
		
		// Offence left
		assertEquals(off, b.getPieceOwner(3, 0));
		assertEquals(PieceType.ROOK, b.getPieceType(3, 0));
		assertEquals(off, b.getPieceOwner(4, 0));
		assertEquals(PieceType.ROOK, b.getPieceType(4, 0));
		assertEquals(off, b.getPieceOwner(5, 0));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 0));
		assertEquals(off, b.getPieceOwner(6, 0));
		assertEquals(PieceType.ROOK, b.getPieceType(6, 0));
		assertEquals(off, b.getPieceOwner(7, 0));
		assertEquals(PieceType.ROOK, b.getPieceType(7, 0));
		assertEquals(off, b.getPieceOwner(5, 1));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 1));
		
		// Offence right
		assertEquals(off, b.getPieceOwner(3, 10));
		assertEquals(PieceType.ROOK, b.getPieceType(3, 10));
		assertEquals(off, b.getPieceOwner(4, 10));
		assertEquals(PieceType.ROOK, b.getPieceType(4, 10));
		assertEquals(off, b.getPieceOwner(5, 10));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 10));
		assertEquals(off, b.getPieceOwner(6, 10));
		assertEquals(PieceType.ROOK, b.getPieceType(6, 10));
		assertEquals(off, b.getPieceOwner(7, 10));
		assertEquals(PieceType.ROOK, b.getPieceType(7, 10));
		assertEquals(off, b.getPieceOwner(5, 9));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 9));
		
		// Offence bottom
		assertEquals(off, b.getPieceOwner(10, 3));
		assertEquals(PieceType.ROOK, b.getPieceType(10, 3));
		assertEquals(off, b.getPieceOwner(10, 4));
		assertEquals(PieceType.ROOK, b.getPieceType(10, 4));
		assertEquals(off, b.getPieceOwner(10, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(10, 5));
		assertEquals(off, b.getPieceOwner(10, 6));
		assertEquals(PieceType.ROOK, b.getPieceType(10, 6));
		assertEquals(off, b.getPieceOwner(10, 7));
		assertEquals(PieceType.ROOK, b.getPieceType(10, 7));
		assertEquals(off, b.getPieceOwner(9, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(9, 5));
		
		// Defence
		assertEquals(def, b.getPieceOwner(3, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(3, 5));
		assertEquals(def, b.getPieceOwner(4, 4));
		assertEquals(PieceType.ROOK, b.getPieceType(4, 4));
		assertEquals(def, b.getPieceOwner(4, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(4, 5));
		assertEquals(def, b.getPieceOwner(4, 6));
		assertEquals(PieceType.ROOK, b.getPieceType(4, 6));
		assertEquals(def, b.getPieceOwner(5, 3));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 3));
		assertEquals(def, b.getPieceOwner(5, 4));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 4));
		assertEquals(def, b.getPieceOwner(5, 5));
		assertEquals(PieceType.KING, b.getPieceType(5, 5));
		assertEquals(def, b.getPieceOwner(5, 6));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 6));
		assertEquals(def, b.getPieceOwner(5, 7));
		assertEquals(PieceType.ROOK, b.getPieceType(5, 7));
		assertEquals(def, b.getPieceOwner(6, 4));
		assertEquals(PieceType.ROOK, b.getPieceType(6, 4));
		assertEquals(def, b.getPieceOwner(6, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(6, 5));
		assertEquals(def, b.getPieceOwner(6, 6));
		assertEquals(PieceType.ROOK, b.getPieceType(6, 6));
		assertEquals(def, b.getPieceOwner(7, 5));
		assertEquals(PieceType.ROOK, b.getPieceType(7, 5));
		
		// A few random checks for piece type NONE
		assertEquals(none.getUserID(), b.getPieceOwner(0, 0).getUserID());
		assertEquals(PieceType.NONE, b.getPieceType(0, 0));
		assertEquals(none.getUserID(), b.getPieceOwner(5, 2).getUserID());
		assertEquals(PieceType.NONE, b.getPieceType(5, 2));
		assertEquals(none.getUserID(), b.getPieceOwner(3, 9).getUserID());
		assertEquals(PieceType.NONE, b.getPieceType(3, 9));
	}
	
	// Testing complete. Constructor adequately tested in above (huge) test.

}
