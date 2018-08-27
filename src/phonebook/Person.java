
package phonebook;

import javafx.beans.property.SimpleStringProperty;


public class Person {
    
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty anyjaNeve;
    private final SimpleStringProperty lakcim;
    private final SimpleStringProperty id;
    
    public Person() {
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.anyjaNeve = new SimpleStringProperty("");
        this.lakcim = new SimpleStringProperty("");
        this.id = new SimpleStringProperty("");
    }
 
    public Person(String lName, String fName, String email,String aNeve, String lakcim ) {
        this.lastName = new SimpleStringProperty(lName);
        this.firstName = new SimpleStringProperty(fName);
        this.email = new SimpleStringProperty(email);
        this.anyjaNeve = new SimpleStringProperty(aNeve);
        this.lakcim = new SimpleStringProperty(lakcim);
        this.id = new SimpleStringProperty("");
    }
    
    public Person(Integer id, String lName, String fName, String email,String aNeve, String lakcim ) {
        this.lastName = new SimpleStringProperty(lName);
        this.firstName = new SimpleStringProperty(fName);
        this.email = new SimpleStringProperty(email);
        this.anyjaNeve = new SimpleStringProperty(aNeve);
        this.lakcim = new SimpleStringProperty(lakcim);
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
    public void setLastName(String fName) {
        lastName.set(fName);
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

    public String getId(){
        return id.get();
    }
    
    public void setId(String fId){
        id.set(fId);
    }
    
}
