package Model;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int numberOfReservs;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfReservs() {
        return numberOfReservs;
    }

    public void setNumberOfReservs(int numberOfReservs) {
        this.numberOfReservs = numberOfReservs;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userID='" + userID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", numberOfReservs=" + numberOfReservs +
                '}';
    }
}