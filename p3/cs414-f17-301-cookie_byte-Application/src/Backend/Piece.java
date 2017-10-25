package Backend;

public class Piece {

	PieceType type;
	User player;
	int x;
	int y;


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
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
