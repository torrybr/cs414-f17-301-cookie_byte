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
	
	@Test
	public void isMoveValidTest(){
		
		GameController gc2 = new GameController(2345, u, u2);
		
		//Test if the user tries to make a move when it's not their turn
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(3, 5), gc2.getBoard().getPieceOwner(3, 5), 3, 5, 2, 5));
		
		//Test to see if  player is trying to move nonexistant piece
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 0), u, 0, 0, 0, 2));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 0, 0, 2));
		
		//******* Test Case that needs to be passed that the code does not account for *********//
		//assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 0), u, 0, 3, 0, 2));
				
		//Test if player is trying to move their opponent's piece
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(3, 5), u, 3, 5, 2, 5));
		
		//Test if piece is actually being moved
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 0, 3));
		
		//Test if piece is moving in a stright line
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 1, 2));
		
		//Test if the piece is out of the range of the board
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 0, 11));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, -1, 3));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 11, 3));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 0, -1));
		
		//***Need help testing if the FromCol and the FromRow goes out of bounds
		/**
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 11, 0, 2));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, -1, 0, 2));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 11, 3, 0, 2));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, -1, 3, 0, 2)); **/
		
		//Test if the piece moving to the corner is not a king
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 0, 0));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 7), u, 0, 7, 0, 10));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(10, 3), u, 10, 3, 10, 0));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(10, 7), u, 10, 7, 10, 10));
		
		//Tests hitting a piece to the right
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 0, 4));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(3, 0), u, 3, 0, 3, 5));
		
		//Tests hitting a piece to the left
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 7), u, 0, 7, 0, 6));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(3, 10), u, 3, 10, 3, 5));
		
		//Tests hitting a piece below
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(9, 5), u, 9, 5, 10, 5));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 5, 3));
		
		//Test hitting a piece above
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(1, 5), u, 1, 5, 0, 5));
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(10, 3), u, 10, 3, 5, 3));
		
		//Valid move right
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 7), u, 0, 7, 0, 8));
		
		//Valid move left
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 3), u, 0, 3, 0, 2));
		
		//Valid move down
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(0, 3), u, 0, 3, 4, 3));
		
		//Valid move up
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 3), u, 10, 3, 6, 3));
		
		//Clears Board to add certain pieces to meet certain conditions
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				// Sets each space to be a piece of type NONE belonging to use none
				gc2.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
			}
		}
		
		//Puts a king at the top and bottom of the empty board
		gc2.getBoard().addPieceToBoard(0, 5, PieceType.KING, u);
		gc2.getBoard().addPieceToBoard(10, 5, PieceType.KING, u);
		
		//if king moves to corner 0,0 and 0, 10
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(0, 5), u, 0, 5, 0, 0));
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(0, 5), u, 0, 5, 0, 10));
		
		//if king moves to corner 10,0 and 10,10
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 5), u, 10, 5, 10, 0));
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 5), u, 10, 5, 10, 10));
		
		//if king moves to the throne (5,5)
		assertTrue(gc2.isMoveValid(gc2.getBoard().getPiece(10, 5), u, 10, 5, 5, 5));
		
		//Places another rook on the edge and tries to move it to the edge
		gc2.getBoard().addPieceToBoard(5, 0, PieceType.ROOK, u);
		assertFalse(gc2.isMoveValid(gc2.getBoard().getPiece(5, 0), u, 5, 0, 5, 5));
		
		}
	
	@Test
	public void capturePieceTest(){
		
		//Clears the default board
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				// Sets each space to be a piece of type NONE belonging to use none
				gc.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
			}
		}
		
		
	}
	

	
	

}
