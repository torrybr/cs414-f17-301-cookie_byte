package Drivers;

import Backend.DatabaseTranslator;
import Backend.InvitationStatus;
import Backend.Invite;
import Backend.User;
import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

import java.lang.reflect.Array;
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
		activeInvites.clear();
		for(int i = 0;i<inviteIDs.size();i++){
			if(inviteIDs.get(i).getStatus() == InvitationStatus.PENDING){
				activeInvites.add(inviteIDs.get(i));
			}
		}
	}
	public void findActiveGames(){
		activeGameIDs.clear();
		for(int i = 0;i<gameIDs.size();i++){
			String stat = DatabaseManagerImpl.getGame(gameIDs.get(i)).getGameStatus().getGameStatus();
			if(stat.equals("Active")){
				activeGameIDs.add(gameIDs.get(i));
			}
		}
	}
	public List<Integer> getGameIDs() {
		return gameIDs;
	}
	public List<Invite> getActiveInvites(){
		return activeInvites;
	}
	public List<Integer> getActiveGames(){
		return activeGameIDs;
	}
	public void setGameIDs(List<Integer> gameIDs) {
		this.gameIDs = gameIDs;
	}
	public List<Integer> activeGameIDs = new ArrayList<Integer>();
	public List<Integer> gameIDs = new ArrayList<Integer>();//only using one gameID rn for testing, will be a list
	
	public ClientDriver(String username, String password, String email) {
		//Db create user profile
		DatabaseManagerImpl.createNewUser(username,password,email);
	}
	
	public ClientDriver(User profile, List<String> inviteIDs, List<Integer> gameIDs) {
		this.profile = profile;
		//this.inviteIDs = inviteIDs; 
		this.gameIDs = gameIDs;
	}
	public ClientDriver(String username) {
		//get user info from DB
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(username);
		profile = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		//List<Integer> tempo = temp.getCurrentGames();
		// = (List<Integer>) tempo.get(0);
		gameIDs = temp.getCurrentGames();
		inviteIDs = DatabaseTranslator.getDbInvites(profile);
		findActiveInvites();
		findActiveGames();
		
	}
	public ClientDriver(){
		
	}
	public boolean checkAuth(String username, String pass){
		try{
			UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(username);
			if(temp.getPassword().equals(pass))
				return true;
			else 
				return false;
		}
		catch(NullPointerException x){
			return false;
		}
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


	public boolean searchPlayers(String searchIn){
		/*List<UsersJavaObject> check = DatabaseManagerImpl.searchUser(searchIn);
		ArrayList<User> returnable = new ArrayList<User>();
		for(int i = 0; i < check.size(); i++)
		{
			returnable.add(DatabaseTranslator.getUser(check.get(i).getNickname()));
		}

		return returnable;*/
		boolean b = true;
		try{
			DatabaseManagerImpl.getUserByNickname(searchIn).getNickname();
		}
		catch(NullPointerException x){
			b = false;
		}
		
		return b;

	}
	public void deleteUser(){
		DatabaseManagerImpl.removeUser(this.profile.getUserID());
	}
	public User getProfile(){
		return profile;
	}
	public static void main(String[] args) {
		
	}
}
