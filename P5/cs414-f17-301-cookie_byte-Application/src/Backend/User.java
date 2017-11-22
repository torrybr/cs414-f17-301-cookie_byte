package Backend;

import java.util.List;

public class User {
	
	String userID; //unique nickname (userID)  
	String password; //password  
	String email; //unique email 
	boolean isOffence;
	boolean isTurn;
	private int wins = 0;
	private int losses = 0;
	
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

