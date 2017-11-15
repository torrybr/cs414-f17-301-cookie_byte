package Drivers;

import Backend.InvitationStatus;
import Backend.Invite;
import Backend.User;
import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

import java.util.ArrayList;
import java.util.List;

public class ClientDriver {
	public User profile;
	public List<Invite> inviteIDs;//only using one inviteID rn for testing, will be a list
	public List<Invite> activeInvites = new ArrayList<Invite>();
	public List<Invite> getInviteIDs() {
		return inviteIDs;
	}

	public void setInviteIDs(List<Invite> inviteIDs) {
		this.inviteIDs = inviteIDs;
	}
	public void findActiveInvites(){
		for(int i = 0;i<inviteIDs.size();i++){
			if(inviteIDs.get(i).getStatus() == InvitationStatus.PENDING){
				activeInvites.add(inviteIDs.get(i));
			}
		}
	}
	public List<Integer> getGameIDs() {
		return gameIDs;
	}
	public List<Invite> getActiveInvites(){
		return activeInvites;
	}
	public void setGameIDs(List<Integer> gameIDs) {
		this.gameIDs = gameIDs;
	}

	public List<Integer> gameIDs;//only using one gameID rn for testing, will be a list
	public DatabaseManagerImpl DBDriver = new DatabaseManagerImpl();
	
	public ClientDriver(String username, String password, String email) {
		//Db create user profile
		DBDriver.createNewUser(username,password,email);
	}
	
	public ClientDriver(User profile, List<String> inviteIDs, List<Integer> gameIDs) {
		this.profile = profile;
		//this.inviteIDs = inviteIDs; 
		this.gameIDs = gameIDs;
	}
	public ClientDriver(String username) {
		//get user info from DB
		UsersJavaObject temp = DBDriver.getUserByNickname(username);
		profile = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		gameIDs = temp.getCurrentGames();
		//inviteIDs = temp.getInvites();
		//getActiveInvites();
		
	}
	public ClientDriver(){
		
	}
	public boolean checkAuth(String username, String pass){
		UsersJavaObject temp = DBDriver.getUserByNickname(username);
		if(temp.getPassword().equals(pass))
			return true;
		else 
			return false;
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
