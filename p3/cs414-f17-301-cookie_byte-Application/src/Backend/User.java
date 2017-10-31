package Backend;

import java.util.ArrayList;
import java.util.List;

public class User {
	
// A Constructor for whenever a new user registers into our system and needs to be created
	//What we want user to do: have a USER ID 
	//List of invites 
	//List of pastGames  
	//List of currentGames 
	//int for win and for loss or tie 
	String userID; //unique nickname (userID)  
	String password; //password  
	String email; //unique email 
	private List<Invite> invites = new ArrayList<Invite>();
	private List<GameController> pastGames = new ArrayList<GameController>();
	private List<GameController> currentGames = new ArrayList<GameController>();
	private int wins = 0;
	private int losses = 0;
	
	
	
	public User (String userID, String password, String email)
	{
		this.userID = userID;
		this.password = password; 
		this.email = email; 
		
	}
	
	//get invites: getInvites() 
	public List<Invite> getInvites()
	{
		return invites;
	}
	//add to invites: addInvite() 
	public void addInvite(Invite i)
	{
		invites.add(i);
	}
	//remove from invites: removeInvite() 
	public void removeInvite(Invite i)
	{
		invites.remove(i);
	}
	
	//get pastGames: getPastGames()
	public List<GameController> getPastGames()
	{
		return pastGames;
	}
	//add to pastGames: addPastGame()
	public void addPastGame(GameController g)
	{
		pastGames.add(g);
	}
	//get currentGames: getCurrentGames()
	public List<GameController> getCurrentGames()
	{
		return currentGames;
	}
	//add to currentGames: addCurrentGame()
	public void addCurrentGame(GameController g)
	{
		currentGames.add(g);
	}
	//remove from current games: removeCurrentGame()
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

