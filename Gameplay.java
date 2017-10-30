public class Gameplay {
	
	// Gameplay sets up board, selects offence and defence, and holds the win conditions for the game. 
	// Gameplay also ends the games and sets the winner. 
	// For any functional movement see class "MakeMove". 
	
	// Gameplay should be called only ONCE at the beginning of a game. Gameplay will handle everything else. 
	// Gameplay will take care of using all other classes as necessary. 
 
	User Player1;
	User Player2;
	User defence;
	User offence;
	User currentTurn;
	User winner;
	GameStatus status;
	Board board;
	int kingLocationCol;
	int kingLocationRow;
	
	Piece[][] pieces = new Piece[11][11];
	
	public Gameplay (Board brd, User Player1, User Player2){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.PENDING;
		
		int rand = (int)Math.random();
		
		//Selects player1 or Player2 at random to start and set up board accordingly so each player is assigned either defence or offense
		if (rand % 2 == 0){
			this.currentTurn = Player1;
			offence = Player1;
			defence = Player2;
			board = new Board();
			SetBoard setupGame = new SetBoard(board, Player1, Player2);
			setupGame.setBoard();
		}
		else{
			this.currentTurn = Player2;
			offence = Player2;
			defence = Player1;
			board = new Board();
			SetBoard setupGame = new SetBoard(board, Player2, Player1);
			setupGame.setBoard();
		}
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
	
	// Checks if offence won
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
					break;
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
		
		return false;
	}

}