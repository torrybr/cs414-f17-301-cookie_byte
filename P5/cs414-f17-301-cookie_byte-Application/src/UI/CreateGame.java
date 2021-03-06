package UI;

import Backend.DatabaseTranslator;
import Backend.GameController;
import Backend.Invite;
import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateGame  extends Application {
	protected String sender;
	protected String reciever;
	protected Stage main; 
	public ClientDriver client;
	
	public CreateGame(ClientDriver driver){
	 	this.client = driver;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane border = new BorderPane();
		border.setTop(addHBox());
		border.setLeft(addVBox());
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("Creating a game");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

	public VBox addVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text("Specify which user you'd like to play against. ");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    TextField userSearch = new TextField();
	    vbox.getChildren().add(userSearch);
	    
	    Button btn = new Button("Send Invite");
	    btn.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            	reciever = userSearch.getText();
            	if(reciever.equals(client.getProfile().getUserID())){
            		Stage stage = new Stage();
        			VBox box = new VBox();
        			box.setPadding(new Insets(10));
        			box.setAlignment(Pos.CENTER);
        			Label label = new Label("Only losers play games with themselves.");
        			box.getChildren().add(label);
        			Scene scene = new Scene(box, 300, 100);
        			stage.setScene(scene);
        			stage.show();
            	}
            	else{
            		if(client.searchPlayers(reciever) == true){
            			send();
            			Home home = new Home(client);
            			try {
            				home.start(main);
            			} catch (Exception e1) {
            				e1.printStackTrace();
            			}
            			Stage stage = new Stage();
            			VBox box = new VBox();
            			box.setPadding(new Insets(10));
            			box.setAlignment(Pos.CENTER);
            			Label label = new Label("Your invite has been sent.");
            			box.getChildren().add(label);
            			Scene scene = new Scene(box, 350, 100);
            			stage.setScene(scene);
            			stage.show();
            		}
            		else{
            			Stage stage = new Stage();
            			VBox box = new VBox();
            			box.setPadding(new Insets(10));
            			box.setAlignment(Pos.CENTER);
            			Label label = new Label("Bad username");
            			box.getChildren().add(label);
            			Scene scene = new Scene(box, 250, 100);
            			stage.setScene(scene);
            			stage.show();
            		}
            	}
            }
	    });

	    vbox.getChildren().add(btn);

	    return vbox;
	}

	public HBox addHBox() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonHome = new Button("Home");
	    buttonHome.setPrefSize(100, 20);

	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Home home = new Home(client);
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
	    hbox.getChildren().addAll(spacer,buttonHome);

	    return hbox;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void send() {
		//create a new game with those sender reciever as player 1 player 2. How to create unique game ID?
		new Invite(DatabaseTranslator.getUser(reciever),client.getProfile());
	}
	
}
