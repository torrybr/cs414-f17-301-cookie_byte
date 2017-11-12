package UI;

import Backend.GameController;
import Backend.Piece;
import Backend.PieceType;
import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Game extends Application{
	
	public ClientDriver clientDriver;
	public GameController gameDriver;
	
	
	protected int piece1 = -1;
	protected int piece2 = -1;
	protected String user1 = "?";
	protected String user2 = "?";
	protected String move = "?";

	protected Text movetext;
	protected Stage main;
	Tile[][] holder;
	
	 public Game(ClientDriver client,GameController game){
	 	this.clientDriver = client;
	 	this.gameDriver = game;
	 	user1 = gameDriver.getPlayer1().getUserID();
	 	user2 = gameDriver.getPlayer2().getUserID();
	 	move = gameDriver.getCurrentTurn().getUserID();
	 }
	
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(650, 650);
		boolean gray = true;
	    
		holder = new Tile[11][11];
		for(int i = 0;i<11;i++) {
			for(int j = 0;j<11;j++) {
				holder[i][j] = new Tile(String.valueOf(""));
				if(gray) {
					gray = false;
					holder[i][j].setTranslateX(50 + (50 * i));
					holder[i][j].setTranslateY(50 + (50 * j));
					holder[i][j].setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
					holder[i][j].setOrigBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
					holder[i][j].setLocationButton(i, j);
					root.getChildren().add(holder[i][j]);
				}
				else {
					gray = true;
					holder[i][j].setTranslateX(50 + (50 * i));
					holder[i][j].setTranslateY(50 + (50 * j));
					holder[i][j].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
					holder[i][j].setOrigBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
					holder[i][j].setLocationButton(i, j);
					root.getChildren().add(holder[i][j]);
				}
			}
		}
		
		holder[0][0].setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));;
		holder[0][10].setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));;
		holder[10][0].setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));;
		holder[10][10].setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));;
		
		setGame();
		
		return root;
	}
	
	private class Tile extends StackPane {
		
		protected BoardPiece tempB;
		protected Background origBackground;

		public Tile(String value) {
			Rectangle border = new Rectangle(50, 50);
			border.setFill(null);
			border.setStroke(Color.BLACK);
			
			tempB = new BoardPiece();
			tempB.setStyle("-fx-background-color: transparent;");
			tempB.setPrefWidth(50);
			tempB.setPrefHeight(50);
			tempB.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					 movePieces(tempB.getX(),tempB.getY());
				 }
			});
			
			Text text = new Text(value);
			text.setFont(Font.font(30));
			setAlignment(Pos.CENTER);
			this.getChildren().add(tempB);
			getChildren().addAll(border,text);
		}
		
		protected void setLocationButton(int x, int y) {
			tempB.setX(x);
			tempB.setY(y);
		}

		protected void setOrigBackground(Background background){
			origBackground = background;
		}

		
	}
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	   // hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonHome = new Button("Home");
	    buttonHome.setPrefSize(100, 20);

		Button buttonQuit = new Button("Quit this game");
		buttonHome.setPrefSize(100, 20);
	    
	    Text p1 = new Text("Player 1: " + user1);
		p1.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
		Text p2 = new Text("Player 2: " + user2);
		p2.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
		movetext = new Text("Move: " + move);
		movetext.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		
	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Home home = new Home(clientDriver);
            		try {
						home.start(main);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                
            }
        });

		buttonQuit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				//gameDriver.quit(clientDriver.profile);
				Home home = new Home(clientDriver);
				try {
					home.start(main);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	  
	    
	    final Pane spacer = new Pane();
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    spacer.setMinSize(10, 1);
	    hbox.getChildren().addAll(spacer,p1,p2,movetext,buttonQuit);
		hbox.getChildren().addAll(buttonHome);

	    return hbox;
	}

	public void setGame(){

		Image image = new Image(getClass().getResourceAsStream("red.png"));
		Image image1 = new Image(getClass().getResourceAsStream("blue.png"));
		Image image2 = new Image(getClass().getResourceAsStream("king.png"));

		ImageView iv = new ImageView(image);
		iv.setFitHeight(30);
		iv.setFitWidth(30);
		ImageView iv2 = new ImageView(image);
		iv2.setFitHeight(30);
		iv2.setFitWidth(30);
		ImageView iv3 = new ImageView(image);
		iv3.setFitHeight(30);
		iv3.setFitWidth(30);
		ImageView iv4 = new ImageView(image);
		iv4.setFitHeight(30);
		iv4.setFitWidth(30);
		ImageView iv5 = new ImageView(image);
		iv5.setFitHeight(30);
		iv5.setFitWidth(30);
		ImageView iv6 = new ImageView(image);
		iv6.setFitHeight(30);
		iv6.setFitWidth(30);
		ImageView iv7 = new ImageView(image);
		iv7.setFitHeight(30);
		iv7.setFitWidth(30);
		ImageView iv8 = new ImageView(image);
		iv8.setFitHeight(30);
		iv8.setFitWidth(30);
		ImageView iv9 = new ImageView(image);
		iv9.setFitHeight(30);
		iv9.setFitWidth(30);
		ImageView iv10 = new ImageView(image);
		iv10.setFitHeight(30);
		iv10.setFitWidth(30);
		ImageView iv11 = new ImageView(image);
		iv11.setFitHeight(30);
		iv11.setFitWidth(30);
		ImageView iv12 = new ImageView(image);
		iv12.setFitHeight(30);
		iv12.setFitWidth(30);

		ImageView iv13 = new ImageView(image);
		iv13.setFitHeight(30);
		iv13.setFitWidth(30);
		ImageView iv14 = new ImageView(image);
		iv14.setFitHeight(30);
		iv14.setFitWidth(30);
		ImageView iv15 = new ImageView(image);
		iv15.setFitHeight(30);
		iv15.setFitWidth(30);
		ImageView iv16 = new ImageView(image);
		iv16.setFitHeight(30);
		iv16.setFitWidth(30);
		ImageView iv17 = new ImageView(image);
		iv17.setFitHeight(30);
		iv17.setFitWidth(30);
		ImageView iv18 = new ImageView(image);
		iv18.setFitHeight(30);
		iv18.setFitWidth(30);
		ImageView iv19 = new ImageView(image);
		iv19.setFitHeight(30);
		iv19.setFitWidth(30);
		ImageView iv20 = new ImageView(image);
		iv20.setFitHeight(30);
		iv20.setFitWidth(30);
		ImageView iv21 = new ImageView(image);
		iv21.setFitHeight(30);
		iv21.setFitWidth(30);
		ImageView iv22 = new ImageView(image);
		iv22.setFitHeight(30);
		iv22.setFitWidth(30);
		ImageView iv23 = new ImageView(image);
		iv23.setFitHeight(30);
		iv23.setFitWidth(30);
		ImageView iv24 = new ImageView(image);
		iv24.setFitHeight(30);
		iv24.setFitWidth(30);

		ImageView iv25 = new ImageView(image1);
		iv25.setFitHeight(30);
		iv25.setFitWidth(30);
		ImageView iv26 = new ImageView(image1);
		iv26.setFitHeight(30);
		iv26.setFitWidth(30);
		ImageView iv27 = new ImageView(image1);
		iv27.setFitHeight(30);
		iv27.setFitWidth(30);
		ImageView iv28 = new ImageView(image1);
		iv28.setFitHeight(30);
		iv28.setFitWidth(30);
		ImageView iv29 = new ImageView(image1);
		iv29.setFitHeight(30);
		iv29.setFitWidth(30);
		ImageView iv30 = new ImageView(image1);
		iv30.setFitHeight(30);
		iv30.setFitWidth(30);
		ImageView iv31 = new ImageView(image2);
		iv31.setFitHeight(30);
		iv31.setFitWidth(30);
		ImageView iv32 = new ImageView(image1);
		iv32.setFitHeight(30);
		iv32.setFitWidth(30);
		ImageView iv33 = new ImageView(image1);
		iv33.setFitHeight(30);
		iv33.setFitWidth(30);
		ImageView iv34 = new ImageView(image1);
		iv34.setFitHeight(30);
		iv34.setFitWidth(30);
		ImageView iv35 = new ImageView(image1);
		iv35.setFitHeight(30);
		iv35.setFitWidth(30);
		ImageView iv36 = new ImageView(image1);
		iv36.setFitHeight(30);
		iv36.setFitWidth(30);
		ImageView iv37 = new ImageView(image1);
		iv37.setFitHeight(30);
		iv37.setFitWidth(30);
		ImageView iv38 = new ImageView(image1);
		iv38.setFitHeight(30);
		iv38.setFitWidth(30);
		ImageView iv39 = new ImageView(image1);
		iv39.setFitHeight(30);
		iv39.setFitWidth(30);
		ImageView iv40 = new ImageView(image1);
		iv40.setFitHeight(30);
		iv40.setFitWidth(30);
		ImageView iv41 = new ImageView(image1);
		iv41.setFitHeight(30);
		iv41.setFitWidth(30);
		ImageView iv42 = new ImageView(image1);
		iv42.setFitHeight(30);
		iv42.setFitWidth(30);
		ImageView iv43 = new ImageView(image1);
		iv43.setFitHeight(30);
		iv43.setFitWidth(30);
		ImageView iv44 = new ImageView(image1);
		iv44.setFitHeight(30);
		iv44.setFitWidth(30);
		ImageView iv45 = new ImageView(image1);
		iv45.setFitHeight(30);
		iv45.setFitWidth(30);
		ImageView iv46 = new ImageView(image1);
		iv46.setFitHeight(30);
		iv46.setFitWidth(30);
		ImageView iv47 = new ImageView(image1);
		iv47.setFitHeight(30);
		iv47.setFitWidth(30);
		ImageView iv48 = new ImageView(image1);
		iv48.setFitHeight(30);
		iv48.setFitWidth(30);
		ImageView iv49 = new ImageView(image1);
		iv49.setFitHeight(30);
		iv49.setFitWidth(30);
		ImageView iv50 = new ImageView(image1);
		iv50.setFitHeight(30);
		iv50.setFitWidth(30);
		ImageView iv51 = new ImageView(image1);
		iv51.setFitHeight(30);
		iv51.setFitWidth(30);
		ImageView iv52 = new ImageView(image1);
		iv52.setFitHeight(30);
		iv52.setFitWidth(30);
		ImageView iv53 = new ImageView(image1);
		iv53.setFitHeight(30);
		iv53.setFitWidth(30);
		ImageView iv54 = new ImageView(image1);
		iv54.setFitHeight(30);
		iv54.setFitWidth(30);
		

		int imgcounter = 0;
		int img1counter = 0;
		ImageView[] images = {iv,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12,iv13,iv14,iv15,iv16,iv17,iv18,iv19,iv20,iv21,iv22,iv23,iv24};
		ImageView[] images1 = {iv25,iv26,iv27,iv28,iv29,iv30,iv32,iv33,iv34,iv35,iv36,iv37,iv38,iv39,iv40,iv41,iv42,iv43,iv44,iv45,iv46,iv47,iv48,iv49,iv50,iv51,iv52,iv53,iv54};
		Piece[][] boardPieces = gameDriver.getBoard().getPieces();


		for(int i = 0;i<11;i++){
			for(int j = 0;j<11;j++){
				if(boardPieces[i][j].getType() == PieceType.NONE){
					((BoardPiece) holder[i][j].getChildren().get(0)).setGraphic(null);
				}
				else{//set space to the piece in boardPieces. use images and imgcounter for offense and images1 and img1counter for defense. use imageview iv31 for king
					if(boardPieces[i][j].getType() == PieceType.ROOK){//regular piece
						if(boardPieces[i][j].getPlayer().equals(gameDriver.getOffence())){//offense
							//set tile i,j to boardPiece
							System.out.println(boardPieces[i][j].getPlayer().getUserID()+" o");
							((BoardPiece) holder[i][j].getChildren().get(0)).setGraphic(images[imgcounter]);
							imgcounter++;
						}
						else{//defense
							//set tile i,j to boardPiece
							System.out.println(boardPieces[i][j].getPlayer().getUserID());
							((BoardPiece) holder[i][j].getChildren().get(0)).setGraphic(images1[img1counter]);
							img1counter++;
						}
					}
					else{//king
						((BoardPiece) holder[i][j].getChildren().get(0)).setGraphic(iv31);
					}

				}
			}
		}


	}
	
	protected void movePieces(int oldx, int oldy) {
		if(piece1 == -1) {
			piece1 = oldx;
			piece2 = oldy;
			//highlight square
		}
		else {
			if(gameDriver.isMoveValid(gameDriver.getBoard().getPiece(piece1,piece2),clientDriver.getProfile(),piece1,piece2,oldx,oldy)){
				//old code
				gameDriver.movePiece(piece1,piece2,oldx,oldy);
				piece1 = -1;
				piece2 = -1;
				setGame();
				//movetext.setText(gameDriver.getCurrentTurn().getUserID());
			}
			else{
				piece1 = -1;
				piece2 = -1;
			}
		}
	}
	
	public void badMoveMessage() {
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane root = new BorderPane();
		root.setPrefSize(650, 650);
		HBox hbox = addHBox();
		root.setTop(hbox);
		root.setBottom(createContent());
	    Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
