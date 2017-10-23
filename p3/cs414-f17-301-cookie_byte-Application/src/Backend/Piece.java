package Backend;

public class Piece {

	PieceType type;
	User player;
	
	public Piece (PieceType type, User user){
		
		this.player = user;
		this.type = PieceType.ROOK;
	}

	public PieceType getType() {
		return type;
	}
	
	public void setType(PieceType type){
		this.type = type;
	}
	
	public User getPlayer() {
		return player;
	}
		
	public void setPlayer(User play){
		this.player = play;
	}
	
}
