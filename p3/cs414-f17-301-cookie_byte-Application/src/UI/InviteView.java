package UI;

import Drivers.ClientDriver;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import Backend.Invite;

public class InviteView extends Application {
	protected Stage main;
	public Invite inviteDriver;
	public ClientDriver driver;
	
	public InviteView(ClientDriver driver, Invite invite) {
		this.driver = driver;
		inviteDriver = invite;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		main = primaryStage;
		BorderPane border = new BorderPane();
		border.setTop(addHBox());
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("Invite from "+inviteDriver.getUserFrom().getUserID());//add invite sender
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    Button buttonAccept = new Button("Accept");
		buttonAccept.setPrefSize(100, 20);

	    Button buttonDecline = new Button("Decline");
		buttonDecline.setPrefSize(100, 20);

	    Button buttonHome = new Button("Home");
	    buttonHome.setPrefSize(100, 20);

	    buttonAccept.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            	//accept invite in db
            	inviteDriver.acceptInvite();
				Home home = new Home(driver);
            		try {
						home.start(main);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

            }
        });

	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
            		//decline invite in db
            		inviteDriver.declineInvite();
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
	    hbox.getChildren().addAll(spacer,buttonAccept,buttonDecline, buttonHome);

	    return hbox;
	}
	
}
