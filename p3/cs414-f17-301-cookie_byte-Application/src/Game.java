
public class Game {
 
	int GameID; 
	User Player1;
	User Player2;
	User CurrentTurn;
	GameStatus status;
	User Winner;
	UniqueRandomNumbers rand;
	
	public Game (int GameID ,User Player1, User Player2){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.PENNDING;
		this.GameID = GameID;
		
		//Selects player1 or Player2 at random to start
		if (rand.OfforDef() == 1)
			this.CurrentTurn = Player1;
		else
			this.CurrentTurn = Player2;
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

	public User getCurrentTurn() {
		return CurrentTurn;
	}

	public void setCurrentTurn( User player) {
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
	
	
}
