package Backend;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseManagerImpl;

public class User {
	
	String userID; //unique nickname (userID)  
	String password; //password  
	String email; //unique email 
	boolean isOffence;
	boolean isTurn;
	private int wins = 0;
	private int losses = 0;
	private List<Invite> invites = new ArrayList<Invite>(); 
	private List<GameController> pastGames = new ArrayList<GameController>(); 
	private List<GameController> currentGames = new ArrayList<GameController>(); 
	
	public User (String userID, String password, String email)
	{
		this.userID = userID;
		this.password = password; 
		this.email = email; 
		
	}
	//get user information
	public String getUserID() 
	{
		return userID;
	}
	public String getPassword()
	{
		return password;
	}
	public String getEmail()
	{
		return email;
	}
	
	//set offence and turn
	public void setOffence(boolean isOffence) 
	{
		this.isOffence = isOffence;
	}
	public void setTurn(boolean isTurn) 
	{
		this.isTurn = isTurn;
	}
	
	//get invites: getInvites() 
	public List<Invite> getInvites()
	{
		return DatabaseTranslator.getDbInvites(this);
	}

	//add to invites: addInvite(Invite i) 
	public void addInvite(Invite i)
	{
		invites.add(i);
	}
	//remove from invites: removeInvite(Invite i) 
	public void removeInvite(Invite i)
	{
		invites.remove(i);
		//DatabaseManagerImpl.removeInvite(this.userID, i);
	}
	
	//get pastGames: getPastGames()
	public List<GameController> getPastGames()
	{
		return pastGames;
	}
	//add to pastGames: addPastGame(GameController g)
	public void addPastGame(GameController g)
	{
		pastGames.add(g);
	}
	//get currentGames: getCurrentGames()
	public List<GameController> getCurrentGames()
	{
		return currentGames;
	}
	//add to currentGames: addCurrentGame(GameController g)
	public void addCurrentGame(GameController g)
	{
		currentGames.add(g);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}
	//remove from current games: removeCurrentGame(GameController g)
	public void removeCurrentGame(GameController g)
	{
		currentGames.remove(g);
	}
	
	//add to loss count: addLoss()
	public void addLoss()
	{
		losses++;
	}
	//get loss count: getLosses() 
	public int getLosses()
	{
		return losses;
	}
	//add to win count: addWin()
	public void addWin()
	{
		wins++;
	}
	//get win count getWins()
	public int getWins()
	{
		return wins;
	}
	
}

