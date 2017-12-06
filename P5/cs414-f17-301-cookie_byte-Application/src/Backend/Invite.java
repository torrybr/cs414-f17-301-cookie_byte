package Backend;

import Database.DatabaseManagerImpl;

public class Invite {

	User userTo;
	User userFrom;
	int gameID;
	InvitationStatus status;
	boolean isTournament = false;
	Tournament tournament;
	
	//Used for database to backend translation 
	public Invite (User to, User from, int gmeID){
		this.userTo = to;
		this.userFrom = from;
		this.gameID = gmeID;
		this.status = InvitationStatus.PENDING;
	}
	
	//used to create a new invite and add to database
	public Invite(User to, User from){
		this.userTo = to;
		this.userFrom = from;
		this.status = InvitationStatus.PENDING;
		this.gameID = DatabaseManagerImpl.createGameID();
		DatabaseManagerImpl.addInvite(this);
	}
	
	//Invitation constructor for Tournament
	public Invite(User to, User from, String status, Tournament T){
		userTo = to;
		userFrom = from;
		//this.gameID = gameID;
		if(status.equals("PENDING"))
			this.status = InvitationStatus.PENDING;
		if(status.equals("ACCEPTED"))
			this.status = InvitationStatus.PENDING;
		if(status.equals("DECLINED"))
			this.status = InvitationStatus.PENDING;
		userTo.addInvite(this);
		tournament = T;
	}
	
	public void acceptInvite()
	{
		// Set invite to accepted
		this.status = InvitationStatus.ACCEPTED;
		DatabaseManagerImpl.setInviteStatus(userFrom.userID, this);
		//Actually creates the game with the two users
		
		if(!isTournament){
			new GameController(gameID, userTo, userFrom);
		}
		else
		{
			this.tournament.checkTournamentStatus();
		}
		
		userTo.removeInvite(this); //do a database remove
		Database.DatabaseManagerImpl.removeInvite(userTo.userID, this);
		//DBDriver.setInviteStatus(nickname, theInvite);
		/**
		 * WHen accepted save game to users table
		 */
		
		// checks the status of a pending Tournament
		
	}
	
	public void declineInvite()
	{
		//Set invite to declined
		this.status = InvitationStatus.DECLINED;
		DatabaseManagerImpl.setInviteStatus(userFrom.userID, this);
		//if it is a tournament invite check the status of the tournament
		if (isTournament)
		{
			this.tournament.checkTournamentStatus();
		}
		// Remove invite from receiving user
		userTo.removeInvite(this);
		Database.DatabaseManagerImpl.removeInvite(userTo.userID, this);
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

	public boolean isTournament() {
		return isTournament;
	}


	public void setTournament(boolean isTournament) {
		this.isTournament = isTournament;
	}


	public String toString() 
	{
		 return "Invite [FromUser=" + userFrom + ", ToUser=" + userTo + ", GameID=" + gameID + ", Status=" + status+ ", IsTorunament="+ isTournament +"]";
	}
	
}
