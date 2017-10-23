package Backend;

public class Move {

	 User player;
	 Game game;
	 Piece piece;
	 int newLocation;
	 Board board;
	 
	 public Move (User player,Piece piece, Game game){
		 this.player = player;
		 this.piece= piece;
		 this.game = game;
		 this.board = game.getBoard();
	 }
	 
	public boolean isMoveValid(Piece piece, int rowFrom, int colFrom, int rowTo, int colTo){
			// Straight line check
			if(rowFrom != rowTo && colFrom != colTo){ 
				return false; }
			// In range of board check
			if(rowFrom > 10 || rowFrom < 0 || rowTo > 10 || rowTo < 0 || colFrom > 10 || colFrom < 0 || colTo > 10 || colTo < 0){
				return false; }
			//Is the piece moving to a corner & not a king check
			if(piece.getType() != PieceType.KING){
				if(rowTo == 0 && colTo == 0){
					return false;}
				else if(rowTo == 0 && colTo == 10){
					return false;}
				else if(rowTo == 10 && colTo == 0){
					return false;}
				else if(rowTo == 10 && colTo == 10){
					return false;}
			}
			
			//Is there a piece in the way check
			if(rowFrom == rowTo){
				for(int i = colFrom; i <= colTo; i++){
					if(!board.Spaces[rowFrom][i].isEmpty()){
						return false;
					}
				}
			}
			else{
				for(int i = rowFrom; i <= rowTo; i++){
					if(!board.Spaces[i][colFrom].isEmpty()){
						return false;
					}
				}
			}		
			return true;
		}
	
	public boolean capturePiece(int row, int col){
		//Make sure we're not checking out of bounds
		if(row < 0 || row > 10 || col < 0 || col > 10){
			return false;
		}
		//Check to see if we are on an edge and if next to a corner, check if needs to be removed
		if(row == 0 || row == 10){
			if(col != 1 && col!= 9){
				return false;
			}
			else if(row == 0){
				//Checking next to top right corner
				if(col == 1){
					if(!board.Spaces[0][2].isEmpty()){
						return true;
					}
				}
				//Checking next to top left corner
				if(col == 9){
					if(!board.Spaces[0][8].isEmpty()){
						return true;
					}
				}
			}
			else if(row == 10){
				//Checking next to bottom right corner
				if(col == 1){
					if(!board.Spaces[10][2].isEmpty()){
						return true;
					}
				}
				//Checking next to bottom left corner
				if(col == 9){
					if(!board.Spaces[10][8].isEmpty()){
						return true;
					}
				}
			}
		}
		else if(col == 0 || col == 10){
			if(row != 1 && row != 9){
				return false;
			}
			else if(col == 0){
				//Checking next to top right corner
				if(row == 1){
					if(!board.Spaces[2][0].isEmpty()){
						return true;
					}
				}
				//Checking next to top left corner
				if(row == 9){
					if(!board.Spaces[8][0].isEmpty()){
						return true;
					}
				}
			}
			else if(col == 10){
				//Checking next to bottom right corner
				if(row == 1){
					if(!board.Spaces[2][10].isEmpty()){
						return true;
					}
				}
				//Checking next to bottom left corner
				if(row == 9){
					if(!board.Spaces[8][10].isEmpty()){
						return true;
					}
				}
			}
		}
		else if(!board.Spaces[row-1][col].isEmpty() && !board.Spaces[row+1][col].isEmpty()){
			return true;
		}
		else if(!board.Spaces[row][col-1].isEmpty() && !board.Spaces[row][col + 1].isEmpty()){
			return true;
		}
		
		return false;
	}
	
	public void movePiece(Piece piece, int rowFrom, int colFrom, int rowTo, int colTo){
		if(isMoveValid(piece,rowFrom, colFrom, rowTo, colTo)){
			//Actually move piece
			board.Spaces[rowTo][colTo].setPiece(board.Spaces[rowFrom][colFrom].getPiece());
			board.Spaces[rowFrom][colFrom].setPiece(null);
			
			//Check win conditions here (this must be done first... I think
			game.attackWinConditions();
			game.kingWinConditions();

			
			//Check if we killed any enemies (capturePiece handles out of bounds checks)
			if(capturePiece(rowTo+1, colTo)){
				board.Spaces[rowTo+1][colTo].setPiece(null);
			}
			if(capturePiece(rowTo-1, colTo)){
				board.Spaces[rowTo-1][colTo].setPiece(null);
			}
			if(capturePiece(rowTo, colTo+1)){
				board.Spaces[rowTo][colTo+1].setPiece(null);
			}
			if(capturePiece(rowTo, colTo-1)){
				board.Spaces[rowTo][colTo-1].setPiece(null);
			}
		}
	}
	 
//	
//	 public boolean checkValid(User player, Piece piece, int newLocation) {	 
//		 //if checkTurn() is false 
//		 	//return false 
//		 
//		 //if checkPiece() is false
//		 	//return false 
//		 
//		 //return true
//	 }
//	 
//	 private boolean checkTurn(User player) {
//		 //if it is not users turn 
//		 	//return false 
//		 //else 
//		 	//return true
//	 }
//	 
//	 private boolean checkPiece(Piece piece, int newLocation) {
//		 //Step 1: get piece location
//		 
//		 //Step 2: compare the x and y coordinated of the old location and the new location 
//		 	//if both x and y changed 
//		 		//return false
//		 	//if new location is out of bounds 
//		 		//return false 
//		 	//if there is already a piece at new location
//		 		//return false 
//		 	//if x or y changed by more than 1 
//		 		//if change of x or y is 2 
//		 			//if change-1 is middle square 
//		 				//continue
//		 			//else
//		 				//return false 
//		 	//if new location is refugee square
//		 		//if piece is not king 
//		 			//return false 
//		 	
//		 
//		 //return true
//	 }
//	 
//	 public boolean updateStatus() {
//		 
//		 
//	 }
	
	 
}