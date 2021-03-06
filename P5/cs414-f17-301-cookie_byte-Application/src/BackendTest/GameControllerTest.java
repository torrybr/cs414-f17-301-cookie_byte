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
		
		//checks if it tries to capture pieces that are out of bounds 
		assertFalse(gc.capturePiece(u, -1, 0));
		assertFalse(gc.capturePiece(u, 11, 0));
		assertFalse(gc.capturePiece(u, 0, -1));
		assertFalse(gc.capturePiece(u, 0, 11));
		
		//checks if the player is trying to capture a nonexistent piece
		assertFalse(gc.capturePiece(u, 0, 0));
		
		//Tests to see if we are on an edge and if next to a corner and needs to be removed
		// Checking next to top right corner
		
		gc.getBoard().addPieceToBoard(0, 1, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 0, 1));
		gc.getBoard().addPieceToBoard(0, 2, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 0, 1));
		
		gc.getBoard().addPieceToBoard(0, 2, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 0, 1));
		assertFalse(gc.capturePiece(u2, 0, 2));
		
		gc.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 0));
		gc.getBoard().addPieceToBoard(2, 0, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 0));
		
		gc.getBoard().addPieceToBoard(2, 0, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 1, 0));
		assertFalse(gc.capturePiece(u2, 2, 0));
		
		// Checking next to top left corner
		gc.getBoard().addPieceToBoard(0, 9, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 0, 9));
		gc.getBoard().addPieceToBoard(0, 8, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 0, 9));
		
		gc.getBoard().addPieceToBoard(0, 8, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 0, 9));
		assertFalse(gc.capturePiece(u2, 0, 8));
		
		gc.getBoard().addPieceToBoard(1, 10, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 10));
		gc.getBoard().addPieceToBoard(2, 10, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 10));
		
		gc.getBoard().addPieceToBoard(2, 10, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 1, 10));
		assertFalse(gc.capturePiece(u2, 2, 10));
		
		//Checks bottom Right corner
		gc.getBoard().addPieceToBoard(9, 0, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 9, 0));
		gc.getBoard().addPieceToBoard(8, 0, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 9, 0));
		
		gc.getBoard().addPieceToBoard(8, 0, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 9, 0));
		assertFalse(gc.capturePiece(u2, 8, 0));
		
		gc.getBoard().addPieceToBoard(10, 1, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 10, 1));
		gc.getBoard().addPieceToBoard(10, 2, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 10, 1));
		
		gc.getBoard().addPieceToBoard(10, 2, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 10, 1));
		assertFalse(gc.capturePiece(u2, 10, 2));
		
		//Checks bottom Left Corner
		gc.getBoard().addPieceToBoard(9, 10, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 9, 10));
		gc.getBoard().addPieceToBoard(8, 10, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 9, 10));
		
		gc.getBoard().addPieceToBoard(8, 10, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 9, 10));
		assertFalse(gc.capturePiece(u2, 8, 10));
		
		gc.getBoard().addPieceToBoard(10, 9, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 10, 9));
		gc.getBoard().addPieceToBoard(10, 8, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 10, 9));
		
		gc.getBoard().addPieceToBoard(10, 8, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 10, 9));
		assertFalse(gc.capturePiece(u2, 10, 8));
		
		//Sandwiches a piece horizontally
		gc.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u2);
		gc.getBoard().addPieceToBoard(1, 1, PieceType.ROOK, u);
		gc.getBoard().addPieceToBoard(1, 2, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 1, 1));
		gc.getBoard().addPieceToBoard(1, 2, PieceType.NONE, none);
		assertFalse(gc.capturePiece(u, 1, 1));
		gc.getBoard().addPieceToBoard(1, 2, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 1));
		
		//Sandwiches a piece Vertically
		gc.getBoard().addPieceToBoard(0, 9, PieceType.ROOK, u2);
		gc.getBoard().addPieceToBoard(1, 9, PieceType.ROOK, u);
		gc.getBoard().addPieceToBoard(2, 9, PieceType.ROOK, u2);
		assertTrue(gc.capturePiece(u, 1, 9));
		gc.getBoard().addPieceToBoard(2, 9, PieceType.NONE, none);
		assertFalse(gc.capturePiece(u, 1, 9));
		gc.getBoard().addPieceToBoard(2, 9, PieceType.ROOK, u);
		assertFalse(gc.capturePiece(u, 1, 9));
		
		//****IF the sandwiched piece is a King ****//
		//Sandwiches a King vertically
		gc.getBoard().addPieceToBoard(0, 9, PieceType.ROOK, u2);
		gc.getBoard().addPieceToBoard(1, 9, PieceType.KING, u);
		gc.getBoard().addPieceToBoard(2, 9, PieceType.ROOK, u2);
		assertFalse(gc.capturePiece(u, 1, 9));
		
		//Sandwiches a King horizontally
		gc.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u2);
		gc.getBoard().addPieceToBoard(1, 1, PieceType.KING, u);
		gc.getBoard().addPieceToBoard(1, 2, PieceType.ROOK, u2);
		assertFalse(gc.capturePiece(u, 1, 1));
		
		
	}
	
	@Test
	public void movePieceTest(){
		
		GameController gc2 = new GameController(2345, u, u2);
		
		//Checks invalid move
		gc2.movePiece(0, 3, 1, 1);
		assertTrue(gc2.getCurrentTurn().equals(u));
		
		//Checks correct move
		gc2.movePiece(0, 3, 0, 2);
		assertTrue(gc2.getCurrentTurn().equals(u2));
		
		//Clears the default board
				for(int row = 0; row < 11; row++)
				{
					for(int col = 0; col < 11; col++)
					{
						// Sets each space to be a piece of type NONE belonging to use none
						gc2.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
					}
				}
		
		//Sets up the attack winning Conditions one move away
		gc2.setCurrentTurn(u);		
		gc2.getBoard().addPieceToBoard(1, 1, PieceType.KING, u2);
		gc2.getBoard().addPieceToBoard(0, 1, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(1, 0, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(1, 2, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(2, 5, PieceType.ROOK, u);
		gc2.movePiece(2, 5, 2, 1); //Makes the winning move and checks the conditions of the gameController
		assertTrue(gc2.getWinner().equals(u));
		assertFalse(u.getCurrentGames().contains(gc2));
		assertFalse(u2.getCurrentGames().contains(gc2));
		assertTrue(u.getPastGames().contains(gc2));
		assertTrue(u2.getPastGames().contains(gc2));
		
		//Clears the default board
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				// Sets each space to be a piece of type NONE belonging to use none
				gc2.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
			}
		}
		
		//Sets up the King Winning Conditions to be one move away
		gc2.setCurrentTurn(u2);		
		gc2.getBoard().addPieceToBoard(0, 5, PieceType.KING, u2);
		gc2.movePiece(0, 5, 0, 0);
		assertTrue(gc2.getWinner().equals(u2)); //Makes the winning move and checks the conditions of the gameController
		assertFalse(u.getCurrentGames().contains(gc2));
		assertFalse(u2.getCurrentGames().contains(gc2));
		assertTrue(u.getPastGames().contains(gc2));
		assertTrue(u2.getPastGames().contains(gc2));
		
		//Clears the default board
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				// Sets each space to be a piece of type NONE belonging to use none
				gc2.getBoard().addPieceToBoard(row, col, PieceType.NONE, none);
			}
		}
		//Checks a piece can be captured from above when piece is moved
		gc2.setCurrentTurn(u);	
		gc2.getBoard().addPieceToBoard(2, 2, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(3, 2, PieceType.ROOK, u2);
		gc2.getBoard().addPieceToBoard(7, 2, PieceType.ROOK, u);
		gc2.movePiece(7, 2, 4, 2);
		assertTrue(gc2.getBoard().getPieceType(3, 2).equals(PieceType.NONE));
		
		//Checks a piece can be captured from below when piece is moved
		gc2.setCurrentTurn(u);	
		gc2.getBoard().addPieceToBoard(5, 2, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(8, 2, PieceType.ROOK, u2);
		gc2.getBoard().addPieceToBoard(9, 2, PieceType.ROOK, u);
		gc2.movePiece(5, 2, 7, 2);
		assertTrue(gc2.getBoard().getPieceType(8, 2).equals(PieceType.NONE));
		
		//Checks a piece can be captured from the right when piece is moved
		gc2.setCurrentTurn(u);	
		gc2.getBoard().addPieceToBoard(8, 5, PieceType.ROOK, u);
		gc2.getBoard().addPieceToBoard(8, 8, PieceType.ROOK, u2);
		gc2.getBoard().addPieceToBoard(8, 9, PieceType.ROOK, u);
		gc2.movePiece(8, 5, 8, 7);
		assertTrue(gc2.getBoard().getPieceType(8, 8).equals(PieceType.NONE));
		
		//Checks a Piece can be captured from the left when piece is moved -BEN CHECK HERE
		GameController gc3 = new GameController(2344, u, u2);
		
		gc3.setCurrentTurn(u);	
		gc3.getBoard().addPieceToBoard(2, 10, PieceType.KING, u);
		gc3.getBoard().addPieceToBoard(2, 8, PieceType.ROOK, u2);
		gc3.getBoard().addPieceToBoard(2, 7, PieceType.ROOK, u);
		gc3.movePiece(2, 10, 2, 9);
		assertTrue(gc2.getBoard().getPieceType(2, 8).equals(PieceType.NONE));
		
	}
	
	@Test
	public void quitTest(){
		GameController gc2 = new GameController(2345, u, u2);
		
		//If player1 quits
		gc.quit(u);
		assertTrue(gc.getWinner().equals(u2));
		assertFalse(u.getCurrentGames().contains(gc));
		assertFalse(u2.getCurrentGames().contains(gc));
		assertTrue(u.getPastGames().contains(gc));
		assertTrue(u2.getPastGames().contains(gc));
		
		//If player2 quits
		gc2.quit(u2);
		assertTrue(gc2.getWinner().equals(u));
		assertFalse(u.getCurrentGames().contains(gc2));
		assertFalse(u2.getCurrentGames().contains(gc2));
		assertTrue(u.getPastGames().contains(gc2));
		assertTrue(u2.getPastGames().contains(gc2));
	}

	
	
	

}
