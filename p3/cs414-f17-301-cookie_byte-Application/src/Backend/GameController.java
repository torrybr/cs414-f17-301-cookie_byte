package Backend;

public class GameController {
	
	// Gameplay sets up board, selects offence and defence, and holds the win conditions for the this. 
	// Gameplay also ends the games and sets the winner. 
	// For any functional movement see class "MakeMove". 
	
	// Gameplay constructor should be called only ONCE at the beginning of a this. Win conditions should be used after each move. Gameplay will handle everything else. 
	// Gameplay will take care of using all other classes as necessary. 
 
	User player1;
	User player2;
	User defence;
	User offence;
	User currentTurn;
	User winner;
	GameStatus status;
	Board board;
	int kingLocationCol;
	int kingLocationRow;
	int gameID;

	
	Piece[][] pieces = new Piece[11][11];
	
	public GameController(int gmeID, User player1, User player2){
		
		board = new Board();
		
		this.player1 = player1;
		this.player2 = player2;
		this.status = GameStatus.PENDING;
		gameID = gmeID;
		
		
		// This is giving a warning because the random player 1 or 2 selection is commented out
		// directly below for testing. 
		int rand = (int)Math.random();
		
		// TODO remove comment block below after testing
		//Selects player1 or player2 at random to start and set up board accordingly so each player is assigned either defence or offense
	//	if (rand % 2 == 0){
			this.currentTurn = player1;
			offence = player1;
			defence = player2;
			SetBoard setupGame = new SetBoard(board, player1, player2);
			setupGame.setBoard();
			this.setStatus(GameStatus.ACTIVE);
			player1.addCurrentGame(this);
			player2.addCurrentGame(this);
			
	/*	}
		else{
			this.currentTurn = player2;
			offence = player2;
			defence = player1;
			SetBoard setupGame = new SetBoard(board, player2, player1);
			setupGame.setBoard();
			this.setStatus(GameStatus.ACTIVE);
		} */
	}
	
	//  Constructor used to get game from database
	public GameController(int gmeID)
	{	
		this.gameID = gmeID;
	}
	
	public void quit(User quitter)
	{
		if(quitter == player1)
		{
			this.setWinner(player2);
			player2.addWin();
			player1.addLoss();
		}
		else
		{
			this.setWinner(player1);
			player1.addWin();
			player2.addLoss();
		}
		
		player1.removeCurrentGame(this);
		player2.removeCurrentGame(this);
		
		player1.addPastGame(this);
		player2.addPastGame(this);
		
	}
	
	// Send game to database method
	
	public int getGameID()
	{
		return gameID;
	}
	
	public void setGameID(int gmeID)
	{
		gameID = gmeID;
	}
	
	public Board getBoard()
	{
		return board;
	}
	
	public boolean isItMyTurn(User player)
	{
		if(currentTurn.equals(player))
		{
			return true;
		}
		return false;
	}
	
	public User getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(User player) {
		currentTurn= player;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winn) {
		winner = winn;
	}
	
	
	// Checks if defence won
	public boolean kingWinConditions(){
		
		//if the king reaches any one of the four corners
		
		//top left corner
		if(board.pieces[0][0].getType().equals(PieceType.KING))
		{
			this.setStatus(GameStatus.FINISHED);
			this.setWinner(defence);
			return true;	
		}
		//top right corner
		if(board.pieces[0][10].getType().equals(PieceType.KING))
		{
			this.setStatus(GameStatus.FINISHED);
			this.setWinner(defence);
			return true;	
		}
		//bottom left corner
		if(board.pieces[10][0].getType().equals(PieceType.KING))
		{
			this.setStatus(GameStatus.FINISHED);
			this.setWinner(defence);
			return true;	
		}
		//bottom left corner			
		if(board.pieces[10][10].getType().equals(PieceType.KING))
		{
			this.setStatus(GameStatus.FINISHED);
			this.setWinner(defence);
			return true;	
		}
		return false;
	}
	
	// Checks if offense won
	public boolean attackWinConditions()
	{
		// First section find location of king
		int kingRow = 0;
		int kingCol = 0;
		
		for(int row = 0; row < 11; row++)
		{
			for(int col = 0; col < 11; col++)
			{
				if(board.pieces[row][col].getType().equals(PieceType.KING))
				{
					kingRow = row;
					kingCol = col;
					System.out.println(kingRow);
					System.out.println(kingCol);
				}
			}
		}
		
		if(kingRow == 0 || kingRow == 10 || kingCol == 0 || kingCol == 10)
		{
			return false;
		}
		// King can be taken if surrounded on 3 sides when next to the middle square
		// Left of middle
		else if(kingRow == 5 && kingCol == 4)
		{
			if(board.pieces[6][4].getPlayer().equals(offence))
			{
				if(board.pieces[5][3].getPlayer().equals(offence))
				{
					if(board.pieces[4][4].getPlayer().equals(offence))
					{
						return true;
					}
				}
			}
		}
		
		// Top of middle
		else if(kingRow == 4 && kingCol == 5)
		{
			if(board.pieces[3][5].getPlayer().equals(offence))
			{
				if(board.pieces[4][6].getPlayer().equals(offence))
				{
					if(board.pieces[4][4].getPlayer().equals(offence))
					{
						return true;
					}
				}
			}
		}
		
		// Right of middle
		else if(kingRow == 5 && kingCol == 6)
		{
			if(board.pieces[4][6].getPlayer().equals(offence))
			{
				if(board.pieces[6][6].getPlayer().equals(offence))
				{
					if(board.pieces[5][7].getPlayer().equals(offence))
					{
						return true;
					}
				}
			}
		}
		
		// Bottom of middle
		else if(kingRow == 6 && kingCol == 5)
		{
			if(board.pieces[6][4].getPlayer().equals(offence))
			{
				if(board.pieces[7][5].getPlayer().equals(offence))
				{
					if(board.pieces[6][6].getPlayer().equals(offence))
					{
						return true;
					}
				}
			}
		}
		// Checks if king is surrounded on all 4 sides
		else if(board.pieces[kingRow - 1][kingCol].getPlayer().equals(offence) && board.pieces[kingRow + 1][kingCol].getPlayer().equals(offence) 
				&& board.pieces[kingRow][kingCol - 1].getPlayer().equals(offence) && board.pieces[kingRow][kingCol + 1].getPlayer().equals(offence))
		{
			return true;
		}
		
		System.out.println("Attack 1 - Row: " + (kingRow - 1) + " Col: " + kingCol);
		System.out.println("Attack 2 - Row: " + (kingRow + 1) + " Col: " + kingCol);
		System.out.println("Attack 3 - Row: " + kingRow + " Col: " + (kingCol - 1));
		System.out.println("Attack 4 - Row: " + kingRow + " Col: " + (kingCol + 1));
		return false;
	}
	
	////////////
	///////////
	///////////
	///////////
	
	public boolean isMoveValid(Piece piece, User player, int rowFrom, int colFrom, int rowTo, int colTo) 
	{
		// Check if the player trying to move is allowed (i.e. their turn)
		if (!this.isItMyTurn(player)) 
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
			if(this.attackWinConditions())
			{
				// TODO Attacker wins. Save game (winner, games history, etc)
				this.setStatus(GameStatus.FINISHED);
				this.setWinner(offence);
				System.out.println("Player 1 wins!");
				
				
			}
			else if(this.kingWinConditions())
			{
				// TODO Defender wins. Save game (winner, games history, etc)
				this.setStatus(GameStatus.FINISHED);
				this.setWinner(defence);
				System.out.println("Player 2 wins!");
			}
			
			//Check if we killed any enemies (capturePiece handles out of bounds checks)
			if(capturePiece(this.getCurrentTurn(), rowTo+1, colTo)){
				board.removePiece(rowTo+1, colTo);
				System.out.println("Piece removed down.");
			}
			if(capturePiece(this.getCurrentTurn(), rowTo-1, colTo)){
				board.removePiece(rowTo-1, colTo);
				System.out.println("Piece removed up.");
			}
			if(capturePiece(this.getCurrentTurn(), rowTo, colTo+1)){
				board.removePiece(rowTo, colTo+1);
				System.out.println("Piece removed right.");
			}
			if(capturePiece(this.getCurrentTurn(), rowTo, colTo-1)){
				board.removePiece(rowTo, colTo-1);
				System.out.println("Piece removed left.");
			}
		
			// Make sure to set the other player as the one to take a turn next
			if(this.getCurrentTurn().equals(offence))
			{
				this.setCurrentTurn(defence);
			}
			else
			{
				this.setCurrentTurn(offence);
			}
		}
	}

}
