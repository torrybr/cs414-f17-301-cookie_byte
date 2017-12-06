package Backend;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

public abstract class DatabaseTranslator {

	//Return a list of invites for the User 
	public static List<Invite> getDbInvites(User u)
	{
		List<Invite> newList = new ArrayList<Invite>();
		
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(u.getUserID());
		
		List<Database.Invite> dbInvites = temp.getInvites();
		for(int i = 0; i < dbInvites.size(); i++) 
		{	
			String userFromId = dbInvites.get(i).getInvite().getUserFrom();
			String fromEmail =  DatabaseManagerImpl.getUserByNickname(userFromId).getEmail();
			String fromPassword = DatabaseManagerImpl.getUserByNickname(userFromId).getPassword();
			User tempFrom = new User(userFromId, fromPassword, fromEmail);
			
			int tempGameID = dbInvites.get(i).getInvite().getGameID();
		
			Invite invite = new Invite(getUser(u.userID), tempFrom, tempGameID);
			String status = dbInvites.get(i).getInvite().getInvitationStatus();
			invite.setStatus(InvitationStatus.valueOf(status));
			
			newList.add(invite);
		}
		return newList;
	}
	
	//Return a certain player from a certain game
	public static User getPlayerFromGame(int player, int gameID) 
	{	
		UsersJavaObject thePlayer;
		User playerToReturn = null;
		
		//check if returning player 1 or player 2
		if(player == 1) 
		{
			thePlayer = DatabaseManagerImpl.getUserByNickname(DatabaseManagerImpl.getGame(gameID).getPlayer1());
			playerToReturn = new User(thePlayer.getNickname(), 
									  thePlayer.getPassword(), 
									  thePlayer.getEmail());
		}
		else
		{
			thePlayer = DatabaseManagerImpl.getUserByNickname(DatabaseManagerImpl.getGame(gameID).getPlayer2());
			playerToReturn = new User(thePlayer.getNickname(), 
									  thePlayer.getPassword(), 
									  thePlayer.getEmail());
		}
		
		//check if player being returned is offence or defence
		if(DatabaseManagerImpl.getGame(gameID).getOffense().equals(thePlayer.getNickname()))
		{
			playerToReturn.setOffence(true);
		} else
		{
			playerToReturn.setOffence(false);
		}
		
		//check if it is the players turn
		System.out.println(DatabaseManagerImpl.getGame(gameID).getCurrentTurn());
		if(DatabaseManagerImpl.getGame(gameID).getCurrentTurn().equals(thePlayer.getNickname()))
		{
			playerToReturn.setTurn(true);
		} else
		{
			playerToReturn.setTurn(false);
		}
		return playerToReturn;
	}
	
	//return the board state of a specific game
	public static Board getGameBoard(int gameID)
	{
		 List<Database.Piece> dbPieces = new ArrayList<>();
		 dbPieces = DatabaseManagerImpl.getGame(gameID).getBoard().getPieces();
		 
		 Board backendBoard = new Board();
		 
		 int pullFrom = 0;
		 for(int row = 0; row < 11; row++)
		 {
			 for(int col = 0; col < 11; col++)
			 {
				 // Pull in pieceOwner user info
				 User pieceOwner = new User(dbPieces.get(pullFrom).getUser().getNickname(), 
						 			   dbPieces.get(pullFrom).getUser().getPassword(), 
						 			   dbPieces.get(pullFrom).getUser().getEmail());
				 // Pull in pieceType info
				 String pt = dbPieces.get(pullFrom).getPieceType().getPieceType();
				 
				 // Make piece
				 Piece p;
				 p = new Backend.Piece(PieceType.valueOf(pt), pieceOwner);
				 
				 // Put piece on board
				 backendBoard.addPieceToBoard(row, col, p.getType(), p.getPlayer());
				 pullFrom++;
			 }
		 }
		return backendBoard;
	}
	
	//get a user from database and translate it to backend
	public static User getUser(String userID) 
	{
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(userID);
		User tempuser = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		tempuser.setLosses(temp.getLoses());
		tempuser.setWins(temp.getWins());
		return tempuser;
	}
	
}
