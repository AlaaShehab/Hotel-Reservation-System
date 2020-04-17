public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String startDate;
    private int managerID = -1;
    private int hotelID;
    private int branchID;
    private String password;
    private String email;
    private String address;
    private boolean isManager;

    public void setEmployeeID(int id) {
        employeeID = id;
    }
    public int getEmployeeID() {
        return employeeID;
    }
    public void setFirstName(String name) {
        firstName = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setPhoneNo(String phone) {
        phoneNumber = phone;
    }
    public String getPhoneNo() {
        return phoneNumber;
    }
    public void setStartDate(String date) {
        startDate = date;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setManagerID(int id) {
        managerID = id;
    }
    public int getManagerID() {
        return managerID;
    }
    public void setHotelID(int id) {
        hotelID = id;
    }
    public int getHotelID() {
        return hotelID;
    }
    public void setBranchID(int id) {
        branchID = id;
    }
    public int getBranchID() {
        return branchID;
    }
    public void setPassword(String pass){
        password = pass;
    }
    public String getPassword() {
        return password;
    }

    public void setEmployeeIsManager (boolean isManager) {
        this.isManager = isManager;
    }
    public boolean isManager () {
        return isManager;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID='" + employeeID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", address=" + address +
                ", startDate=" + startDate +
                ", managerID=" + managerID +
                ", hotelID=" + hotelID +
                ", branchID=" + branchID +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}