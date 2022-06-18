package userInterfaces;

import ecoleClasses.Ecole;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminInterface extends Application {

	public static void main(String[] args) {
		AdminInterface.launch(args);
	}

	Ecole ecole = new Ecole();
	static Stage window;

	@Override
	public void start(Stage arg0) throws Exception {
		window = arg0;
		
		// Close the program properly
		window.setOnCloseRequest(e -> {
			e.consume();
			boolean answer = ConfirmExit.confirm();
			if (answer)
				window.close();
		});
		// login
		Scene sc = LoginScene.build();
		window.setScene(sc);
		window.setTitle("Welcome :SCHOOL NAME");
		/// show
		window.show();

	}


}

