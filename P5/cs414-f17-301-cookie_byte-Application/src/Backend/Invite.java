package Backend;

import Database.DatabaseManagerImpl;
import Database.UsersJavaObject;

public class Invite {

	User userTo;
	User userFrom;
	int gameID;
	InvitationStatus status;
	
	public Invite (String to, User from, int gmeID){
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(to);
		userTo = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		this.userFrom = from;
		this.gameID = gmeID;
		// Set invite to pending
		status = InvitationStatus.PENDING;	
		// Add invite to userTo's list of invites
		DatabaseManagerImpl.addInvite(to, this);
	}
	
	public Invite (String to, User from, int gmeID, int a){
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(to);
		userTo = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		this.userFrom = from;
		this.gameID = gmeID;
		// Set invite to pending
		status = InvitationStatus.PENDING;	
		// Add invite to userTo's list of invites
	}
	
	
	public Invite(String to, User from){
		UsersJavaObject temp = DatabaseManagerImpl.getUserByNickname(to);
		userTo = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
		this.userFrom = from;
		status = InvitationStatus.PENDING;
		DatabaseManagerImpl.addInvite(to, this);
	}
	
	public Invite(User to,User from, int game, String status){
		userTo = to;
		userFrom = from;
		gameID = game;
		if(status.equals("PENDING"))
			this.status = InvitationStatus.PENDING;
		if(status.equals("ACCEPTED"))
			this.status = InvitationStatus.PENDING;
		if(status.equals("DECLINED"))
			this.status = InvitationStatus.PENDING;
	}
	public void acceptInvite()
	{
		// Set invite to accepted
		status = InvitationStatus.ACCECPTED;
		//Actually creates the game with the two users
		GameController gme = new GameController(gameID, userTo, userFrom);
		// Sets game to active
		gme.setStatus(GameStatus.ACTIVE);
		// Add game to both users THIS IS NOW DONE IN DB
		// userTo.addCurrentGame(gme);
		// userFrom.addCurrentGame(gme);
		// Remove invite from receiving user
		//DBDriver.setInviteStatus(nickname, theInvite);
		/**
		 * WHen accepted save game to users table
		 */
	}
	
	public void declineInvite()
	{
		// Set invite to declined
		status = InvitationStatus.DECLINED;
		// Remove invite from receiving user
		/**
		 * remove the user invite from <invites></invites>
		 */
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invite other = (Invite) obj;
		if (gameID != other.gameID)
			return false;
		return true;
	}
	
	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public void setStatus(InvitationStatus status) {
		this.status = status;
	}

	public String toString() 
	{
		 return "Invite [FromUser=" + userFrom + ", ToUser=" + userTo + ", GameID=" + gameID + "]";
	}
	
}
