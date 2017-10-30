package Backend;


public class MakeMove {
	
	// MakeMove checks that the desired move is valid, and then moves the piece. 
	// MakeMove also sets whose turn it is and checks the win conditions located
	// in Gameplay. VERY important that offence passed before defence (i.e. don't 
	// mix them up)
	
	// MakeMove is what actually runs the game! This should be called for EVERY move. 
	// MakeMove will take care of using all other classes as necessary. 
	
	 User player;
	 Gameplay game;
	 Board board;
	 User Player1;
	 User Player2;
	 
	 public MakeMove (Gameplay game, User offence, User defence)
	 {
		 this.game = game;
		 this.board = game.getBoard();
		 Player1 = offence;
		 Player2 = defence;
	 }
	 
	public boolean isMoveValid(Piece piece, User player, int rowFrom, int colFrom, int rowTo, int colTo) 
	{
		// Check if the player trying to move is allowed (i.e. their turn)
		if (!game.isItMyTurn(player)) 
		{
			System.out.println("Not your turn. ");
			return false;
		}
		
		// Check if player is trying to move their opponent's piece
		if(!board.pieces[rowFrom][colFrom].getPlayer().equals(player))
		{
			System.out.println("Not your piece. ");
			return false;
		}
		
		// Check if player is trying to move nonexistant piece
		if(board.pieces[rowFrom][colFrom].getType().equals(PieceType.NONE))
		{
			return false;
		}
		
		// Check if piece is actually being moved
		if(rowFrom == rowTo && colFrom == colTo)
		{
			System.out.println("Can't stay in same spot. ");
			return false;
		}

		// Straight line check
		if (rowFrom != rowTo && colFrom != colTo)
		{
			System.out.println("Must move in a straight line. ");
			return false;
		}
		// In range of board check
		if (rowFrom > 10 || rowFrom < 0 || rowTo > 10 || rowTo < 0 || colFrom > 10 || colFrom < 0 || colTo > 10
				|| colTo < 0)
		{
			System.out.println("Not inside board. ");
			return false;
		}
		// Is the piece moving to a corner OR the center piece & not a king check
		if (piece.getType() != PieceType.KING)
		{
			if (rowTo == 0 && colTo == 0)
			{
				System.out.println("Space reserved for king. ");
				return false;
			} 
			else if (rowTo == 0 && colTo == 10) 
			{
				System.out.println("Space reserved for king. ");
				return false;
			} 
			else if (rowTo == 10 && colTo == 0) 
			{
				System.out.println("Space reserved for king. ");
				return false;
			} 
			else if (rowTo == 10 && colTo == 10) 
			{
				System.out.println("Space reserved for king. ");
				return false;
			}
			else if (rowTo == 5 && colTo == 5)
			{
				System.out.println("Space reserved for king. ");
				return false;
			}
		}

		// Is there a piece in the way check
		// Left and right
		if(rowFrom == rowTo)
		{
			// Moving to the right
			if(colFrom < colTo)
			{
				for(int j = colFrom + 1; j <= colTo; j++)
				{
					if(!board.getPieceType(rowFrom, j).equals(PieceType.NONE))
					{
						return false;
					}
				}
			}
			// Moving to the left
			else
			{
				for(int j = colFrom - 1; j >= colTo; j--)
				{
					if(!board.getPieceType(rowFrom, j).equals(PieceType.NONE))
					{
						return false;
					}
				}
			}
		}
		// Moving up and down
		else
		{
			// Moving down
			if(rowFrom < rowTo)
			{
				for(int i = rowFrom + 1; i <= rowTo; i++)
				{
					if(!board.getPieceType(i, colFrom).equals(PieceType.NONE))
					{
						return false;
					}
				}
			}
			// Moving up
			else
			{
				for(int i = rowFrom - 1; i >= rowTo; i--)
				{
					if(!board.getPieceType(i, colFrom).equals(PieceType.NONE))
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public boolean capturePiece(User player, int row, int col) {
		
		
		// Make sure we're not checking out of bounds
		if (row < 0 || row > 10 || col < 0 || col > 10) {
			return false;
		}
		
		// Check if player is trying to capture nonexistant piece
		if(board.pieces[row][col].getType().equals(PieceType.NONE))
		{
			return false;
		}
		
		// Check to see if we are on an edge and if next to a corner, check if
		// needs to be removed
		if (row == 0 || row == 10) {
			if (col != 1 && col != 9) {
				return false;
			} else if (row == 0) {
				// Checking next to top right corner
				if (col == 1) {
					if (board.pieces[0][2].getType() != PieceType.NONE && board.pieces[0][2].getPlayer() != player) {
						return true;
					}
				}
				// Checking next to top left corner
				if (col == 9) {
					if (board.pieces[0][8].getType() != PieceType.NONE && board.pieces[0][8].getPlayer() != player) {
						return true;
					}
				}
			} else if (row == 10) {
				// Checking next to bottom right corner
				if (col == 1) {
					if (board.pieces[10][2].getType() != PieceType.NONE && board.pieces[10][2].getPlayer() != player) {
						return true;
					}
				}
				// Checking next to bottom left corner
				if (col == 9) {
					if (board.pieces[10][8].getType() != PieceType.NONE && board.pieces[10][8].getPlayer() != player) {
						return true;
					}
				}
			}
		} else if (col == 0 || col == 10) {
			if (row != 1 && row != 9) {
				return false;
			} else if (col == 0) {
				// Checking next to top right corner
				if (row == 1) {
					if (board.pieces[2][0].getType() != PieceType.NONE && board.pieces[2][0].getPlayer() != player) {
						return true;
					}
				}
				// Checking next to top left corner
				if (row == 9) {
					if (board.pieces[8][0].getType() != PieceType.NONE && board.pieces[0][8].getPlayer() != player) {
						return true;
					}
				}
			} else if (col == 10) {
				// Checking next to bottom right corner
				if (row == 1) {
					if (board.pieces[2][10].getType() != PieceType.NONE && board.pieces[10][2].getPlayer() != player) {
						return true;
					}
				}
				// Checking next to bottom left corner
				if (row == 9) {
					if (board.pieces[8][10].getType() != PieceType.NONE && board.pieces[10][8].getPlayer() != player) {
						return true;
					}
				}
			}
		}
		// Do the general checks to see if something should be removed
		if((row - 1) < 0 || (row + 1) > 10 || (col - 1) < 0 || (col + 1) > 10)
		{
			return false;
		}
		if (board.pieces[row - 1][col].getType() != PieceType.NONE && board.pieces[row + 1][col].getType() != PieceType.NONE) {
			if(!board.getPieceOwner(row-1, col).equals(board.getPieceOwner(row, col)) && !board.getPieceOwner(row+1, col).equals(board.getPieceOwner(row, col)))
			{
				return true;
			}
		}
		if (board.pieces[row][col - 1].getType() != PieceType.NONE && board.pieces[row][col + 1].getType() != PieceType.NONE) {
			if(!board.getPieceOwner(row, col-1).equals(board.getPieceOwner(row, col)) && !board.getPieceOwner(row, col+1).equals(board.getPieceOwner(row, col)))
			{
				return true;
			}
		} 
		return false;

	}
	
	public void movePiece(int rowFrom, int colFrom, int rowTo, int colTo){
		
		//checks to see if the move is valid
		if(isMoveValid(board.pieces[rowFrom][colFrom], board.pieces[rowFrom][colFrom].getPlayer(), rowFrom, colFrom, rowTo, colTo)){
			
			//Actually move piece
			board.movePiece(rowFrom, colFrom, rowTo, colTo);
			System.out.println("Piece moved from: " + rowFrom + ", " + colFrom + " to " + rowTo + ", " + colTo);
			
			// Check win conditions
			if(game.attackWinConditions())
			{
				// TODO Attacker wins. Save game (winner, games history, etc)
				game.setStatus(GameStatus.FINISHED);
				game.setWinner(Player1);
				System.out.println("Player 1 wins!");
				
				
			}
			else if(game.kingWinConditions())
			{
				// TODO Defender wins. Save game (winner, games history, etc)
				game.setStatus(GameStatus.FINISHED);
				game.setWinner(Player2);
				System.out.println("Player 2 wins!");
			}
			
			//Check if we killed any enemies (capturePiece handles out of bounds checks)
			if(capturePiece(game.getCurrentTurn(), rowTo+1, colTo)){
				board.removePiece(rowTo+1, colTo);
				System.out.println("Piece removed down.");
			}
			if(capturePiece(game.getCurrentTurn(), rowTo-1, colTo)){
				board.removePiece(rowTo-1, colTo);
				System.out.println("Piece removed up.");
			}
			if(capturePiece(game.getCurrentTurn(), rowTo, colTo+1)){
				board.removePiece(rowTo, colTo+1);
				System.out.println("Piece removed right.");
			}
			if(capturePiece(game.getCurrentTurn(), rowTo, colTo-1)){
				board.removePiece(rowTo, colTo-1);
				System.out.println("Piece removed left.");
			}
		
			// Make sure to set the other player as the one to take a turn next
			if(game.getCurrentTurn().equals(Player1))
			{
				game.setCurrentTurn(Player2);
			}
			else
			{
				game.setCurrentTurn(Player1);
			}
		}
	}
}
