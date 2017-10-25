package UI;

import Drivers.ClientDriver;
import Drivers.GameDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
		int[] tempGames = {0,1};//get games later from clientDriver
		int[] tempInvites = {0};//get invites later from clientDriver
		border.setLeft(addVBoxGames("Current Games",tempGames));
		border.setRight(addVBoxFriends("Invites",tempInvites));
		
		Scene scene = new Scene(border,500,400);
		primaryStage.setTitle(clientDriver.profile.getNickname()+" Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public VBox addVBoxGames(String label,int[] games) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[games.length];
	    	for(int i = 0;i < games.length;i++) {
	    		options[i] = new Button(Integer.toString(games[i]));
	    		options[i].setPrefSize(100, 20);
	    		int tempint = i;
	    		options[i].setOnAction(new EventHandler<ActionEvent>() {
	    	       	 
	                @Override
	                public void handle(ActionEvent e) {
	                		GameDriver gameD = new GameDriver(Integer.parseInt(options[tempint].getText()));
	                		try {
	                				Game game = new Game(clientDriver,gameD);
	                				game.start(main);
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
	
	public VBox addVBoxFriends(String label,int[] games) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[games.length];
	    	for(int i = 0;i < games.length;i++) {
	    		options[i] = new Button(Integer.toString(games[i]));
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
	            });
	    		VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
		    vbox.getChildren().add(options[i]);
	    	}*/
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
