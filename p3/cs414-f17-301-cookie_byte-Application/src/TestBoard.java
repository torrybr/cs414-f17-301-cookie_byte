
import static org.junit.Assert.*;
import org.junit.Test;

public class TestBoard {
	
	@Test
	public void testRemovePiece() {
		User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
		User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
		Game gme = new Game(1323, usr1, usr2);
		
		
		Piece p = gme.getBoard().getSpace(5,5).getPiece();
		
		if(p != null)
		{
			gme.board.removePiece(p);
			
			assertTrue(gme.getBoard().getSpace(5, 5).isEmpty());
		}
		
	}

}
