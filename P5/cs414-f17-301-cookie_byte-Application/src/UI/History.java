package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class History extends Application {
	
	private final BorderPane rootPane;
	protected String username;
	
	public History(String User) {
		rootPane = new BorderPane();
		username = User;
		
	}
	
	public Pane getRootPane() {
		return rootPane;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		border.setLeft(addVBox());
		Scene scene = new Scene(border,500,400);

		
		primaryStage.setTitle("User Home");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

	public VBox addVBox() {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

	    Text title = new Text("Data");
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    vbox.getChildren().add(title);

	    Hyperlink options[] = new Hyperlink[] {
	        new Hyperlink("Sales"),
	        new Hyperlink("Marketing"),
	        new Hyperlink("Distribution"),
	        new Hyperlink("Costs")};

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

	    Button buttonCurrent = new Button("Account Info");
	    buttonCurrent.setPrefSize(100, 20);

	    Button buttonProjected = new Button("Logout");
	    buttonProjected.setPrefSize(100, 20);
	    
	    hbox.getChildren().addAll(buttonCurrent, buttonProjected);

	    return hbox;
	}
	
}
