package Drivers;

import java.util.List;

import Backend.User;

public class ClientDriver {
	public User profile;
	public List<Integer> inviteIDs;//only using one inviteID rn for testing, will be a list
	public List<Integer> gameIDs;//only using one gameID rn for testing, will be a list
	
	public ClientDriver(String username, String password, String email) {
		//Db create user profile
	}
	
	public ClientDriver(User profile, List<Integer> inviteIDs, List<Integer> gameIDs) {
		this.profile = profile;
		this.inviteIDs = inviteIDs; 
		this.gameIDs = gameIDs;
	}
	public ClientDriver(String username) {
		//get user info from DB
		profile = new User("", username, "", "");
	}
	
	public String[] viewProfile() {
		String[] returnable = new String[6];
		returnable[0] = profile.getEmail();
		returnable[1] = profile.getNickname();
		returnable[2] = "Your wins here.";
		returnable[3] = "Your loses here.";
		returnable[4] = Double.toString(profile.getWinPercentage());
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
	
	public static void main(String[] args) {
		
	}
}
