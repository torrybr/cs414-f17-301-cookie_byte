package Backend;

import java.util.ArrayList;
import java.util.List;

public class Board {

    // A board has pieces... well, it has a 2D array of pieces. A board is no more than a container for pieces.
    // The board does not care about players or turns; only the pieces it contains. The board holds all power
    // of piece movement and removal. It does not initialize the board, but it is used to add the pieces during
    // board setup.

    // This class should only be used by other classes.

    Piece[][] pieces = new Piece[11][11];
    User none = new User("nullUser", "nullUser", "null@null.null");
    // Array list var
    public List<Piece> pieces1DLayout;
    int gameID;

    public Board() {

        // Initialize ArrayList here
        Piece empty = new Piece(PieceType.NONE, none);
        pieces1DLayout = new ArrayList<>(121);
        for (int i = 0; i < 121; i++) {
            pieces1DLayout.add(empty);
        }
    }
    public int getGameID(){
        return gameID;
    }
        // Method to get a piece; returns Piece

    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }
    
    public Piece[][] getPieces() {
        return pieces;
    }
    // Method to get type of piece; returns PieceType
    public PieceType getPieceType(int row, int col) {
        return pieces[row][col].getType();
    }


    // Method to get player who owns piece
    public User getPieceOwner(int row, int col) {
        return pieces[row][col].getPlayer();
    }

    // Add piece to board
    public void addPieceToBoard(int row, int col, PieceType typeOfPiece, User pieceOwner) {
        // Create the piece
        Piece p = new Piece(typeOfPiece, pieceOwner);

        // Put the pieces in a 1D array for storing in Database, based on thier row/column positions
        pieces1DLayout.set((row * 11) + col, p);


        // Put the piece where it goes
        pieces[row][col] = p;
    }

    // Remove piece from board
    public void removePiece(int row, int col) {
        // Take the piece off the board
        pieces[row][col].setType(PieceType.NONE);
        pieces[row][col].setPlayer(none);
        
        Piece nope = new Piece(PieceType.NONE, none);
        pieces1DLayout.set((row * 11) + col, nope);
    }

    // Method to move piece from one space to another; does not return anything
    public void movePiece(int rowFrom, int colFrom, int rowTo, int colTo) {
        // Store info about piece we are about to move
        User pieceOwner = getPieceOwner(rowFrom, colFrom);
        PieceType typeOfPiece = getPieceType(rowFrom, colFrom);

        // Take the piece off the board
        pieces[rowFrom][colFrom].setType(PieceType.NONE);
        pieces[rowFrom][colFrom].setPlayer(none);


        // Put the piece back where it goes
        pieces[rowTo][colTo].setPlayer(pieceOwner);
        pieces[rowTo][colTo].setType(typeOfPiece);

        
        // Put the pieces in a 1D array for storing in Database, based on thier row/column positions & remove moved piece
        Piece p = new Piece(typeOfPiece, pieceOwner);
        Piece nope = new Piece(PieceType.NONE, none);
        pieces1DLayout.set((rowTo * 11) + colTo, p);
        pieces1DLayout.set((rowFrom * 11) + colFrom, nope);

    }


}
