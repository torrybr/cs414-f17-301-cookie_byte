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
		System.out.println(u.getUserID());
		DatabaseManagerImpl.getmyUserJson("D");
		List<Database.Invite> dbInvites = temp.getInvites();
		System.out.println(dbInvites.size());
		for(int i = 0; i < dbInvites.size(); i++) 
		{	
			String userFromId = dbInvites.get(i).getInvite().getUserFrom();
			System.out.println(userFromId);
			String fromEmail =  DatabaseManagerImpl.getUserByNickname(userFromId).getEmail();
			String fromPassword = DatabaseManagerImpl.getUserByNickname(userFromId).getPassword();
			User tempFrom = new User(userFromId, fromPassword, fromEmail);
			
			int tempGameID = dbInvites.get(i).getInvite().getGameID();
		
			Invite invite = new Invite(u.userID, tempFrom, tempGameID, 12);
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
			playerToReturn = new User(thePlayer.getNickname(), thePlayer.getPassword(), thePlayer.getEmail());
		}
		else
		{
			thePlayer = DatabaseManagerImpl.getUserByNickname(DatabaseManagerImpl.getGame(gameID).getPlayer2());
			playerToReturn = new User(thePlayer.getNickname(), thePlayer.getPassword(), thePlayer.getEmail());
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
		if(DatabaseManagerImpl.getGame(gameID).getCurrentTurn().equals(thePlayer.getNickname()))
		{
			playerToReturn.setTurn(true);
		} else
		{
			playerToReturn.setTurn(false);
		}
		
		return playerToReturn;
	}
	
}
