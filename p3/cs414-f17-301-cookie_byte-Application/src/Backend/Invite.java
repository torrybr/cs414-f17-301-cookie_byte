package Backend;

public class Invite {

	User FromUser;
	User ToUser;
	int GameID;
	InvitationStatus Acceptance;
	
	
	public Invite (User ToUser, User FromUser, int GameID){
		this.ToUser = ToUser;
		this.FromUser = FromUser;
		this.GameID = GameID;
		Acceptance= InvitationStatus.PENDING;	
	}
	
	public InvitationStatus getAcceptance() {
		return Acceptance;
	}

	public void setAcceptance(InvitationStatus acceptance) {
		Acceptance = acceptance;
	}

	public String toString() {
		return "Invite [FromUser=" + FromUser + ", ToUser=" + ToUser + ", GameID=" + GameID + "]";
	}
	
	
}
