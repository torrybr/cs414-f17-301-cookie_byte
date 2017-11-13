package Backend;

public class Invite {

	User userTo;
	User userFrom;
	int gameID;
	InvitationStatus status;
	
	
	public Invite (User to, User from, int gmeID){
		this.userTo = to;
		this.userFrom = from;
		this.gameID = gmeID;
		// Set invite to pending
		status = InvitationStatus.PENDING;	
		// Add invite to userTo's list of invites
		userTo.addInvite(this);
		
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
		userTo.removeInvite(this);
		/**
		 * WHen accepted save game to users table
		 */
	}
	
	public void declineInvite()
	{
		// Set invite to declined
		status = InvitationStatus.DECLINED;
		// Remove invite from receiving user
		userTo.removeInvite(this);
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
	
	public String toString() 
	{
		 return "Invite [FromUser=" + userFrom + ", ToUser=" + userTo + ", GameID=" + gameID + "]";
	}
	
}
