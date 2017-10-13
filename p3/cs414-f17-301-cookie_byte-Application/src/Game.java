
public class Game {
 
	int GameID; 
	User Player1;
	User Player2;
	User Player3;
	User CurrentTurn;
	GameStatus status;
	User Winner;
	
	public Game (User Player1, User Player2){
		
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.status = GameStatus.INITATIED;
		this.GameID = setGameID();
		this.CurrentTurn = setCurrentTurn();
	}

	public int setGameID() {
	    //Random or in a Sequence
		return 0;
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

	public User setCurrentTurn() {
		//will determine at random with random generator
		return this.Player1;
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
