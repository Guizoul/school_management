

import java.util.ArrayList;

public class Professeur extends Personne {

	int codeProf;
	Classe classe;
	ArrayList<Filiere> profFilieres;

	Professeur() {

	}

	public Professeur(String nom, String prenom, int code, Classe classe) {
		super(nom, prenom);
		this.codeProf = code;
		this.classe = classe;

	}

	// getters

	int getCode() {
		return this.codeProf;
	}

	Classe getClasse() {
		return this.classe;
	}

	ArrayList<Filiere> getProfFiliere() {
		return this.profFilieres;
	}

	@Override
	public String toString() {
		return "Professeur ( id:  " + this.getCode() + ", nom: " + this.getNom() + ", prenom: " + this.getPrenom()+")";
	}

}