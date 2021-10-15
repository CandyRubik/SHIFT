package rubik.shifttest.data.storage.models;

public class User {
    private String firstName;
    private String lastName;
    private String bornDate;
    private String password;

    public User(String firstName, String lastName, String bornDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public String getPassword() {
        return password;
    }
}
