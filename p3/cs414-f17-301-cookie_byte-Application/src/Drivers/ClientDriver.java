package Drivers;

import Backend.User;

public class ClientDriver {
	public User profile;
	public int inviteID;//only using one inviteID rn for testing, will be a list
	public int gameID;//only using one gameID rn for testing, will be a list
	
	public ClientDriver(User profile, int inviteID, int gameID) {
		this.profile = profile;
		this.inviteID = inviteID; 
		this.gameID = gameID;
	}
	public ClientDriver(String username) {
		//
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
