

import java.util.ArrayList;
import java.util.Map;

public class Etudiant extends Personne {

	// le code d'etudient est de la forme : "A1234"
	String codeStudent;
	Filiere filiere;
	int age;
	Map<String, String> emploi;

	Etudiant() {

	}

	public Etudiant(String fname, String lname, Filiere f, String code, int age) throws codeStudentException {
		super(fname, lname);
		this.filiere = f;

		if (code.length() == 5) {

			boolean isinteger = false;
			try {
				int valSt = Integer.parseInt(code.substring(1));
				isinteger = true;
			} catch (NumberFormatException e) {
				System.out.println("invalid code please enter a correct code fomat :'A1234'!!");

			}

			String s = "AZERTYUIOPQSDFGHJKLMWXCVBN";
			ArrayList<Character> a = new ArrayList<Character>();
			for (int i = 0; i < s.length(); i++) {
				a.add(s.charAt(i));
			}
			if (a.contains(code.charAt(0)) && isinteger) {
				this.codeStudent = code;

			} else {
				throw new codeStudentException("invalid code please enter a correct code fomat :'A1234'!!");
			}

		} else {
			throw new codeStudentException("invalide code pleas check the code again");
		}

		this.age = age;

	}

	// getters
	String getCodeStudent() {
		return this.codeStudent;
	}

	Filiere getFiliere() {
		return this.filiere;
	}

	int getAge() {
		return this.age;
	}

	// setters
	void setCode(String code) {
		this.codeStudent = code;
	}

	void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "etudiant ( code: " + this.codeStudent + ", nom: " + this.getNom() + ", prenom: " + this.getPrenom()+", filiere: "+this.getFiliere()+" )";
	}

}