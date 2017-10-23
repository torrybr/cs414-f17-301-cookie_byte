package Drivers;

import Backend.Game;
import Backend.User;

public class GameDriver {

	public int gameID;
	public Game game;
	
	public GameDriver(int gameID) {
		this.gameID = gameID;
		buildGame();
	}
	public GameDriver(String sender,String reciever) {
		createGame(sender, reciever);
	}
	public void buildGame() {
		//find game in db
		//build game from response
		//game = new Game(ID,player1,player2,board);
		User User1 = new User("", "p1", "", "");
		User User2 = new User("", "p2", "", "");
		game = new Game(0, User1, User2);
	}
	public void createGame(String sender,String reciever) {
		User User1 = new User("", sender, "", "");
		User User2 = new User("", reciever, "", "");
		game = new Game(0, User1, User2);
	}
}
