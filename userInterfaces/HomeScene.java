package userInterfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HomeScene {
	public static Button btn2, btn3;
	public static Scene build() {
		VBox vb = new VBox();
		vb.setPadding(new Insets(10));
		Text nav = new Text("bienvenu a l'App de gestion \n \n");
		nav.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		vb.getChildren().add(nav);
		vb.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		VBox vhome = new VBox(10);
		vhome.setAlignment(Pos.CENTER);
		vhome.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


		// vhome.setPadding(new Insets(50,50,50,50));

		btn2 = new Button("espace d'etudiants");
		btn2.setOnAction(e -> {
			AdminInterface.window.setTitle("espace etudiant");
			AdminInterface.window.setScene(EtudiantScene.build());
		});

		btn3 = new Button("espace de professeurs");
		btn3.setOnAction(e -> {
			AdminInterface.window.setTitle("espace professeur");
			AdminInterface.window.setScene(ProfScene.build());
		});
		

		Button retour = new Button("✖logout");
		retour.setStyle("-fx-background-color: #3390fa");
		retour.setOnAction(e -> AdminInterface.window.setScene(LoginScene.build()) );

		vhome.getChildren().addAll(retour, vb, btn2, btn3);
		vhome.setMargin(btn3, new Insets(50, 0, 0, 0));
		vhome.setMargin(btn2, new Insets(50, 0, 0, 0));
		Scene scene = new Scene(vhome, 400, 400);
		
		return scene;
	}
}
