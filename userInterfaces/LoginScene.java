package userInterfaces;

import ecoleClasses.Admin;
import ecoleClasses.Ecole;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginScene {
	public static Button btn;
	static Ecole ecole = new Ecole();

	public static Scene build() {
		// LOGIN SCENE
		VBox vbox = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();

		vbox.setPadding(new Insets(20, 20, 20, 20));

		Text t1 = new Text("username");
		TextField txf1 = new TextField();
		txf1.setPromptText("username here");
		hb1.setPadding(new Insets(30, 20, 20, 20));
		hb1.getChildren().addAll(t1, txf1);
		hb1.setMargin(txf1, new Insets(0, 0, 0, 20));

		Text t2 = new Text("password");
		PasswordField txf2 = new PasswordField();
		txf2.setPromptText("password here");
		hb2.setPadding(new Insets(20, 20, 20, 20));
		hb2.getChildren().addAll(t2, txf2);
		hb2.setMargin(txf2, new Insets(0, 0, 0, 20));

		btn = new Button("Log in");
		btn.setOnAction(e -> {
			Admin admin = new Admin(txf1.getText(), txf2.getText());
			for (Admin a : ecole.listAdmin()) {
				if (a.getUsername().equals(admin.getUsername()) && a.getPassword().equals(admin.getPassword())) {
					AdminInterface.window.setTitle("home");
					AdminInterface.window.setScene(HomeScene.build());
				} else {
					AlertBox.display("incorrect informations");
				}
			}
		});

		vbox.getChildren().addAll(hb1, hb2, btn);
		vbox.setMargin(btn, new Insets(0, 0, 0, 140));
		vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene sc = new Scene(vbox, 350, 300);
		return sc;
	}
}
