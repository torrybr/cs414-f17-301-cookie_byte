package Backend;

public class Space{

	Piece Piece;
	
	public Space(){
		this.Piece = null;
	}
	
	public Piece getPiece() {
		return this.Piece;
	}

	public void setPiece(Piece piece) {
		this.Piece = piece;
	}
	
	public boolean isEmpty (){
		if (this.Piece == null)
			return true;
		else
			return false;
	}
}
