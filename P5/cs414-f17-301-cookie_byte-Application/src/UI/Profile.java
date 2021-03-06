package UI;

import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Profile extends Application{
	
	protected Stage main;
	public ClientDriver driver;
	
	public Profile(ClientDriver driver){
		this.driver = driver;
	}
	
	public void start(Stage primaryStage) {
		main = primaryStage;
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		GridPane grid = new GridPane();
		grid.setPrefHeight(400);
		grid.setAlignment(Pos.CENTER);
		Image image = new Image(getClass().getResourceAsStream("profile.jpg"));
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage bg = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background b = new Background(bg);
		ImageView iv = new ImageView(image);
		iv.setFitHeight(30);
		iv.setFitWidth(30);
		grid.setBackground(b);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		Text scenetitle = new Text("Profile");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Email:");
		grid.add(userName, 0, 1);
		
		Text email = new Text(driver.getProfile().getEmail());
		email.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(email, 1, 1);
		
		Label nn = new Label("Username:");
		grid.add(nn, 0, 2);
		
		Text name = new Text(driver.getProfile().getUserID());
		name.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(name, 1, 2);
		
		Label wins = new Label("Wins:");
		grid.add(wins, 0, 3);

		Text winstext = new Text(Integer.toString(driver.getProfile().getWins()));
		winstext.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(winstext, 1, 3);
		
		Label loses = new Label("Loses: ");
		grid.add(loses, 0, 4);

		Text losestext = new Text(Integer.toString(driver.getProfile().getLosses()));
		losestext.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(losestext, 1, 4);
		
		float winpct = 0;
		if(driver.getProfile().getLosses()+driver.getProfile().getWins() == 0){
			
		}
		else{
			winpct = driver.getProfile().getWins()/(driver.getProfile().getLosses()+driver.getProfile().getWins());
		}
		Label winp = new Label("Win %: ");
		grid.add(winp, 0, 5);

		Text winpercentage = new Text(Float.toString(winpct*100));
		winpercentage.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(winpercentage, 1, 5);

		Button buttonCurrent = new Button("Home");
	    buttonCurrent.setPrefSize(100, 20);
		
		border.setBottom(grid);
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonHome = new Button("Home");
	    buttonHome.setPrefSize(100, 20);

		Button buttonUnreg = new Button("Unregister This User");
		buttonHome.setPrefSize(100, 20);

	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Home home = new Home(driver);
            		try {
						home.start(main);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                
            }
        });

		buttonUnreg.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				LoginAuth la = new LoginAuth();
				driver.deleteUser();
				Login newLogin = new Login();
				try {
					newLogin.start(main);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	  
	    
	    final Pane spacer = new Pane();
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    spacer.setMinSize(10, 1);
	    hbox.getChildren().addAll(spacer,buttonUnreg);
		hbox.getChildren().addAll(buttonHome);

	    return hbox;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
