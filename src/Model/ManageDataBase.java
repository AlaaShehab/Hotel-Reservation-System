import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ManageDataBase {

    public boolean addBranch(Branch branch) throws SQLException {
        PreparedStatement addBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Branch VALUES (?,?,?,?,?,?,?,?,?,?);");
        addBranch.setInt(1, branch.get_Branch_ID());
        addBranch.setInt(2, branch.get_Hotel_ID());
        addBranch.setInt(3, branch.get_MGR_ID());
        addBranch.setFloat(4, branch.get_Rating());
        addBranch.setBoolean(5, branch.hasPool());
        addBranch.setBoolean(6, branch.hasGym());
        addBranch.setBoolean(7, branch.hasSpa());
        addBranch.setBoolean(8, branch.hasCar_rental());
        addBranch.setBoolean(9, branch.hasBeach());
        addBranch.setBoolean(10, branch.hasGarage());
        if(addBranch.executeUpdate() == -1){
            return false;
        }
        return true;
    }
    public boolean addEmployee(Employee employee) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(employee.get_Start_Date());
        java.sql.Date sqlDate = new Date(date.getTime());
        PreparedStatement addBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?);");
        addBranch.setInt(1, employee.get_Emp_ID());
        addBranch.setString(2, employee.get_Emp_Name());
        addBranch.setString(3, employee.get_Phone_No());
        addBranch.setDate(4,sqlDate);
        if (employee.get_MGR_ID() != -1) {
            addBranch.setInt(5, employee.get_MGR_ID());
        } else {
            addBranch.setString(5, null);
        }
        addBranch.setInt(6, employee.get_Hotel_ID());
        addBranch.setInt(7, employee.get_Branch_ID());
        addBranch.setString(8, employee.getPassword());
        if(addBranch.executeUpdate() == -1){
            return false;
        }
        return true;
    }
    public Branch getBranchInfo(int hotelID, int branchID){
        String query = "SELECT * FROM Branch " +
                "WHERE Hotel_ID = "+ hotelID +" and Branch_ID = "+
                branchID + ";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            Branch branch = constructBranch(rs);
            String query1 = "SELECT Hotel_Name FROM Hotel NATURAL JOIN Branch " +
                    "WHERE Hotel_ID = " + hotelID +";";
            rs = SQLConnection.getInstance().getData(query1);
            rs.next();
            branch.set_Hotel_Name(rs.getString("Hotel_Name"));
            return branch;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public boolean editBranchInfo(Branch branch) throws SQLException {
        PreparedStatement checkBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Branch WHERE Branch_ID = ? and Hotel_ID = ?;");
        checkBranch.setInt(1, branch.get_Branch_ID());
        checkBranch.setInt(2, branch.get_Hotel_ID());
        ResultSet rs = checkBranch.executeQuery();
        if (rs.next()) {
            PreparedStatement editBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Branch SET MGR_ID = ?, Rating = ?, Pool = ?, GYM = ?, SPA = ?," +
                            " Car_rental = ?, Beach = ?, Garage = ?  WHERE Branch_ID = ? and Hotel_ID = ?;");
            editBranch.setInt(1, branch.get_MGR_ID());
            editBranch.setFloat(2, branch.get_Rating());
            editBranch.setBoolean(3, branch.hasPool());
            editBranch.setBoolean(4, branch.hasGym());
            editBranch.setBoolean(5, branch.hasSpa());
            editBranch.setBoolean(6, branch.hasCar_rental());
            editBranch.setBoolean(7, branch.hasBeach());
            editBranch.setBoolean(8, branch.hasGarage());
            editBranch.setInt(9, branch.get_Branch_ID());
            editBranch.setInt(10, branch.get_Hotel_ID());
            if (editBranch.execute()) {
                return true;
            }
        }
        return false;
    }
    public Employee getEmployeeInfo(int empID){
        String query = "SELECT * FROM Employee " +
                "WHERE Emp_ID = "+ empID + ";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            Employee employee = constructEmployee(rs);
            return employee;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public boolean editEmployeeInfo(Employee employee) throws SQLException {
        PreparedStatement checkEmployee = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Employee WHERE Emp_ID = ?;");
        checkEmployee.setInt(1, employee.get_Emp_ID());
        ResultSet rs = checkEmployee.executeQuery();
        if (rs.next()) {
            PreparedStatement editEmployee = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Employee SET Emp_Name = ?, PhoneNo = ?," +
                            "MGR_ID = ?, Branch_ID = ?  WHERE Emp_ID = ?;");
            editEmployee.setString(1, employee.get_Emp_Name());
            editEmployee.setString(2, employee.get_Phone_No());
            if (employee.get_MGR_ID() != -1) {
                editEmployee.setInt(3, employee.get_MGR_ID());
            } else {
                editEmployee.setString(3, null);
            }
            editEmployee.setInt(4, employee.get_Branch_ID());
            editEmployee.setInt(5, employee.get_Emp_ID());
            if (editEmployee.execute()) {
                return true;
            }
        }
        return false;
    }
    public Employee signIN(int id, String password) throws SQLException {
        PreparedStatement checkEmployee = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Employee WHERE Emp_ID = ?;");
        checkEmployee.setInt(1, id);
        ResultSet rs = checkEmployee.executeQuery();
        if (rs.next()) {
            // encrypt password
            if (password.equals(rs.getString("password"))) {
                Employee employee = constructEmployee(rs);
                return employee;
            }
        }
        return null;
    }
    public boolean removeEmployee(int empID){
        try {
            Statement stat = SQLConnection.getInstance().getConnection().createStatement();
            String query = "DELETE FROM Employee WHERE Emp_ID = "+empID+";";
            if(stat.executeUpdate(query) == -1){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public boolean removeBranch(int hotelID, int branchID){
        try {
            Statement stat = SQLConnection.getInstance().getConnection().createStatement();
            String query = "DELETE FROM Branch WHERE Hotel_ID = "+ hotelID +" and Branch_ID = "+
                    branchID + ";";
            if(stat.executeUpdate(query) == -1){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Branch> getHotelBranches(int hotelID){
        String query = "SELECT * FROM Hotel NATURAL JOIN Branch " +
                "WHERE Hotel_ID = " + hotelID +";";
        ArrayList<Branch> branches = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                branches.add(constructBranch(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return branches;
    }
    public boolean addHotel(String hotelName, int hotelID)throws SQLException{
        PreparedStatement addHotel = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Hotel VALUES (?,?);");
        addHotel.setInt(1, hotelID);
        addHotel.setString(2, hotelName);
        if(addHotel.executeUpdate() == -1){
            return false;
        }
        return true;
    }
    private Branch constructBranch(ResultSet rs){
        Branch branch = new Branch();
        try {
            branch.set_Branch_ID(rs.getInt("Branch_ID"));
            branch.set_Hotel_ID(rs.getInt("Hotel_ID"));
            branch.set_MGR_ID(rs.getInt("MGR_ID"));
            branch.set_Rating(rs.getFloat("Rating"));
            branch.setBeach(Boolean.parseBoolean(rs.getString("Beach")));
            branch.setCar_rental(rs.getBoolean("Car_Rental"));
            branch.setGarage(rs.getBoolean("Garage"));
            branch.setGym(rs.getBoolean("GYM"));
            branch.setPool(rs.getBoolean("POOL"));
            branch.setSpa(rs.getBoolean("SPA"));
            return branch;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    private Employee constructEmployee(ResultSet rs){
        Employee employee = new Employee();
        try {
            employee.set_Emp_ID(Integer.parseInt(rs.getString("Emp_ID")));
            employee.set_Emp_Name(rs.getString("Emp_Name"));
            employee.set_Phone_No(rs.getString("PhoneNo"));
            employee.set_Start_Date(rs.getString("Start_Date"));
            employee.set_MGR_ID(rs.getInt("MGR_ID"));
            employee.set_Hotel_ID(rs.getInt("Hotel_ID"));
            employee.set_Branch_ID(rs.getInt("Branch_ID"));
            employee.setPassword(rs.getString("password"));
            return employee;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

}
