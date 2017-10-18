public class Move {

	 User player;
	 Game game;
	 Piece piece;
	 int newLocation;
	 
	 public Move (User player,Piece piece, int newLocation){
		 this.Player = player;
		 this.Piece= piece;
		 this.newLocation = newLocation;
	 }
	 
	
	 public boolean checkValid(User player, Piece piece, int newLocation) {	 
		 //if checkTurn() is false 
		 	//return false 
		 
		 //if checkPiece() is false
		 	//return false 
		 
		 //return true
	 }
	 
	 private boolean checkTurn(User player) {
		 //if it is not users turn 
		 	//return false 
		 //else 
		 	//return true
	 }
	 
	 private boolean checkPiece(Piece piece, int newLocation) {
		 //Step 1: get piece location
		 
		 //Step 2: compare the x and y coordinated of the old location and the new location 
		 	//if both x and y changed 
		 		//return false
		 	//if new location is out of bounds 
		 		//return false 
		 	//if there is already a piece at new location
		 		//return false 
		 	//if x or y changed by more than 1 
		 		//if change of x or y is 2 
		 			//if change-1 is middle square 
		 				//continue
		 			//else
		 				//return false 
		 	//if new location is refugee square
		 		//if piece is not king 
		 			//return false 
		 	
		 
		 //return true
	 }
	 
	 public boolean updateStatus() {
		 
		 
	 }
	
	 
}