package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Search extends Application{
	protected String username;
	protected Stage main;
	
	public Search(String username){
		this.username = username;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		main = primaryStage;
		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);
		
		Scene scene = new Scene(border,635,375);
		primaryStage.setTitle("Search");
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

	    buttonHome.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent e) {
            		Home home = new Home("Connor");
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
	
}
