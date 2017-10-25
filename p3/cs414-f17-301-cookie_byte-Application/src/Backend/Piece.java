package Backend;

public class Piece {

	PieceType type;
	User player;
	
	public Piece (PieceType type, User user){
		
		this.player = user;
		this.type = type;
	}

	public PieceType getType() {
		return this.type;
	}
	
	public void setType(PieceType type){
		this.type = type;
	}
	
	public User getPlayer() {
		return this.player;
	}
		
	public void setPlayer(User player){
		this.player = player;
	}
	
}
