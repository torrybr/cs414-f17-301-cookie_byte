
public class Piece {

	PieceType type;
	User player;
	
	public Piece (PieceType type, User user){
		
		this.player = user;
		this.type = PieceType.KING;
	}

	public PieceType getType() {
		return type;
	}
	
	public void setType(PieceType tpe){
		type = tpe;
	}
	
	public User getPlayer() {
		return player;
	}
		
	public void setPlayer(User play){
		player = play;
	}
	
}
