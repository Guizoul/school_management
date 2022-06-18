package projectClasses;


public class Personne {

    private String firstName;
    private String lastName;

    Personne() {

    }

    Personne(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String s) {
        this.firstName = s;
    }

    public void setLastName(String s) {
        this.lastName = s;
    }

    String getNom() {
        return this.firstName;
    }

    String getPrenom() {
        return this.lastName;
    }

}

