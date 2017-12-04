package UI;

import Backend.GameController;
import Backend.Invite;
import Backend.User;
import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Home extends Application {
	protected Stage main;
	private BorderPane rootPane;
	protected ClientDriver clientDriver;
	BorderPane border;
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
		border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		clientDriver.findActiveGames();
		clientDriver.findActiveGames();
		ScrollPane sp = new ScrollPane();
		ScrollPane sp2 = new ScrollPane();
		sp.setContent(addVBoxGames("Current Games",clientDriver.getActiveGames()));
		sp2.setContent(addVBoxInvites("Invites",clientDriver.getActiveInvites()));
		border.setLeft(sp);
		border.setRight(sp2);
		border.setMargin(sp2, new Insets(10));
		Scene scene = new Scene(border,600,400);
		primaryStage.setTitle(clientDriver.profile.getUserID()+" Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public VBox addVBoxGames(String label,List<Integer> games) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[games.size()];
	    	for(int i = 0;i < games.size();i++) {
	    		options[i] = new Button(Integer.toString(games.get(i)));
	    		options[i].setPrefSize(100, 20);
	    		int tempi = i;
	    		options[i].setOnAction(new EventHandler<ActionEvent>() {
	    	       	 
	                @Override
	                public void handle(ActionEvent e) {
	                		GameController gameD = new GameController(Integer.parseInt((options[tempi].getText())));
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
	
	public VBox addVBoxInvites(String label,List<Invite> invites) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Button options[] = new Button[invites.size()];
	    	for(int i = 0;i < invites.size();i++) {
	    		options[i] = new Button(invites.get(i).getUserFrom().getUserID());
	    		options[i].setPrefSize(100, 20);
				int tempi = i;
	    		options[i].setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                		InviteView iv = new InviteView(clientDriver,invites.get(tempi));
	                		try {
	                				iv.start(main);
	    					} catch (Exception e1) {
	    						// TODO Auto-generated catch block
	    						e1.printStackTrace();
	    					}
	                    
	                }
	            });
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
	    buttonGame.setPrefSize(120, 20);

	    Button buttonLogout = new Button("Logout");
	    buttonLogout.setPrefSize(120, 20);
	    
	    Button buttonRefresh = new Button("Refresh");
	    buttonLogout.setPrefSize(120, 20);
	    
	    Button buttonProfile = new Button("Profile");
	    buttonProfile.setPrefSize(120, 20);
	    
	    buttonGame.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		CreateGame create = new CreateGame(clientDriver);
            		try {
						create.start(main);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                
            }
        });
	    
	    buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent e) {
            		border.getChildren().clear();
            		try {
            			String name = clientDriver.getProfile().getUserID();
            			clientDriver = new ClientDriver(name);
            			HBox hbox = addHBox();
            			border.setTop(hbox);
            			border.setLeft(addVBoxGames("Current Games",clientDriver.getActiveGames()));
            			border.setRight(addVBoxInvites("Invites",clientDriver.getActiveInvites()));
					} catch (Exception e1) {
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
	    hbox.getChildren().addAll(spacer,buttonProfile,buttonGame, buttonRefresh, buttonLogout);

	    return hbox;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
