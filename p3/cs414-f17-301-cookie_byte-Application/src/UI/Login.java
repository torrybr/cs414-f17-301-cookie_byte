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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {
	
	public String loggedInUser;
	public Stage primary;
	public LoginAuth auth= new LoginAuth();

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setId("introPane");
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25,25,25,25));
			
			Text scenetitle = new Text("Welcome");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
			grid.add(scenetitle, 0, 0, 2, 1);

			Label userName = new Label("Username:");
			userName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
			grid.add(userName, 0, 1);

			TextField userTextField = new TextField();
			grid.add(userTextField, 1, 1);

			Label pw = new Label("Password:");
			pw.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
			grid.add(pw, 0, 2);

			PasswordField pwBox = new PasswordField();
			grid.add(pwBox, 1, 2);
			
			Button btn = new Button("Sign in");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(btn);
			grid.add(hbBtn, 1, 4);
			
			Button btnNew = new Button("Create new user.");
			HBox hbBtnNew = new HBox(10);
			hbBtnNew.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtnNew.getChildren().add(btnNew);
			grid.add(hbBtnNew, 1, 5);
			
			final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 6);
			
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	                String username = userTextField.getText();
	                String pass = pwBox.getText();
	            		loginClick(username,pass);
	                
	            }
	        });
	        
	        btnNew.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            @Override
	            public void handle(ActionEvent e) {
	            		createNewUser();
	                primaryStage.hide();
	            }
	        });
	       
			Scene scene = new Scene(grid,635,375);
			scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primary = primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void loginClick(String username, String password) {
		Stage stage = new Stage();
		VBox box = new VBox();
		box.setPadding(new Insets(10));
		box.setAlignment(Pos.CENTER);
		Label label = new Label("Authenticating....");
		//connect to server, wait for response. 
		Button btnLogin = new Button();
		btnLogin.setText("Continue");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 stage.hide();
				 if(auth.checkAuth(username, password)) {
					 loggedInUser = username;
					 loginResult(true);
				 }
				 else
					 loginResult(false);
			 }
		});
		box.getChildren().add(label);
		box.getChildren().add(btnLogin);
		Scene scene = new Scene(box, 150, 100);
		stage.setScene(scene);
		stage.show();

	}
	
	public void loginResult(boolean result) {
		if(result) {//succesful login
			ClientDriver driver = new ClientDriver(loggedInUser);
			Home nextPage = new Home(driver);
			try {
				nextPage.start(primary);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {//unsuccessfull
			Stage stage = new Stage();
			VBox box = new VBox();
			box.setPadding(new Insets(10));
			box.setAlignment(Pos.CENTER);
			Label label = new Label("Login Failed. \nPlease try again.");
			//connect to server, wait for response. 
			Button btnLogin = new Button();
			btnLogin.setText("Cancel");
			btnLogin.setOnAction(new EventHandler<ActionEvent>() {
				 @Override
				 public void handle(ActionEvent event) {
					 stage.hide();
					 
				 }
			});
			box.getChildren().add(label);
			box.getChildren().add(btnLogin);
			Scene scene = new Scene(box, 200, 100);
			stage.setScene(scene);
			stage.show();
		}//
	}
	public void createNewUser() {
		Stage stage = new Stage();
		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setId("createPane");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Email:");
		userName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);
		
		Label nickName = new Label("Username:");
		nickName.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(nickName, 0, 2);

		TextField userNickameField = new TextField();
		grid.add(userNickameField, 1, 2);

		Label pw = new Label("Password:");
		pw.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
		grid.add(pw, 0, 3);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 3);
		
		Button btn = new Button("Create Account");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 5);
		
		Button btnNew = new Button("Go back to Login Screen");
		HBox hbBtnNew = new HBox(10);
		hbBtnNew.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnNew.getChildren().add(btnNew);
		grid.add(hbBtnNew, 1, 9);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
		
        btn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            		String email = userTextField.getText();
            		String nickname = userNickameField.getText();
            		String password = pwBox.getText();
            		if(email.length() > 0 && nickname.length() > 0 && password.length() > 0) {
            			createAccountClicked(email, nickname, password);
            			stage.close();
            		}
            		else {
            			final Text actiontarget = new Text("You must fill out all fields!");
            			grid.add(actiontarget, 1, 6);
            		}
            }
        });
        
        btnNew.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
            		stage.close();
            		primary.show();
                
            }
        });
       
		Scene scene = new Scene(grid,635,375);
		scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();
	}
	
	public void createAccountClicked(String email, String nickname, String password) {
		ClientDriver newClient = new ClientDriver(email, nickname, password);
		primary.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
