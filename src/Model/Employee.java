public class Employee {
    private int Emp_ID;
    private String Emp_Name;
    private String Phone_No;
    private String Start_Date;
    private int MGR_ID = -1;
    private int Hotel_ID;
    private int Branch_ID;
    private String password;

    public void set_Emp_ID (int id) {
        Emp_ID = id;
    }
    public int get_Emp_ID () {
        return Emp_ID;
    }
    public void set_Emp_Name (String name) {
        Emp_Name = name;
    }
    public String get_Emp_Name () {
        return Emp_Name;
    }
    public void set_Phone_No (String phone) {
        Phone_No = phone;
    }
    public String get_Phone_No () {
        return Phone_No;
    }
    public void set_Start_Date (String date) {
        Start_Date = date;
    }
    public String get_Start_Date () {
        return Start_Date;
    }
    public void set_MGR_ID (int id) {
        MGR_ID = id;
    }
    public int get_MGR_ID () {
        return MGR_ID;
    }
    public void set_Hotel_ID (int id) {
        Hotel_ID = id;
    }
    public int get_Hotel_ID () {
        return Hotel_ID;
    }
    public void set_Branch_ID (int id) {
        Branch_ID = id;
    }
    public int get_Branch_ID () {
        return Branch_ID;
    }
    public void setPassword(String pass){
        password = pass;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "Emp_ID='" + Emp_ID + '\'' +
                ", Emp_Name='" + Emp_Name + '\'' +
                ", Phone_No=" + Phone_No +
                ", Start_Date=" + Start_Date +
                ", MGR_ID=" + MGR_ID +
                ", Hotel_ID=" + Hotel_ID +
                ", Branch_ID=" + Branch_ID +
                '}';
    }
}
