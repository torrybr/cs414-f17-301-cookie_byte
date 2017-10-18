package Backend;

public class Space{

	Piece Piece;
	
	public Space(){
		this.Piece = null;
	}
	
	public Piece getPiece() {
		return Piece;
	}

	public void setPiece(Piece piece) {
		Piece = piece;
	}
	
	public boolean isEmpty (){
		if (this.Piece == null)
			return true;
		else
			return false;
	}
}
