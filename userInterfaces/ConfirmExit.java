package userInterfaces;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmExit {
	static boolean answer;
	public static boolean confirm() {
		Stage fenetre = new Stage();
		
		fenetre.initModality(Modality.APPLICATION_MODAL);
		fenetre.setTitle("confirm");
		fenetre.setMinWidth(250);
		fenetre.setMinHeight(150);
		
		Label l = new Label("Sure you want to exit?");
		Button yesBtn = new Button("Yes");
		yesBtn.setOnAction(e -> {
			 answer = true;
			 fenetre.close();
		});
		Button noBtn = new Button("No");
		noBtn.setOnAction(e -> {
			answer = false;
			fenetre.close();
		});
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(l, yesBtn, noBtn);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #5aa0fc");
//		vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene scene = new Scene(vbox);
		fenetre.setScene(scene);
		fenetre.showAndWait();
		
		return answer;
		
	}
}

