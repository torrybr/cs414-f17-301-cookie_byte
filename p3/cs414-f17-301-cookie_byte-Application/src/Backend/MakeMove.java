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
	 
	 public MakeMove (User player, Gameplay game, User offence, User defence)
	 {
		 this.player = player;
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
			return false;
		}
		
		// Check if player is trying to move their opponent's piece
		if(!board.pieces[rowFrom][colFrom].getPlayer().equals(player))
		{
			return false;
		}
		
		// Check if piece is actually being moved
		if(rowFrom == rowTo && colFrom == colTo)
		{
			return false;
		}

		// Straight line check
		if (rowFrom != rowTo && colFrom != colTo)
		{
			return false;
		}
		// In range of board check
		if (rowFrom > 10 || rowFrom < 0 || rowTo > 10 || rowTo < 0 || colFrom > 10 || colFrom < 0 || colTo > 10
				|| colTo < 0)
		{
			return false;
		}
		// Is the piece moving to a corner OR the center piece & not a king check
		if (piece.getType() != PieceType.KING)
		{
			if (rowTo == 0 && colTo == 0)
			{
				return false;
			} 
			else if (rowTo == 0 && colTo == 10) 
			{
				return false;
			} 
			else if (rowTo == 10 && colTo == 0) 
			{
				return false;
			} 
			else if (rowTo == 10 && colTo == 10) 
			{
				return false;
			}
			else if (rowTo == 5 && colTo == 5)
			{
				return false;
			}
		}

		// Is there a piece in the way check
		if (rowFrom == rowTo) 
		{
			// going up
			if (colFrom < colTo) 
			{
				for (int i = colFrom + 1; i <= colTo; i++) 
				{
					System.out.println(i);
					if (board.pieces[rowFrom][i].getType() != PieceType.NONE) 
					{
						return false;
					}
				}
			} 
			else 
			{
				// going down
				for (int i = colFrom - 1; i >= colTo; i--) 
				{
					System.out.println(i);
					if (board.pieces[rowFrom][i].getType() != PieceType.NONE) 
					{
						return false;
					}
				}
			}
		} 
		else 
		{
			// going up
			if (rowFrom < rowTo) 
			{
				for (int i = rowFrom + 1; i <= rowTo; i++) 
				{
					System.out.println(i);
					if (board.pieces[i][colFrom].getType() != PieceType.NONE) 
					{
						return false;
					}
				}
			} 
			else 
			{
				for (int i = rowFrom - 1; i >= rowTo; i--) 
				{
					System.out.println(i);
					if (board.pieces[rowFrom][i].getType() != PieceType.NONE) 
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
		if (board.pieces[row - 1][col].getType() != PieceType.NONE && board.pieces[row-1][col].getPlayer() != player && board.pieces[row + 1][col].getType() != PieceType.NONE && board.pieces[row+1][col].getPlayer() != player) {
			return true;
		} else if (board.pieces[row][col - 1].getType() != PieceType.NONE && board.pieces[row][col-1].getPlayer() != player && board.pieces[row][col + 1].getType() != PieceType.NONE && board.pieces[row][col+1].getPlayer() != player) {
			return true;
		} else
			return false;
	}
	
	public void movePiece(int rowFrom, int colFrom, int rowTo, int colTo){
		
		//checks to see if the move is valid
		if(isMoveValid(board.pieces[rowFrom][colFrom], board.pieces[rowFrom][colFrom].getPlayer(), rowFrom, colFrom, rowTo, colTo)){
			
			//Actually move piece
			board.movePiece(rowFrom, colFrom, rowTo, colTo);
			
			// Check win conditions
			if(game.attackWinConditions())
			{
				// TODO Attacker wins. Save game (winner, games history, etc)
				game.setStatus(GameStatus.FINISHED);
				game.setWinner(Player1);
				
				
			}
			else if(game.kingWinConditions())
			{
				// TODO Defender wins. Save game (winner, games history, etc)
				game.setStatus(GameStatus.FINISHED);
				game.setWinner(Player2);
			}
			
			//Check if we killed any enemies (capturePiece handles out of bounds checks)
			if(capturePiece(player, rowTo+1, colTo)){
				board.removePiece(rowTo+1, colTo);
			}
			if(capturePiece(player, rowTo-1, colTo)){
				board.removePiece(rowTo-1, colTo);
			}
			if(capturePiece(player, rowTo, colTo+1)){
				board.removePiece(rowTo, colTo+1);
			}
			if(capturePiece(player, rowTo, colTo-1)){
				board.removePiece(rowTo, colTo-1);
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
