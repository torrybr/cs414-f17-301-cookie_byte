
public class Piece {

	int PieceID;
	String type;
	User Player;
	
	public Piece (int PieceID){
		
		this.PieceID =PieceID;
		this.type ="Rook";
	}
	
	public int getPieceID() {
		return PieceID;
	}

	public void setPieceID(int pieceID) {
		PieceID = pieceID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getPlayer() {
		return Player;
	}

	public void setPlayer(User player) {
		Player = player;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (PieceID != other.PieceID)
			return false;
		return true;
	}

	
	
	
}
