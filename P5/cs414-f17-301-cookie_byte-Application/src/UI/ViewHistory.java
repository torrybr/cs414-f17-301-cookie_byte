package UI;

import Backend.GameController;
import Backend.Invite;
import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class ViewHistory extends Application {
	protected String sender;
	protected String reciever;
	protected Stage main;
	public ClientDriver client;

	public ViewHistory(ClientDriver driver){
	 	this.client = driver;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane border = new BorderPane();
		border.setTop(addHBox());
		String other = "D";
		if(client.getProfile().getUserID().equals("D"))
			other = "A";
		border.setLeft(addVBox("Select a past game to view it.",client.getGameIDs()));
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("History of "+client.getProfile().getUserID());
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

	public VBox addVBox(String label,List<Integer> pastGames) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

		Button options[] = new Button[pastGames.size()];
		for(int i = 0;i < pastGames.size();i++) {
			options[i] = new Button(Integer.toString(pastGames.get(i)));
			options[i].setPrefSize(100, 20);
			int tempi = i;
			options[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					GameController gameD = new GameController(Integer.parseInt((options[tempi].getText())));
					try {
						OldGameView game = new OldGameView(client,gameD);
						game.start(main);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			});
			VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
			vbox.getChildren().add(options[i]);
		}
	    
	    
	    VBox.setMargin(options[0], new Insets(0, 0, 0, 8));
	    vbox.getChildren().add(options[0]);

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
		Invite i = new Invite(reciever,client.getProfile());
	}
	
}
