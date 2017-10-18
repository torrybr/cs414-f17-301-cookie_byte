package Backend;
public class Board {

	Space[][] Spaces = new Space[11][11];
	Piece[] Pieces= new Piece [36];
	
	public Board (User Offence, User Defence){
			
		//Set the beginning conditions of the Board
		
		//Sets offense Pieces on the board 1st
		
		//Upper offensive Pieces set
		Pieces[0].setPlayer(Offence);
		Spaces[0][3].setPiece(Pieces[0]);
		
		Pieces[1].setPlayer(Offence);
		Spaces[0][4].setPiece(Pieces[1]);
		
		Pieces[2].setPlayer(Offence);
		Spaces[0][5].setPiece(Pieces[2]);
		
		Pieces[3].setPlayer(Offence);
		Spaces[0][6].setPiece(Pieces[3]);
		
		Pieces[4].setPlayer(Offence);
		Spaces[0][7].setPiece(Pieces[4]);
		
		Pieces[5].setPlayer(Offence);
		Spaces[1][5].setPiece(Pieces[5]);
		
		//Left-Side Offensive Pieces set
		Pieces[6].setPlayer(Offence);
		Spaces[3][0].setPiece(Pieces[6]);
		
		Pieces[7].setPlayer(Offence);
		Spaces[4][0].setPiece(Pieces[7]);
		
		Pieces[8].setPlayer(Offence);
		Spaces[5][0].setPiece(Pieces[8]);
		
		Pieces[9].setPlayer(Offence);
		Spaces[6][0].setPiece(Pieces[9]);
		
		Pieces[10].setPlayer(Offence);
		Spaces[7][0].setPiece(Pieces[10]);
		
		Pieces[11].setPlayer(Offence);
		Spaces[5][1].setPiece(Pieces[11]);
		
		//Right-side Offensive Pieces set
		Pieces[12].setPlayer(Offence);
		Spaces[3][10].setPiece(Pieces[12]);
		
		Pieces[13].setPlayer(Offence);
		Spaces[4][10].setPiece(Pieces[13]);
		
		Pieces[14].setPlayer(Offence);
		Spaces[5][10].setPiece(Pieces[14]);
		
		Pieces[15].setPlayer(Offence);
		Spaces[6][10].setPiece(Pieces[15]);
		
		Pieces[16].setPlayer(Offence);
		Spaces[7][10].setPiece(Pieces[16]);
		
		Pieces[17].setPlayer(Offence);
		Spaces[5][9].setPiece(Pieces[17]);
		
		//Lower Offensive Pieces Set
		Pieces[18].setPlayer(Offence);
		Spaces[10][3].setPiece(Pieces[18]);
		
		Pieces[19].setPlayer(Offence);
		Spaces[10][4].setPiece(Pieces[19]);
		
		Pieces[20].setPlayer(Offence);
		Spaces[10][5].setPiece(Pieces[20]);
		
		Pieces[21].setPlayer(Offence);
		Spaces[10][6].setPiece(Pieces[21]);
		
		Pieces[22].setPlayer(Offence);
		Spaces[10][7].setPiece(Pieces[22]);
		
		Pieces[23].setPlayer(Offence);
		Spaces[9][5].setPiece(Pieces[23]);
		
		//Starts setting the Defensive Pieces
		
		Pieces[24].setPlayer(Defence);
		Spaces[3][5].setPiece(Pieces[24]);
		
		Pieces[25].setPlayer(Defence);
		Spaces[4][4].setPiece(Pieces[25]);
		
		Pieces[26].setPlayer(Defence);
		Spaces[4][5].setPiece(Pieces[26]);
		
		Pieces[27].setPlayer(Defence);
		Spaces[4][6].setPiece(Pieces[27]);
		
		Pieces[28].setPlayer(Defence);
		Spaces[5][3].setPiece(Pieces[28]);
		
		Pieces[29].setPlayer(Defence);
		Spaces[5][4].setPiece(Pieces[29]);
		
		//King Piece
		Pieces[30].setPlayer(Defence);
		Pieces[30].setType(PieceType.KING);
		Spaces[5][5].setPiece(Pieces[30]);
		
		Pieces[31].setPlayer(Defence);
		Spaces[5][6].setPiece(Pieces[31]);
		
		Pieces[32].setPlayer(Defence);
		Spaces[5][7].setPiece(Pieces[32]);
		
		Pieces[33].setPlayer(Defence);
		Spaces[6][4].setPiece(Pieces[33]);
		
		Pieces[34].setPlayer(Defence);
		Spaces[6][5].setPiece(Pieces[34]);
		
		Pieces[35].setPlayer(Defence);
		Spaces[6][6].setPiece(Pieces[35]);
		
		Pieces[36].setPlayer(Defence);
		Spaces[7][5].setPiece(Pieces[36]);
	}
	
	public Space getSpace(int row, int col) {
		return Spaces[row][col];
	}
	
	//Remove a piece from board
	public boolean removePiece(int row, int col){
		Spaces[row][col].setPiece(null);
		return true;
		 
	}	
	public Piece getPiece(int row,int col){
		return Spaces[row][col].getPiece();
	}
	
}
