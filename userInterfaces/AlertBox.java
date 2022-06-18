package userInterfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class AlertBox {
	public static void display(String message) {
		Stage window = new Stage();
		VBox vbox = new VBox();
		vbox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		vbox.setPadding(new Insets(10, 20, 20, 10));
		
		Label label = new Label(message);
		label.setFont(Font.font("Verdana", FontPosture.REGULAR, 14));
		Button btn = new Button("close");
		vbox.getChildren().addAll(label, btn);
		btn.setOnAction(e -> window.close());
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.setTitle("alert box");
		window.show();
	}
}

