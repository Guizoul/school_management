

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import userInterfaces.AlertBox;

public class Ecole {

	ArrayList<Etudiant> listEtudiant = new ArrayList<>();
	ArrayList<Professeur> listProfesseur = new ArrayList<>();
	ArrayList<Classe> listClasse = new ArrayList<>();
	ArrayList<Admin> listAdmin = new ArrayList<>();


	// connect to my database called ecole

	private Connection connection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecole", "root", "password");

			System.out.println("everything is under control");

		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println("connection problem");
		}
		return con;
	}

	public ArrayList<Etudiant> listEtudiant() {
		Statement st, st2;
		try {
			Connection connexion = connection();
			st = connexion.createStatement();
			st2 = connexion.createStatement();
			String requete = "select * from etudiant;";
			ResultSet resultat = st.executeQuery(requete);

			Etudiant e = null;
			Filiere f = null;
			int idFiliere, age;
			String nom, prenom, codeEt;
			String nomFiliere = null;

			while (resultat.next()) {
				codeEt = resultat.getString(1);
				nom = resultat.getString(2);
				prenom = resultat.getString(3);
				idFiliere = resultat.getInt(4);
				age = resultat.getInt(5);

				String requete2 = "select * from filiere where idFiliere=" + idFiliere + ";";
				ResultSet resultat2 = st2.executeQuery(requete2);
				while (resultat2.next()) {
					nomFiliere = resultat2.getString("nom");
				}

				f = Filiere.valueOf(nomFiliere);

				try {
					e = new Etudiant(nom, prenom, f, codeEt, age);
				} catch (codeStudentException e1) {
					e1.printStackTrace();
					System.out.println("\"invalid code please enter a correct code fomat :'A1234'!!\"");
				}
				listEtudiant.add(e);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Connection error!");

		}

		return listEtudiant;
	};

	public ArrayList<Professeur> listProfesseur() {
		Statement st, st2;
		try {
			Connection connexion = connection();
			st = connexion.createStatement();
			st2 = connexion.createStatement();

			// prof(codeprof int , nom varchar(20) ,prenom varchar(20) , idclasse int ,
			// primary key(codeprof));

			String requete = "select * from prof";
			ResultSet resultat = st.executeQuery(requete);

			Professeur p = null;
			Classe c;
			int codeProf, idClasse, capacityClasse = -1;
			boolean isCc = false;
			String nom, prenom;

			while (resultat.next()) {
				codeProf = resultat.getInt(1);
				nom = resultat.getString(2);
				prenom = resultat.getString(3);
				idClasse = resultat.getInt(4);

				// classe(idclasse int primary key , capacity int , iscc boolean);

				String requete2 = "select * from classe where idClasse=" + idClasse + ";";
				ResultSet resultat2 = st2.executeQuery(requete2);

				while (resultat2.next()) {
					capacityClasse = resultat2.getInt(2);
					isCc = resultat2.getBoolean(3);
				}

				c = new Classe(idClasse, capacityClasse, isCc);

				p = new Professeur(nom, prenom, codeProf, c);

				listProfesseur.add(p);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Connection error!");

		}

		return listProfesseur;
	};

	public ArrayList<Classe> listClasse() {
		Statement st;
		try {
			Connection connexion = connection();
			st = connexion.createStatement();
			// classe(idclasse int primary key , capacity int , iscc boolean);

			String requete2 = "select * from classe;";
			ResultSet resultat2 = st.executeQuery(requete2);

			Classe c;
			int idClasse, capacityClasse;
			boolean isCc;

			while (resultat2.next()) {
				idClasse = resultat2.getInt(1);
				capacityClasse = resultat2.getInt(2);
				isCc = resultat2.getBoolean(3);

				c = new Classe(idClasse, capacityClasse, isCc);

				listClasse.add(c);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Connection error!");

		}

		return listClasse;
	};

	public ArrayList<Admin> listAdmin() {
		Admin admin;
		String username;
		String password;
		Statement st;
		try {
			Connection connexion = connection();
			st = connexion.createStatement();
			// classe(idclasse int primary key , capacity int , iscc boolean);

			String requete = "select * from administrateur";
			ResultSet result = st.executeQuery(requete);
			while (result.next()) {
				username = result.getString(2);
				password = result.getString(3);
				admin = new Admin(username, password);
				listAdmin.add(admin);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Connection error!");

		}

		return listAdmin;

	}

	///////////////////////////////////
	////////// ADMIN METHODS///////////
	//////////////////////////////////

	/// SUT ETUDIANT

	public void ajouterEtudiant(Etudiant etudiant) {
		if (etudiant.getCodeStudent() != null) {
			String codeEtudiant = etudiant.getCodeStudent();

			if (this.rechercherEtudiantParNum(codeEtudiant) == null) {
				// etudiant n'existe pas
				String nom = etudiant.getNom();
				String prenom = etudiant.getPrenom();
				Filiere filiere = etudiant.getFiliere();
				int idFiliere = -1, age = etudiant.getAge();

				Statement st, st2;
				try {
					Connection connexion = this.connection();
					st2 = connexion.createStatement();

					// Trouver l'id de la filiere
					String requete2 = "select * from filiere where nom='" + filiere + "';";
					ResultSet resultat = st2.executeQuery(requete2);
					while (resultat.next()) {
						idFiliere = resultat.getInt(1);
					}

//				String requete = "insert into etudiant values ( '" + codeEtudiant + "', '" + nom + "', '" + prenom + "', "+ idFiliere + ", " + age + " );";
					String requete = "insert into etudiant values (?, ?, ?, ?, ?);";
					PreparedStatement pst = connexion.prepareStatement(requete);
					pst.setString(1, codeEtudiant);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setInt(4, idFiliere);
					pst.setInt(5, age);

					pst.executeUpdate();
					AlertBox.display("L'etudiant " + nom + " " + prenom + " est ajouté");
				} catch (SQLException e) {

					e.printStackTrace();
					System.out.println("Connection error");
				}
			} else
				System.out.println("L'etudiant deja existe");
		} else {
			System.out.println("code etudiant est null");
		}

	}

	public void supprimerEtudiant(Etudiant etudiant) {
		String codeEtudiant = etudiant.getCodeStudent();

		if (this.rechercherEtudiantParNum(codeEtudiant) != null) {
			Statement st;
			try {
				Connection connexion = this.connection();
				String requete = "delete from etudiant where codeEtudiant=?";
				PreparedStatement pst = connexion.prepareStatement(requete);
				pst.setString(1, codeEtudiant);
				pst.executeUpdate();
				AlertBox.display("L'etudiant est supprimé");
			} catch (SQLException e) {

				e.printStackTrace();
				System.out.println("Connection error");

			}
		}

	}

	public Etudiant rechercherEtudiantParNum(String codeEtudiant) {
		Etudiant etudiant = null;
		for (Etudiant e : this.listEtudiant()) {
			if (e.getCodeStudent().equals(codeEtudiant)) {
				etudiant = e;
			}
		}
		return etudiant;
	}

	/// SUT PROF

	public void ajouterProf(Professeur prof) {
		if (prof != null) {
			int codeProf = prof.getCode();
			if (this.rechercherProfParNum(codeProf) == null) {
				// prof n'existe pas
				String nom = prof.getNom();
				String prenom = prof.getPrenom();
				int idClasse = prof.getClasse().getClasseId();

				Statement st, st2;
				try {
					Connection connexion = this.connection();

					String requete = "insert into prof values (?, ?, ?, ?)";
					PreparedStatement pst = connexion.prepareStatement(requete);
					pst.setInt(1, codeProf);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setInt(4, idClasse);
					pst.executeUpdate();
					AlertBox.display("le prof " + nom + " " + prenom + " est ajouté");
				} catch (SQLException e) {

					e.printStackTrace();
					System.out.println("connection error");
				}
			} else {
				AlertBox.display("Le prof deja existe");
			}
		}

		else {
			System.out.println("prof is null");
		}
	}

	public void supprimerProf(Professeur prof) {
		int codeProf = prof.getCode();

		if (this.rechercherProfParNum(codeProf) != null) {
			try {
				Connection connexion = this.connection();
				String requete = "delete from prof where codeprof=?";
				PreparedStatement pst = connexion.prepareStatement(requete);
				pst.setInt(1, codeProf);
				pst.executeUpdate();
				AlertBox.display("Le prof est supprime");
			} catch (SQLException e) {

				e.printStackTrace();
				System.out.println("Connection error");

			}
		} else {
			AlertBox.display("Le prof n'existe pas");
		}

	}

	public Professeur rechercherProfParNum(int codeProf) {
		Professeur prof = null;
		for (Professeur p : this.listProfesseur()) {
			if (p.getCode() == codeProf) {
				prof = p;
			}
		}
		return prof;
	}

	// sur classes

	public Classe rechercherClasseParNum(int idClasse) {
		Classe classe = null;
		for (Classe c : this.listClasse()) {
			if (c.getClasseId() == idClasse)
				classe = c;
		}
		return classe;
	}

}