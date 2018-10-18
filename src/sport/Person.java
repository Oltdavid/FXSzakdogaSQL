package sport;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty anyjaNeve;
    private final SimpleStringProperty lakcim;
    private final SimpleStringProperty tajszam;
    private final SimpleStringProperty szido;
    private final SimpleStringProperty telefon;
    private final SimpleStringProperty id;

    public Person() {
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.anyjaNeve = new SimpleStringProperty("");
        this.lakcim = new SimpleStringProperty("");
        this.tajszam = new SimpleStringProperty("");
        this.szido = new SimpleStringProperty("");
        this.telefon = new SimpleStringProperty("");
        this.id = new SimpleStringProperty("");
    }

    public Person(String lName, String fName, String email, String aNeve, String lakcim, String tajszam, String Szido, String Telefon) {
        this.lastName = new SimpleStringProperty(lName);
        this.firstName = new SimpleStringProperty(fName);
        this.email = new SimpleStringProperty(email);
        this.anyjaNeve = new SimpleStringProperty(aNeve);
        this.lakcim = new SimpleStringProperty(lakcim);
        this.tajszam = new SimpleStringProperty(tajszam);
        this.szido = new SimpleStringProperty(Szido);
        this.telefon = new SimpleStringProperty(Telefon);
        this.id = new SimpleStringProperty("");
    }

    public Person(Integer id, String lName, String fName, String email, String aNeve, String lakcim, String tajszam, String Szido, String Telefon) {
        this.lastName = new SimpleStringProperty(lName);
        this.firstName = new SimpleStringProperty(fName);
        this.email = new SimpleStringProperty(email);
        this.anyjaNeve = new SimpleStringProperty(aNeve);
        this.lakcim = new SimpleStringProperty(lakcim);
        this.tajszam = new SimpleStringProperty(tajszam);
        this.szido = new SimpleStringProperty(Szido);
        this.telefon = new SimpleStringProperty(Telefon);
        this.id = new SimpleStringProperty(String.valueOf(id));
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lName) {
        lastName.set(lName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String Email) {
        email.set(Email);
    }

    public String getAnyjaNeve() {
        return anyjaNeve.get();
    }

    public void setAnyjaNeve(String aNeve) {
        anyjaNeve.set(aNeve);
    }

    public String getLakcim() {
        return lakcim.get();
    }

    public void setLakcim(String Lakcim) {
        lakcim.set(Lakcim);
    }

    public String getTajszam() {
        return tajszam.get();
    }

    public void setTajszam(String Tajszam) {
        tajszam.set(Tajszam);
    }

    public String getSzido() {
        return szido.get();
    }

    public void setSzido(String Szido) {
        szido.set(Szido);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String Telefon) {
        telefon.set(Telefon);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String fId) {
        id.set(fId);
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", anyjaNeve=" + anyjaNeve + ", lakcim=" + lakcim + ", tajszam=" + tajszam + ", szido=" + szido + ", telefon=" + telefon + ", id=" + id + '}';
    }

}
