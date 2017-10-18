package UI;

import Drivers.ClientDriver;
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
	protected String username = "Connor";
	protected ClientDriver driver;
	
	public Home(String User) {
		rootPane = new BorderPane();
		username = User;
		ClientDriver driver = new ClientDriver(User);
	}
	public Home(ClientDriver driver) {
		rootPane = new BorderPane();
		this.driver = driver;
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
		border.setLeft(addVBox("Current Games","?","?","?","?"));
		border.setRight(addVBox("Friends","Sent Invites","Invites","?","?"));
		
		
		
		Scene scene = new Scene(border,500,400);
		primaryStage.setTitle("User Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	
	public VBox addVBox(String label,String sub1,String sub2, String sub3,String sub4) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text(label);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Hyperlink options[] = new Hyperlink[] {
	        new Hyperlink(sub1),
	        new Hyperlink(sub2),
	        new Hyperlink(sub3),
	        new Hyperlink(sub4)};

	    for (int i=0; i<4; i++) {
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
            		CreateGame create = new CreateGame(driver);
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
            		Profile prof = new Profile(driver);
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
