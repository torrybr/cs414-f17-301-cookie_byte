package UI;

import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateGame  extends Application {
	protected String sender;
	protected String reciever;
	protected Stage main; 
	public ClientDriver driver;
	
	public CreateGame(ClientDriver driver){
	 	this.driver = driver;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane border = new BorderPane();
		border.setTop(addHBox());
		border.setLeft(addVBox("Send To","?","?","?","?"));
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("Login");
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

	    Button options[] = new Button[] {
	        new Button(sub1),
	        new Button(sub2),
	        new Button(sub3),
	        new Button(sub4)};
	    
	    options[0].setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		reciever = options[0].getText();
            		send();
            }
        });
	    
	    options[1].setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent e) {
            		reciever = options[1].getText();
            		send();
            }
        });
	    
	    options[2].setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent e) {
            		reciever = options[2].getText();
                send();
            }
        });
	    
	    options[3].setOnAction(new EventHandler<ActionEvent>() {
	       	 
            @Override
            public void handle(ActionEvent e) {
            		reciever = options[3].getText();
            		send();
            }
        });
	    
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

	    Button buttonHome = new Button("Home");
	    buttonHome.setPrefSize(100, 20);

	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Home home = new Home(driver);
            		try {
						home.start(main);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
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
		Game game = new Game();
		try {
			game.start(main);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
