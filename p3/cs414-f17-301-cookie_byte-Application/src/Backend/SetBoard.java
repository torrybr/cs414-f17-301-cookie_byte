package Backend;

import Database.DatabaseManagerImpl;

public class SetBoard {
	
	// SetBoard uses the board class to set the correct starting location of all pieces
	// for each new game. Once a board has been created, this class will be called to 
	// actually place the pieces on the board. Nothing else is done in this class. 
	
	// This class should ONLY be used by Gameplay. 
	
	Board board = new Board();
	User offence;
	User defence;
	User none = new User("nullUser", "nullUser", "null@null.null");
	DatabaseManagerImpl db = new DatabaseManagerImpl();
	
	public SetBoard(Board brd, User off, User def)
	{
		this.board = brd;
		offence = off;
		defence = def;
	}
	
	public void setBoard()
	{
		
		// Offence top pieces
		board.addPieceToBoard(0, 3, PieceType.ROOK, offence);
		board.addPieceToBoard(0, 4, PieceType.ROOK, offence);
		board.addPieceToBoard(0, 5, PieceType.ROOK, offence);
		board.addPieceToBoard(0, 6, PieceType.ROOK, offence);
		board.addPieceToBoard(0, 7, PieceType.ROOK, offence);
		board.addPieceToBoard(1, 5, PieceType.ROOK, offence);
		
		// Offence left pieces
		board.addPieceToBoard(3, 0, PieceType.ROOK, offence);
		board.addPieceToBoard(4, 0, PieceType.ROOK, offence);
		board.addPieceToBoard(5, 0, PieceType.ROOK, offence);
		board.addPieceToBoard(6, 0, PieceType.ROOK, offence);
		board.addPieceToBoard(7, 0, PieceType.ROOK, offence);
		board.addPieceToBoard(5, 1, PieceType.ROOK, offence);
		
		// Offence right pieces
		board.addPieceToBoard(3, 10, PieceType.ROOK, offence);
		board.addPieceToBoard(4, 10, PieceType.ROOK, offence);
		board.addPieceToBoard(5, 10, PieceType.ROOK, offence);
		board.addPieceToBoard(6, 10, PieceType.ROOK, offence);
		board.addPieceToBoard(7, 10, PieceType.ROOK, offence);
		board.addPieceToBoard(5, 9, PieceType.ROOK, offence);
		
		// Offcence bottom pieces
		board.addPieceToBoard(10, 3, PieceType.ROOK, offence);
		board.addPieceToBoard(10, 4, PieceType.ROOK, offence);
		board.addPieceToBoard(10, 5, PieceType.ROOK, offence);
		board.addPieceToBoard(10, 6, PieceType.ROOK, offence);
		board.addPieceToBoard(10, 7, PieceType.ROOK, offence);
		board.addPieceToBoard(9, 5, PieceType.ROOK, offence);
		
		// Defence from the TOP LEFT to the BOTTOM RIGHT in order
		
		board.addPieceToBoard(3, 5, PieceType.ROOK, defence);
		board.addPieceToBoard(4, 4, PieceType.ROOK, defence);
		board.addPieceToBoard(4, 5, PieceType.ROOK, defence);
		board.addPieceToBoard(4, 6, PieceType.ROOK, defence);
		board.addPieceToBoard(5, 3, PieceType.ROOK, defence);
		board.addPieceToBoard(5, 4, PieceType.ROOK, defence);
		board.addPieceToBoard(5, 5, PieceType.KING, defence);
		board.addPieceToBoard(5, 6, PieceType.ROOK, defence);
		board.addPieceToBoard(5, 7, PieceType.ROOK, defence);
		board.addPieceToBoard(6, 4, PieceType.ROOK, defence);
		board.addPieceToBoard(6, 5, PieceType.ROOK, defence);
		board.addPieceToBoard(6, 6, PieceType.ROOK, defence);
		board.addPieceToBoard(7, 5, PieceType.ROOK, defence);
		
		// Make sure all spaces without a piece have a PieceType of NONE
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				// Sets each space to be a piecd of type NONE belonging to use none
				if(board.getPiece(row, col) == null)
				{
					board.addPieceToBoard(row, col, PieceType.NONE, none);
				}
			}
		}
	}
}

