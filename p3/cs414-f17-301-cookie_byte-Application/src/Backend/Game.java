package Backend;
import java.lang.*; 

public class Game {
 
	int GameID; 
	User Player1;
	User Player2;
	User CurrentTurn;
	GameStatus status;
	User Winner;
	UniqueRandomNumbers rand = new UniqueRandomNumbers();
	Board board;
	int kingLocationCol;
	int kingLocationRow;
	
	public Game (int GameID, User Player1, User Player2){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.PENDING;
		this.GameID = GameID;
		
		
		//Selects player1 or Player2 at random to start and set up board accordingly so each player is assigned either defence or offense
		if (rand.offOrdef() == 1){
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
	public boolean kingWinConditions(){
		
		//if the king reaches any one of the four corners
		
		//top left corner
		if (!this.board.Spaces[0][0].isEmpty()){
			if(this.board.Spaces[0][0].getPiece().getType().equals(PieceType.KING)){
				this.setStatus(GameStatus.FINISHED);
				this.setWinner(this.board.Spaces[0][0].getPiece().getPlayer());
				Player1.PastGames.add(this);
				Player2.PastGames.add(this);
				Player1.winPercentage();
				Player2.winPercentage();
				return true;
			}
		}
		//top right corner
			if (!this.board.Spaces[0][10].isEmpty()){
				if(this.board.Spaces[0][10].getPiece().getType().equals(PieceType.KING)){
					this.setStatus(GameStatus.FINISHED);
					this.setWinner(this.board.Spaces[0][10].getPiece().getPlayer());
					Player1.PastGames.add(this);
					Player2.PastGames.add(this);
					Player1.winPercentage();
					Player2.winPercentage();
					return true;
				}
		}
		//bottom left corner
			if (!this.board.Spaces[10][0].isEmpty()){
					if(this.board.Spaces[10][0].getPiece().getType().equals(PieceType.KING)){
						this.setStatus(GameStatus.FINISHED);
						this.setWinner(this.board.Spaces[10][0].getPiece().getPlayer());
						Player1.PastGames.add(this);
						Player2.PastGames.add(this);
						Player1.winPercentage();
						Player2.winPercentage();
						return true;
					}
		}
		//bottom left corner			
		if (!this.board.Spaces[0][0].isEmpty()){
						if(this.board.Spaces[10][10].getPiece().getType().equals(PieceType.KING)){
							this.setStatus(GameStatus.FINISHED);
							this.setWinner(this.board.Spaces[10][10].getPiece().getPlayer());
							Player1.PastGames.add(this);
							Player2.PastGames.add(this);
							Player1.winPercentage();
							Player2.winPercentage();
							return true;
						}
		}
		return false;
	}
	public boolean attackWinConditions(){
		
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
			Player1.PastGames.add(this);
			Player2.PastGames.add(this);
			Player1.winPercentage();
			Player2.winPercentage();
			return true;
	   }
	return false;
	}
	
	

	

}
