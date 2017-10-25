package Backend;

public class Invite {

	User FromUser;
	User ToUser;
	String GameID;
	InvitationStatus Acceptance;
	
	
	public Invite (User ToUser, User FromUser, String GameID){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.parseInt(GameID);
		return result;
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
		if (GameID != other.GameID)
			return false;
		return true;
	}

	public String toString() {
		return "Invite [FromUser=" + FromUser + ", ToUser=" + ToUser + ", GameID=" + GameID + "]";
	}
	
	
}
