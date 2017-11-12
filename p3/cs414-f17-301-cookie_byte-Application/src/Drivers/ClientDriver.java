package Drivers;

import Backend.User;
import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

import java.util.List;

public class ClientDriver {
	public User profile;
	public List<String> inviteIDs;//only using one inviteID rn for testing, will be a list

	public List<String> getInviteIDs() {
		return inviteIDs;
	}

	public void setInviteIDs(List<String> inviteIDs) {
		this.inviteIDs = inviteIDs;
	}

	public List<String> getGameIDs() {
		return gameIDs;
	}

	public void setGameIDs(List<String> gameIDs) {
		this.gameIDs = gameIDs;
	}

	public List<String> gameIDs;//only using one gameID rn for testing, will be a list
	public DatabaseManagerImpl DBDriver = new DatabaseManagerImpl();
	
	public ClientDriver(String username, String password, String email) {
		//Db create user profile
		//DBDriver.createNewUser(username,password,email);
	}
	
	public ClientDriver(User profile, List<String> inviteIDs, List<String> gameIDs) {
		this.profile = profile;
		this.inviteIDs = inviteIDs; 
		this.gameIDs = gameIDs;
	}
	public ClientDriver(String username) {
		//get user info from DB
		UsersJavaObject temp = DBDriver.getUserByNickname(username);
		profile = new User(temp.getUserID(),temp.getPassword(),temp.getEmail());
		gameIDs = temp.getCurrentGames();
		inviteIDs = temp.getInvites();
	}
	
	public String[] viewProfile() {
		String[] returnable = new String[6];
		returnable[0] = profile.getEmail();
		//returnable[1] = profile.getNickname();
		returnable[2] = "Your wins here.";
		returnable[3] = "Your loses here.";
		//returnable[4] = Double.toString(profile.getWinPercentage());
		returnable[5] = "Your games here.";
		return returnable;
	}
	
	//public ArrayList<Invite> seeInvite(){
		//get invite from db by inviteID
	//	return invites;
	//}
	
	//method to get user from db
	//public User getUser(String username){
		//get user from db by username
	//}
	public User getProfile(){
		return profile;
	}
	public static void main(String[] args) {
		
	}
}
