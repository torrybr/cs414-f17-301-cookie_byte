package Backend;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

//Will test all win conditions of the game
public class TestGame {

	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	
	@Test
	public void testkingWinConditions() {
		
		//Creates fake king and game to place in different areas of the default board.
		Game gme = new Game(1323, usr1, usr2);
		Piece King= new Piece (PieceType.KING, usr1);
		
		//Checks if the king is in the top left corner!
		gme.board.getSpace(0, 0).setPiece(King);
		assertTrue(gme.kingWinConditions());
		gme.board.getSpace(0, 0).setPiece(null);
		
		//Checks if the king is in the top right corner!
		gme.board.getSpace(0, 10).setPiece(King);
		assertTrue(gme.kingWinConditions());
		gme.board.getSpace(10, 10).setPiece(null);
				
		//Checks if the king is in the bottom left corner!
		gme.board.getSpace(10, 0).setPiece(King);
		assertTrue(gme.kingWinConditions());
		gme.board.getSpace(10, 0).setPiece(null);
		
		//Checks if the king is in the bottom right corner!
		gme.board.getSpace(10, 10).setPiece(King);
		assertTrue(gme.kingWinConditions());
		
		//Checks the false condition
		gme.board.getSpace(10, 10).setPiece(null);
		assertFalse(gme.kingWinConditions());
		
		//Checks to make sure other attributes of the game have been properly set
		assertTrue(gme.Winner.equals(King.getPlayer()));
		assertTrue(gme.status.equals(GameStatus.FINISHED));
		assertTrue(usr1.PastGames.contains(gme));
		assertTrue(usr2.PastGames.contains(gme));
		
	}
	
	public void testattackWinConditions() {
		
		//Creates fake king and game to place in different areas of the default board.
				Game gme = new Game(1323, usr1, usr2);
				Piece King= new Piece (PieceType.KING, usr1);
		
		//Creates 4 dummy attack pieces
				Piece attack1 = new Piece (PieceType.ROOK, usr2);
				Piece attack2 = new Piece (PieceType.ROOK, usr2);
				Piece attack3 = new Piece (PieceType.ROOK, usr2);
				Piece attack4 = new Piece (PieceType.ROOK, usr2);
				
				
		//Creates a king that is surrounded by 4 pieces
				gme.board.getSpace(1, 1).setPiece(King);
				gme.board.getSpace(1, 0).setPiece(attack1);
				gme.board.getSpace(0, 1).setPiece(attack2);
				gme.board.getSpace(2, 1).setPiece(attack3);
				gme.board.getSpace(1, 2).setPiece(attack3);
				assertTrue(gme.attackWinConditions());
		
		//Changes one piece to be on the King's side and expect win condition to fail
				gme.board.getSpace(1, 0).getPiece().setPlayer(usr1);
				assertFalse(gme.attackWinConditions());
				gme.board.getSpace(1, 0).getPiece().setPlayer(usr2);
				
				gme.board.getSpace(0, 1).getPiece().setPlayer(usr1);
				assertFalse(gme.attackWinConditions());
				gme.board.getSpace(0, 1).getPiece().setPlayer(usr2);
				
				gme.board.getSpace(2, 1).getPiece().setPlayer(usr1);
				assertFalse(gme.attackWinConditions());
				gme.board.getSpace(2, 1).getPiece().setPlayer(usr2);
				
				gme.board.getSpace(1, 2).getPiece().setPlayer(usr1);
				assertFalse(gme.attackWinConditions());
				gme.board.getSpace(1, 2).getPiece().setPlayer(usr2);
				
	}
	

}
