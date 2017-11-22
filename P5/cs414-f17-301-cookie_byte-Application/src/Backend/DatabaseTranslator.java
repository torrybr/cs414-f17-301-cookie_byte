package Backend;

import java.util.ArrayList;
import java.util.List;

import Database.BoardJavaObject;
import Database.DatabaseManagerImpl;
import Database.UserFrom;
import Database.UserTo;
import Database.UsersJavaObject;

public abstract class DatabaseTranslator {

	public static List<Invite> getDbInvites(User u)
	{
		List<Invite> newList = new ArrayList<Invite>();
		
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(u.getUserID());
		List<Database.Invite> dbInvites = temp.getInvites();
		
		for(int i = 0; i < dbInvites.size(); i++) 
		{
			UserTo userto = dbInvites.get(i).getInvite().getUserTo();
			String to = userto.getUserID();
			
			UserFrom userfrom = dbInvites.get(i).getInvite().getUserFrom();
			String fromId = userfrom.getUserID();
			String fromEmail = userfrom.getEmail();
			String fromPassword = userfrom.getPassword();
			User tempFrom = new User(fromId, fromPassword, fromEmail);
			
			int tempGameID = dbInvites.get(i).getInvite().getGameID();
		
			Invite invite = new Invite(to, tempFrom, tempGameID, 12);
			String status = dbInvites.get(i).getInvite().getInvitationStatus().getInvitationStatus();
			invite.setStatus(InvitationStatus.valueOf(status));
			
			newList.add(invite);
		}
		return newList;
	}
	
	public static User getPlayerFromGame(int player, int gameID) 
	{	
		UsersJavaObject thePlayer;
		
		if(player == 1) 
		{
			thePlayer = DatabaseManagerImpl.getUserByNickname(DatabaseManagerImpl.getGame(gameID).getPlayer1());
		}
		else
		{
			thePlayer = DatabaseManagerImpl.getUserByNickname(DatabaseManagerImpl.getGame(gameID).getPlayer1());	
		}
		
		return new User(thePlayer.getNickname(), thePlayer.getPassword(), thePlayer.getEmail());		
		
	}
	
}
