package Backend;
import java.lang.*; 

public class Game {
 
	int GameID; 
	User Player1;
	User Player2;
	User CurrentTurn;
	GameStatus status;
	User Winner;
	UniqueRandomNumbers rand;
	Board board;
	int kingLocationCol;
	int kingLocationRow;
	
	public Game (int GameID, User Player1, User Player2){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.PENDING;
		this.GameID = GameID;
		
		
		//Selects player1 or Player2 at random to start and set up board accordingly so each player is assigned either defence or offense
		int rand = (int)Math.random();
		if (rand % 2 == 0){
			this.CurrentTurn = Player1;
			board =new Board(Player1,Player2);
		}
		else{
			this.CurrentTurn = Player2;
			board =new Board(Player2,Player1);
		}
	}
	public Game (int GameID, User Player1, User Player2, Board savedGame, User CurrentTurn){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.PENDING;
		this.GameID = GameID;
		this.CurrentTurn= CurrentTurn;
		this.board = savedGame;
	}

	public User getPlayer1() {
		return Player1;
	}

	public void setPlayer1(User player1) {
		Player1 = player1;
	}

	public User getPlayer2() {
		return Player2;
	}

	public void setPlayer2(User player2) {
		Player2 = player2;
	}
	
	public Board getBoard() {
		return board;
	}

	public User getCurrentTurn() {
		return CurrentTurn;
	}

	public void setCurrentTurn(User player) {
		CurrentTurn= player;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public User getWinner() {
		return Winner;
	}

	public void setWinner(User winner) {
		Winner = winner;
	}
	
	//Checks all win conditions of the game, sets and updates history accordingly
	public void kingWinConditions(){
		
		//if the king reaches any one of the four corners
		
		//top left corner
		if (!this.board.Spaces[0][0].isEmpty()){
			if(this.board.Spaces[0][0].getPiece().getType().equals(PieceType.KING)){
				this.setStatus(GameStatus.FINISHED);
				this.setWinner(this.board.Spaces[0][0].getPiece().getPlayer());
				Player1.PassGames.add(this);
				Player2.PassGames.add(this);
			}
		}
		//top right corner
			if (!this.board.Spaces[0][10].isEmpty()){
				if(this.board.Spaces[0][10].getPiece().getType().equals(PieceType.KING)){
					this.setStatus(GameStatus.FINISHED);
					this.setWinner(this.board.Spaces[0][10].getPiece().getPlayer());
					Player1.PassGames.add(this);
					Player2.PassGames.add(this);
				}
		}
		//bottom left corner
			if (!this.board.Spaces[10][0].isEmpty()){
					if(this.board.Spaces[10][0].getPiece().getType().equals(PieceType.KING)){
						this.setStatus(GameStatus.FINISHED);
						this.setWinner(this.board.Spaces[10][0].getPiece().getPlayer());
						Player1.PassGames.add(this);
						Player2.PassGames.add(this);
					}
		}
		//bottom left corner			
		if (!this.board.Spaces[0][0].isEmpty()){
						if(this.board.Spaces[10][10].getPiece().getType().equals(PieceType.KING)){
							this.setStatus(GameStatus.FINISHED);
							this.setWinner(this.board.Spaces[10][10].getPiece().getPlayer());
							Player1.PassGames.add(this);
							Player2.PassGames.add(this);
						}
		}
	}
	public void attackWinConditions(){
		
		//or if the king is cornered on all 4 sides
		//first it searches for the king and locates it on the board
		Integer Checkmate1 = null,Checkmate2 = null,Checkmate3 = null,Checkmate4 =null; 
		boolean assertCheck1 = false,assertCheck2 = false,assertCheck3= false,assertCheck4 = false;
		Piece King = null;
		
		for (int i=0; i<11; i++){
				for (int j=0; j<11; j++){
					if(this.board.Spaces[i][j].getPiece().getType().equals(PieceType.KING)){
						kingLocationRow = i;
						kingLocationCol =j;
						King = this.board.Spaces[i][j].getPiece(); 
						
						//Saves the locations of all spaces that are a threat to the king
						if ((0 <= (kingLocationCol+1)) &&  ((kingLocationCol+1) <= 10))
							Checkmate1=kingLocationCol+1;
						
						if ((0 <= (kingLocationCol-1)) &&  ((kingLocationCol-1) <= 10))
							Checkmate2=kingLocationCol-1;
	
						if ((0 <= (kingLocationRow+1)) &&  ((kingLocationRow+1) <= 10))
							Checkmate3=kingLocationRow+1;
						
						if ((1 <= (kingLocationRow-1)) &&  ((kingLocationRow-1) <= 10))
							Checkmate4=kingLocationRow-1;
	
				}
			}
		}
		
		//checks if all of the spaces have enemy pieces on them according to their 
		for (int i=0; i<11; i++){
			for (int j=0; j<11; j++){
				
			if (Checkmate1 != null)
				if (!this.board.Spaces[kingLocationRow][kingLocationCol+1].isEmpty())
					//get the piece on the location space and compares it to the player that the King belongs to
					if (!this.board.Spaces[kingLocationRow][kingLocationCol+1].getPiece().getPlayer().equals(King.getPlayer()));
					//sets the assertions to the condition
					assertCheck1= true;
					
			if (Checkmate2 != null)	
				if (!this.board.Spaces[kingLocationRow][kingLocationCol-1].isEmpty())
					//get the piece on the location space and compares it to the player that the King belongs to
					if (this.board.Spaces[kingLocationRow][kingLocationCol-1].getPiece().getPlayer().equals(King.getPlayer()));
					//sets the assertions to the condition
					assertCheck2= true;
					
			if (Checkmate3 != null)		
				if (!this.board.Spaces[kingLocationRow+1][kingLocationCol].isEmpty())
					//get the piece on the location space and compares it to the player that the King belongs to
					if (this.board.Spaces[kingLocationRow+1][kingLocationCol].getPiece().getPlayer().equals(King.getPlayer()));
					//sets the assertions to the condition
					assertCheck3= true;
					
			if (Checkmate4 != null)		
				if (!this.board.Spaces[kingLocationRow-1][kingLocationCol].isEmpty())
					//get the piece on the location space and compares it to the player that the King belongs to
					if (this.board.Spaces[kingLocationRow-1][kingLocationCol].getPiece().getPlayer().equals(King.getPlayer()));
					//sets the assertions to the condition
					assertCheck4= true;			
			}
		}
	
		//King must be sandwiched on all 4 sides
	   if (assertCheck1 && assertCheck2 && assertCheck3 && assertCheck4 == true){
			this.setStatus(GameStatus.FINISHED);
			
			//whoever owns the king loses
			if (King.getPlayer().equals(Player1))
				this.setWinner(Player2);
			else
				this.setWinner(Player1);
			
		    //adds results in history
			Player1.PassGames.add(this);
			Player2.PassGames.add(this);
	   }
	
	}
	
	public boolean isMoveValid(Piece piece, int rowFrom, int colFrom, int rowTo, int colTo){
		// Straight line check
		if(rowFrom != rowTo && colFrom != colTo)
		{
			return false;
		}
		// In range of board check
		if(rowFrom > 10 || rowFrom < 0 || rowTo > 10 || rowTo < 0 || colFrom > 10 || colFrom < 0 || colTo > 10 || colTo < 0)
		{
			return false;
		}
		
		//Is the piece moving to a corner & not a king check
		if(piece.getType() != PieceType.KING)
		{
			if(rowTo == 0 && colTo == 0)
			{
				return false;
			}
			else if(rowTo == 0 && colTo == 10)
			{
				return false;
			}
			else if(rowTo == 10 && colTo == 0)
			{
				return false;
			}
			else if(rowTo == 10 && colTo == 10)
			{
				return false;
			}
		}
		
		//Is there a piece in the way check
		if(rowFrom == rowTo)
		{
			for(int i = colFrom; i <= colTo; i++)
			{
				if(!board.Spaces[rowFrom][i].isEmpty())
				{
					return false;
				}
			}
		}
		else
		{
			for(int i = rowFrom; i <= rowTo; i++)
			{
				if(!board.Spaces[i][colFrom].isEmpty())
				{
					return false;
				}
			}
		}		
		return true;
	}
}
