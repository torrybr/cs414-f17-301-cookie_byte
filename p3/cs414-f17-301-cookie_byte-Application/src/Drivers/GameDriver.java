package Drivers;
import Backend.Game;
import Backend.Piece;
import Backend.Move;
import Backend.User;
import Database.DatabaseManagerImpl;
import Database.GameJavaObject;
import java.util.List;
import java.util.ArrayList;

public class GameDriver {

	public String gameID;
	public Game game;
	public List<String> locations;
	public DatabaseManagerImpl DBDriver = new DatabaseManagerImpl();
	public Move move;

	public GameDriver(String gameID) {
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
		GameJavaObject temp = DBDriver.getGame(gameID);
		User User1 = new User("", temp.getPlayer1(), "", "");//fix
		User User2 = new User("", temp.getPlayer2(), "", "");//fix
		User User3 = new User("", temp.getCurrentTurn(), "", "");//fix
		game = new Game(gameID,User1,User2,temp.getPieceLocation(),User3);
		locations = temp.getPieceLocation();
	}
	public void createGame(String sender,String reciever) {
		User User1 = new User("", sender, "", "");
		User User2 = new User("", reciever, "", "");
		game = new Game("newGame", User1, User2);
	}

	public boolean makeMove(int x1, int y1, int x2, int y2,String name){
		move = new Move(name,game);
		game.getBoard().getPiece(x1,y1);
		boolean returnable = move.isMoveValid(game.getBoard().getPiece(x1,y1),x1,y1,x2,y2);
		if(returnable){
			move.movePiece(game.getBoard().getPiece(x1,y1),x1,y1,x2,y2);
			return true;
		}
		else {
			return false;
		}
	}
	public void saveMove(){
		List<String> returnable = new ArrayList<String>();
		Piece[] tempPieces = game.getBoard().getPieces();
		for(int i = 0;i < 37;i++){
			String temp = "";
			temp += tempPieces[i].getX() + " " + tempPieces[i].getY();
			returnable.add(temp);
		}
		DBDriver.updatePieceLocation(gameID,returnable);
	}
	public List<String> getLocations(){
		return locations;
	}
}
