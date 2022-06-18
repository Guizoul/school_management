package userInterfaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import projectClasses.Ecole;
import projectClasses.Etudiant;
import projectClasses.Filiere;
import projectClasses.codeStudentException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EtudiantScene {
	static Ecole ecole = new Ecole();

	public static Scene build() {
		VBox vbox = new VBox(40);
		vbox.setAlignment(Pos.CENTER);
		vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		vbox.setPadding(new Insets(60, 60, 60, 60));

		Button retour = new Button("⬅retour");
		retour.setStyle("-fx-background-color: #3390fa");
		retour.setOnAction(e -> AdminInterface.window.setScene(HomeScene.build()));

		GridPane gridP = new GridPane();
		gridP.setAlignment(Pos.CENTER);
		gridP.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		gridP.setVgap(40);
		gridP.setHgap(20);

		// Labels & input fields
		Label label1 = new Label("Code d'etudiant");
		gridP.add(label1, 0, 0);
		TextField txtf1 = new TextField();
		txtf1.setPromptText("code etudiant here");
		gridP.add(txtf1, 1, 0);

		Label label2 = new Label("Nom");
		gridP.add(label2, 0, 1);
		TextField txtf2 = new TextField();
		txtf2.setPromptText("nom here");
		gridP.add(txtf2, 1, 1);

		Label label3 = new Label("Prenom");
		gridP.add(label3, 0, 2);
		TextField txtf3 = new TextField();
		txtf3.setPromptText("prenom here");
		gridP.add(txtf3, 1, 2);

		Label label4 = new Label("Filiere");
		gridP.add(label4, 0, 3);
		ChoiceBox<Filiere> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().add(Filiere.CLOUDIOT);
		choiceBox.getItems().add(Filiere.MOBILEDEV);
		choiceBox.getItems().add(Filiere.SECURITY);
		choiceBox.getItems().add(Filiere.WEBDEV);
		gridP.add(choiceBox, 1, 3);

		Label label5 = new Label("age");
		gridP.add(label5, 0, 4);
		TextField txtf4 = new TextField();
		txtf4.setPromptText("age here");
		gridP.add(txtf4, 1, 4);

		Button btn1 = new Button("Ajouter etudiant");
		Button btn2 = new Button("Supprimer etudiant");
		Button btn3 = new Button("Rechercher etudiant");

		btn1.setOnAction(e -> {

			if (txtf1.getText().isBlank() || txtf2.getText().isBlank() || txtf3.getText().isBlank()
					|| txtf4.getText().isBlank() || choiceBox.getValue() == null) {
				AlertBox.display("Please fill all input fields");
			} else {
				String id = txtf1.getText();
				String name = txtf2.getText();
				String fname = txtf3.getText();

				int ageet = Integer.parseInt(txtf4.getText());
				Filiere major = choiceBox.getValue();

				Etudiant et = null;

				try {
					try {
						et = new Etudiant(name, fname, major, id, ageet);

					} catch (Exception ex) {
						AlertBox.display("can't create etudiant check inputs");
					}
					ecole.ajouterEtudiant(et);

					txtf1.setText("");
					txtf2.setText("");
					txtf3.setText("");

				} catch (Exception exc) {
					System.out.println("problem is here");

				}

			}
		});

		/////////
		btn2.setOnAction(e -> {
			if (txtf1.getText().isBlank() || txtf2.getText().isBlank() || txtf3.getText().isBlank()
					|| txtf4.getText().isBlank() || choiceBox.getValue() == null) {
				AlertBox.display("Please fill all input fields");
			} else {
				String code = txtf1.getText();
				String name = txtf2.getText();
				String fname = txtf3.getText();
				Filiere major = choiceBox.getValue();
				int ageet = Integer.parseInt(txtf4.getText());
				Etudiant etu = null;
				try {
					etu = new Etudiant(name, fname, major, code, ageet);
				} catch (codeStudentException e2) {
					e2.printStackTrace();
					System.out.print("code incorrect");
				}

				ecole.supprimerEtudiant(etu);
			}
		});

		btn3.setOnAction(e -> {

			if (txtf1.getText().isBlank()) {
				AlertBox.display("no etudiant code!!");
			} else {

				String idString = txtf1.getText();
				Etudiant e1 = ecole.rechercherEtudiantParNum(idString);
				if (e1 != null) {
					AlertBox.display(e1.toString());
				} else {
					AlertBox.display("L'etudiant n'existe pas!");
				}
			}
		});

		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btn1, btn2, btn3);
		
		Button btn = new Button("telecharger liste etudiants");
		HBox hbox1 = new HBox();
		hbox1.getChildren().add(btn);
		hbox1.setAlignment(Pos.CENTER);
		btn.setOnAction(e -> {
			
			
			try {
				Ecole eco = new Ecole();
				File file = new File("C:/Users/Hp/Desktop/etudiants.txt");
				ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(file));
				for(Etudiant et : eco.listEtudiant()) {
					System.out.println( eco.listEtudiant());
					out.writeChar('*');
					out.writeObject(et);
					out.writeChar('\n');
				}
				out.close();
				AlertBox.display("telechargement termine");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		vbox.getChildren().addAll(retour, gridP, hbox, hbox1);

		Scene scene = new Scene(vbox);

		return scene;

	}

}
