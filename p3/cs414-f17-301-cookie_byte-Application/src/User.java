import java.text.DecimalFormat;
import java.util.*;

public class User {

	String userID;
	String Nickname;
	String Email;
	String Password;
	double winPercentage;
	DecimalFormat df = new DecimalFormat("#.##");

	ArrayList<Invite> Invites;
	ArrayList<Game> PassGames;
	ArrayList<Game> CurrentGames;
	UniqueRandomNumbers rand;
	
// A Constructor for whenever a new user registers into our system and needs to be created
	public User (String userID, String Nickname, String Email,String Password){
		
		this.userID = userID;
		this.Nickname = Nickname;
		this.Email = Email;
		this.Password = Password;
		this.winPercentage = 0.0;
	}
	
	//Getters and Setters
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
	public double getWinPercentage(){
		return winPercentage;
	}
	
	//Equals method that compares one user to another based on UserID and Password
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	//Takes the response from the user and sets the invitation so the game initiates or is deleted.
	public void responseInvitation (Invite i, InvitationStatus response ){
		
		//If invitation is accepted
		if (response == InvitationStatus.ACCECPTED){
			// 1. Creates game based on ID attached to invite.
			Game newGame = new Game (i.GameID, i.FromUser, i.ToUser);
			// 2. Adds the game to currentGames arrayList
			CurrentGames.add(newGame);
			// 3. Removes the Invite form the User from invite arrayList
			Invites.remove(i);
		}
		if (response == InvitationStatus.DECLINED){
			//1. Removes the Pending Invite from the invite arrayList
			Invites.remove(i);
		}
	}
	
	//Based on the User that is given an invitation is made.
	public void sendInvitation (User u){
		
		//Creates an invite based on a user that is given and generates a random number to create a game from.
		Invite newInvite = new Invite (this, u, rand.numberGenerator());
		
		//Adds it to that users arraylist of invites.
		u.Invites.add(newInvite);
	}
	
	//shows all invitations that have been sent and not yet responded to or that are pending from this given user.
	public Object[] allInvitations(){
		
		return Invites.toArray();
	}
	
	//Returns an ArrayList of games where the the game status is PENNDING
	public Object[] getCurrentGames (){
		
		return CurrentGames.toArray();
		
	}
	
	//Returns an ArrayList of games where the the game status is Finished and the winner is decided. 
	public Object[] getPassGames (){
		
		return PassGames.toArray();
	}
	
	//Calculates passed on the passGames ArrayList a winning percentage thin is returned.
	public void winPercentage(){
		double wins=0.0; double loses =0.0;
				
		//Copies the users record
		Game [] record = (Game[]) this.getPassGames();
		
		//Splits up wins and loses by iterating the object array.
		for (Game g : record){
			//wins
			if(g.Winner.equals(this))
				wins++;
			//loses
			else
				loses++;
			
		this.winPercentage = Double.parseDouble(df.format(((wins/(wins+loses))*100)));
		}
	}
	
	//toString function that will display the user as [UserID:Nickname:Winning Percentage]
	public String toString() {
		return "User [userID=" + userID + ", Nickname=" + Nickname + ", winPercentage=" + winPercentage + "]";
	}
}

