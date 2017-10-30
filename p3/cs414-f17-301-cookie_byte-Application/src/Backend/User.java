package Backend;
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
	
	
	public User (String userID, String password, String email)
	{
		this.userID = userID;
		this.password = password; 
		this.email = email; 
		
	}
	
	//make sure that userID  
	//make sure that email  
	
	//get invites: getInvites() 
	//add to invites: addInvite() 
	//remove from invites: removeInvite() 
	
	//get pastGames: getPastGames()  
	//add to pastGames: addPastGame()
	//get currentGames: getCurrentGames()
	//add to currentGames: addCurrentGame()
	//remove from current games: removeCurrentGames()
	
	//add to loss count: addLoss()
	//get loss count: getLosses() 
	//add to win count: addWin()
	//get win count getWins()
	
}

