package BackendTest;

import static org.junit.Assert.*;
import org.junit.Test;
import Backend.Piece;
import Backend.PieceType;
import Backend.User;

public class PieceTest {

	// Testing piece constructor
	@Test
	public void testPiece()
	{
		User u = new User("testID", "abc123", "test@test.cats");
		Piece p = new Piece(PieceType.ROOK, u);
		
		assertEquals(u , p.getPlayer());
		assertEquals(PieceType.ROOK , p.getType());
	}
	
	// Testing complete. All other methods are simple getter/setters. No need to test
	

}
