package userInterfaces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import projectClasses.Classe;
import projectClasses.Ecole;
import projectClasses.Professeur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProfScene {

	public static Ecole ecole = new Ecole();

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
		Label label1 = new Label("Numero de prof");
		gridP.add(label1, 0, 0);
		TextField txtf1 = new TextField();
		txtf1.setPromptText("prof id here");
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

		Label label4 = new Label("classe id");
		gridP.add(label4, 0, 3);
		TextField txtf4 = new TextField();
		txtf4.setPromptText("classe id here");
		gridP.add(txtf4, 1, 3);

		Button btn1 = new Button("Ajouter prof");
		Button btn2 = new Button("Supprimer prof");
		Button btn3 = new Button("Rechercher prof");

		btn1.setOnAction(e -> {

			if (txtf1.getText().isBlank() || txtf2.getText().isBlank() || txtf3.getText().isBlank()
					|| txtf4.getText().isBlank()) {
				AlertBox.display("Please fill all input fields");
			} else {
				int id = Integer.parseInt(txtf1.getText());
				String nom = txtf2.getText();
				String prenom = txtf3.getText();
				int classeId = -1;
				try {
					classeId = Integer.parseInt(txtf4.getText());
				} catch (Exception e1) {
					AlertBox.display("enter a valid classe id (number)");
				}

				Professeur p = null;
				Classe classe = ecole.rechercherClasseParNum(classeId);
				if (classe != null) {
					try {
						try {
							p = new Professeur(nom, prenom, id, classe);

						} catch (Exception ex) {
							AlertBox.display("can't create prof check inputs");
						}
						ecole.ajouterProf(p);

						txtf1.setText("");
						txtf2.setText("");
						txtf3.setText("");

					} catch (Exception exc) {
						System.out.println("problem is here");

					}

				}
			}

		});

		/////////
		btn2.setOnAction(e -> {
			if (txtf1.getText().isBlank() || txtf2.getText().isBlank() || txtf3.getText().isBlank()
					|| txtf4.getText().isBlank()) {
				AlertBox.display("Please fill all input fields");
			} else {
				int id = Integer.parseInt(txtf1.getText());
				String nom = txtf2.getText();
				String prenom = txtf3.getText();
				int idClasse = -1;
				try {
					idClasse = Integer.parseInt(txtf4.getText());
				} catch (Exception e1) {
					AlertBox.display("enter a valid classe id (number)");
				}
				Professeur p2 = null;
				Classe classe = ecole.rechercherClasseParNum(idClasse);
				if (classe != null) {
					try {
						p2 = new Professeur(nom, prenom, id, classe);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					ecole.supprimerProf(p2);
				}
			}

		});

		btn3.setOnAction(e -> {
			if (txtf1.getText().isBlank()) {
				AlertBox.display("no prof id");
			} else {
				int id = -1;
				try {
					id = Integer.parseInt(txtf1.getText());
				} catch (Exception e1) {
					AlertBox.display("enter a valid classe id (number)");
				}
				Professeur p = ecole.rechercherProfParNum(id);
				if (p != null) {
					AlertBox.display(p.toString());
				} else {
					AlertBox.display("Le prof n'existe pas!");
				}
			}

		});
		Button btn = new Button("telecharger liste profs");
		HBox hbox1 = new HBox();
		hbox1.getChildren().add(btn);
		hbox1.setAlignment(Pos.CENTER);
		btn.setOnAction(e -> {
			
			
			try {
				File file = new File("C:/Users/Acer/Desktop/profs.txt");
				ObjectOutputStream out =new ObjectOutputStream(new FileOutputStream(file));
				for(Professeur prof : ecole.listProfesseur()) {
					out.writeChar('*');
					out.writeChars(prof.toString());
					out.writeChar('\n');
				}
				out.close();
				AlertBox.display("telechargement termine");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btn1, btn2, btn3);

		vbox.getChildren().addAll(retour, gridP, hbox, hbox1);

		Scene scene = new Scene(vbox);

		return scene;

	}

}
