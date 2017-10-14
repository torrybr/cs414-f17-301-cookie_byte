
public class Spaces {

	//Will be used as a uniqueID per game
	int Location;
	Piece Piece;
	
	public Spaces (int Location){
		
		this.Location = Location;
		this.Piece = null;
	}
	
	public int getLocation() {
		return Location;
	}

	public void setLocation(int location) {
		Location = location;
	}
	
	public Piece getPiece() {
		return Piece;
	}

	public void setPiece(Piece piece) {
		Piece = piece;
	}
}
