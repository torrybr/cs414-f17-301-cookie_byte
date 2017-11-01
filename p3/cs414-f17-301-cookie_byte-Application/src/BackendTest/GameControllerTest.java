package BackendTest;

import static org.junit.Assert.*;
import org.junit.Test;
import Backend.Board;
import Backend.Piece;
import Backend.PieceType;
import Backend.User;
import Backend.GameController;
import Backend.Invite;
import org.junit.Test;

public class GameControllerTest {
	
	User u = new User("A", "B", "C");
	User u2 = new User("D", "E", "F");
	User none;
	GameController gc = new GameController(1234, u, u2);
	
	@Test
	public void kingWinConditionstest() {
	
		//Checks the top left win condition by placing a king in the 0,0 spot. Then removes piece to check next condition
		gc.getBoard().addPieceToBoard(0, 0, PieceType.KING, u2);
		assertTrue(gc.kingWinConditions());
		gc.getBoard().removePiece(0, 0);
		
		//Checks the top right win condition by placing a king in the 0,10 spot. Then removes piece to check next condition
		gc.getBoard().addPieceToBoard(0, 10, PieceType.KING, u2);
		assertTrue(gc.kingWinConditions());
		gc.getBoard().removePiece(0, 10);
		
		//Checks the bottom right win condition by placing a king in the 10,0 spot. Then removes piece to check next condition
		gc.getBoard().addPieceToBoard(10, 0, PieceType.KING, u2);
		assertTrue(gc.kingWinConditions());
		gc.getBoard().removePiece(10, 0);
		
		//Checks the top right win condition by placing a king in the 10,10 spot. Then removes piece to check next condition
		gc.getBoard().addPieceToBoard(10, 10, PieceType.KING, u2);
		assertTrue(gc.kingWinConditions());
		gc.getBoard().removePiece(10, 10);
		
		//Checks the false condition by leaving the king in its default position
		assertFalse(gc.kingWinConditions());
		
	}
	
	@Test
	public void attackWinConditionstest() {
		
		//Clears the default board
					for(int row = 0; row < 11; row++)
					{
						for(int col = 0; col < 11; col++)
						{
							// Sets each space to be a piece of type NONE belonging to use none
							gc.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
						}
					}

		//If the king is on the top edge attack conditions are automatically false- removes king afterwards
		gc.getBoard().addPieceToBoard(0, 1, PieceType.KING, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(0, 1, PieceType.NONE, none);
		
		//If the king is on the right edge attack conditions are automatically false- removes king afterwards
		gc.getBoard().addPieceToBoard(1, 10, PieceType.KING, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(1, 10, PieceType.NONE, none);
		
		//If the king is on the bottom edge attack conditions are automatically false- removes king afterwards
		gc.getBoard().addPieceToBoard(10, 1, PieceType.KING, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(10, 1, PieceType.NONE, none);
		
		//If the king is on the left edge attack conditions are automatically false- removes king afterwards
		gc.getBoard().addPieceToBoard(1, 0, PieceType.KING, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(1, 0, PieceType.NONE, none);
		
		//If king is on the left side of the throne
		gc.getBoard().addPieceToBoard(5, 4, PieceType.KING, u2);
		
		//Rook at 6, 4
		gc.getBoard().addPieceToBoard(6, 4, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(6, 4, PieceType.ROOK, u);
		
		//Rook at 5, 3
		gc.getBoard().addPieceToBoard(5, 3, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(5, 3, PieceType.ROOK, u);
		
		//Rook at 4, 4
		gc.getBoard().addPieceToBoard(4, 4, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(4, 4, PieceType.ROOK, u);
		
		assertTrue(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(5, 4, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(5, 3, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(4, 4, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(6, 4, PieceType.NONE, none);
		
		
		
		//If King is on the top side of the throne
		gc.getBoard().addPieceToBoard(4, 5, PieceType.KING, u2);
		
		//Rook at 4, 4
		gc.getBoard().addPieceToBoard(3, 5, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(3, 5, PieceType.ROOK, u);
		
		//Rook at 4, 6
		gc.getBoard().addPieceToBoard(4, 6, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(4, 6, PieceType.ROOK, u);
		
		//Rook at 3, 5
		gc.getBoard().addPieceToBoard(4, 4, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(4, 4, PieceType.ROOK, u);
		
		assertTrue(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(4, 5, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(4, 4, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(4, 6, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(3, 5, PieceType.NONE, none);
		
		//If King is on the right side of the throne
		gc.getBoard().addPieceToBoard(5, 6, PieceType.KING, u2);
		
		//Rook at 4,6
		gc.getBoard().addPieceToBoard(4, 6, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(4, 6, PieceType.ROOK, u);
		
		//Rook at 6,6
		gc.getBoard().addPieceToBoard(6, 6, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(6, 6, PieceType.ROOK, u);
		
		//Rook at 5,7
		gc.getBoard().addPieceToBoard(5, 7, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(5, 7, PieceType.ROOK, u);
		
		assertTrue(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(5, 6, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(4, 6, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(6, 6, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(5, 7, PieceType.NONE, none);
		
		//If King is on the bottom side of the throne
		gc.getBoard().addPieceToBoard(6, 5, PieceType.KING, u2);
		
		//Rook at 6,4
		gc.getBoard().addPieceToBoard(6, 4, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(6, 4, PieceType.ROOK, u);
		
		//Rook at 6,6
		gc.getBoard().addPieceToBoard(7, 5, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(7, 5, PieceType.ROOK, u);
		
		//Rook at 5,7
		gc.getBoard().addPieceToBoard(6, 6, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(6, 6, PieceType.ROOK, u);
		
		assertTrue(gc.attackWinConditions());
		gc.getBoard().addPieceToBoard(6, 5, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(6, 4, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(6, 7, PieceType.NONE, none);
		gc.getBoard().addPieceToBoard(7, 5, PieceType.NONE, none);
		
		//Sets up 4 opponent pieces around the King to capture it.
		gc.getBoard().addPieceToBoard(1, 1, PieceType.KING, u2);
		gc.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u);
		gc.getBoard().addPieceToBoard(1, 2, PieceType.ROOK, u);
		gc.getBoard().addPieceToBoard(0, 1, PieceType.ROOK, u);
		gc.getBoard().addPieceToBoard(2, 1, PieceType.ROOK, u);
		
		//Checks if the king is captured
		assertTrue(gc.attackWinConditions());
		
		//Checks if one of the pieces belong to an opposite player
		gc.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u2);
		assertFalse(gc.attackWinConditions());
		
		
	}

}
