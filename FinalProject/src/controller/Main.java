package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		showPrincipalPane(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void showPrincipalPane(Stage primaryStage)
	{
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(Main.class.getResource("../view/PrincipalPane.fxml"));
			BorderPane panelPrincipal = (BorderPane)cargador.load();
			Scene scene = new Scene(panelPrincipal);
			PrincipalController controlador = cargador.getController();
			controlador.setPrincipalPane(panelPrincipal);
			controlador.setPrincipal(this);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
