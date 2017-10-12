import java.util.*;


public class User {

	String userID;
	String Nickname;
	String Email;
	String Password;
	ArrayList<Invite> Invites;
	ArrayList<Game> PassGames;
	ArrayList<Game> CurrentGames;
	
	public User (String userID, String Nickname, String Email,String Password){
		
		this.userID = userID;
		this.Nickname = Nickname;
		this.Email = Email;
		this.Password = Password;
		
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public void currentGames (){
		
	}
	public void passGames (){
		
	}
	
	public void winPercentage(){
		
	}
}

