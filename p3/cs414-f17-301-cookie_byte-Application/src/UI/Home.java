package UI;

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

public class Home extends Application {
	protected Stage main;
	private BorderPane rootPane;
	protected ClientDriver clientDriver;
	
	public Home(String name) {
		rootPane = new BorderPane();
		this.clientDriver= new ClientDriver(name);
	}
	public Home(ClientDriver driver) {
		rootPane = new BorderPane();
		this.clientDriver = driver;
	}
	public Pane getRootPane() {
		return rootPane;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		border.setLeft(addVBoxGames("Current Games",clientDriver.getGameIDs()));
		border.setRight(addVBoxFriends("Invites",clientDriver.getInviteIDs()));
		
		Scene scene = new Scene(border,500,400);
		//primaryStage.setTitle(clientDriver.profile.getNickname()+" Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public VBox addVBoxGames(String label,List<String> games) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[games.size()];
	    	for(int i = 0;i < games.size();i++) {
	    		options[i] = new Button(games.get(i));
	    		options[i].setPrefSize(100, 20);
	    		int tempint = i;
	    		options[i].setOnAction(new EventHandler<ActionEvent>() {
	    	       	 
	                @Override
	                public void handle(ActionEvent e) {
	                		//GameDriver gameD = new GameDriver((options[tempint].getText()));
	                		try {
	                		//		Game game = new Game(clientDriver,gameD);
	                		//		game.start(main);
	    					} catch (Exception e1) {
	    						e1.printStackTrace();
	    					}
	                    
	                }
	            });
	    		VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
		    vbox.getChildren().add(options[i]);
	    	}

	    return vbox;
	}
	
	public VBox addVBoxFriends(String label,List<String> invites) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[invites.size()];
	    	for(int i = 0;i < invites.size();i++) {
	    		options[i] = new Button(invites.get(i));
	    		options[i].setPrefSize(100, 20);
	    		/*options[i].setOnAction(new EventHandler<ActionEvent>() {
	    	       	 
	                @Override
	                public void handle(ActionEvent e) {
	                		GameDriver gameD = new GameDriver(Integer.parseInt(options[i].getText()));
	                		try {
	                				Game game = new Game(clientDriver,gameD);
	    					} catch (Exception e1) {
	    						// TODO Auto-generated catch block
	    						e1.printStackTrace();
	    					}
	                    
	                }
	            });*/
	    		VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
		    vbox.getChildren().add(options[i]);
	    	}
	    return vbox;
	}
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonGame = new Button("Create Game");
	    buttonGame.setPrefSize(100, 20);

	    Button buttonLogout = new Button("Logout");
	    buttonLogout.setPrefSize(100, 20);
	    
	    Button buttonProfile = new Button("Profile");
	    buttonProfile.setPrefSize(100, 20);
	    
	    buttonGame.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		CreateGame create = new CreateGame(clientDriver);
            		try {
						create.start(main);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                
            }
        });
	    
	    buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Login login = new Login();
            		try {
            				
            				login.start(main);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                
            }
        });
	    
	    buttonProfile.setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent e) {
            		Profile prof = new Profile(clientDriver);
            		try {
						prof.start(main);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                
            }
        });
	    
	    
	    final Pane spacer = new Pane();
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    spacer.setMinSize(10, 1);
	    hbox.getChildren().addAll(spacer,buttonProfile,buttonGame, buttonLogout);

	    return hbox;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
