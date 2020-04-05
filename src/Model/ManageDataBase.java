package Model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ManageDataBase {

    public boolean addBranch(Branch branch) throws SQLException {
        PreparedStatement addBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Branch VALUES (?,?,?,?,?,?,?,?,?,?);");
        addBranch.setInt(1, branch.getBranchID());
        addBranch.setInt(2, branch.getHotelID());
        addBranch.setInt(3, branch.getManagerID());
        addBranch.setFloat(4, branch.getRating());
        addBranch.setBoolean(5, branch.hasPool());
        addBranch.setBoolean(6, branch.hasGym());
        addBranch.setBoolean(7, branch.hasSpa());
        addBranch.setBoolean(8, branch.hasCarRental());
        addBranch.setBoolean(9, branch.hasBeach());
        addBranch.setBoolean(10, branch.hasGarage());
        if(addBranch.executeUpdate() == -1){
            return false;
        }
        return true;
    }
    public boolean addEmployee(Employee employee) throws SQLException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(employee.getStartDate());
        java.sql.Date sqlDate = new Date(date.getTime());
        PreparedStatement addBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?,?);");
        addBranch.setString(1, employee.getFirstName());
        addBranch.setString(2, employee.getLastName());
        addBranch.setString(3, employee.getPhoneNo());
        addBranch.setString(4, employee.getAddress());
        addBranch.setDate(5,sqlDate);
        if (employee.getManagerID() != -1) {
            addBranch.setInt(6, employee.getManagerID());
        } else {
            addBranch.setString(6, null);
        }
        addBranch.setInt(7, employee.getHotelID());
        addBranch.setInt(8, employee.getBranchID());
        addBranch.setString(9, employee.getEmail());
        addBranch.setInt(10, employee.getBranchID());
        addBranch.setString(11, employee.getPassword());
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
            branch.setHotelName(rs.getString("Hotel_Name"));
            return branch;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public boolean editBranchInfo(Branch branch) throws SQLException {
        PreparedStatement checkBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Branch WHERE Branch_ID = ? and Hotel_ID = ?;");
        checkBranch.setInt(1, branch.getBranchID());
        checkBranch.setInt(2, branch.getHotelID());
        ResultSet rs = checkBranch.executeQuery();
        if (rs.next()) {
            PreparedStatement editBranch = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Branch SET MGR_ID = ?, Rating = ?, Pool = ?, GYM = ?, SPA = ?," +
                            " Car_rental = ?, Beach = ?, Garage = ?  WHERE Branch_ID = ? and Hotel_ID = ?;");
            editBranch.setInt(1, branch.getManagerID());
            editBranch.setFloat(2, branch.getRating());
            editBranch.setBoolean(3, branch.hasPool());
            editBranch.setBoolean(4, branch.hasGym());
            editBranch.setBoolean(5, branch.hasSpa());
            editBranch.setBoolean(6, branch.hasCarRental());
            editBranch.setBoolean(7, branch.hasBeach());
            editBranch.setBoolean(8, branch.hasGarage());
            editBranch.setInt(9, branch.getBranchID());
            editBranch.setInt(10, branch.getHotelID());
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
        checkEmployee.setInt(1, employee.getEmployeeID());
        ResultSet rs = checkEmployee.executeQuery();
        if (rs.next()) {
            PreparedStatement editEmployee = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Employee SET First_Name = ?, Last_Name = ?, PhoneNo = ?," +
                            "Address = ?, Email = ?, password = ?;");
            editEmployee.setString(1, employee.getFirstName());
            editEmployee.setString(2, employee.getLastName());
            editEmployee.setString(3, employee.getPhoneNo());
            editEmployee.setString(4, employee.getAddress());
            editEmployee.setString(5, employee.getEmail());
            editEmployee.setString(6, employee.getPassword());

            if (editEmployee.execute()) {
                return true;
            }
        }
        return false;
    }
    public Employee signIN(String email, String password) throws SQLException {
        PreparedStatement checkEmployee = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Employee WHERE Email = ?;");
        checkEmployee.setString(1, email);
        ResultSet rs = checkEmployee.executeQuery();
        if (rs.next()) {
            // encrypt password
            if (password.equals(rs.getString("Emp_Password"))) {
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
            branch.setBranchID(rs.getInt("Branch_ID"));
            branch.setHotelID(rs.getInt("Hotel_ID"));
            branch.setManagerID(rs.getInt("MGR_ID"));
            branch.setRating(rs.getFloat("Rating"));
            branch.setBeach(Boolean.parseBoolean(rs.getString("Beach")));
            branch.setCarRental(rs.getBoolean("Car_Rental"));
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
            employee.setEmployeeID(Integer.parseInt(rs.getString("Emp_ID")));
            employee.setFirstName(rs.getString("First_Name"));
            employee.setLastName(rs.getString("Last_Name"));
            employee.setEmail(rs.getString("Email"));
            employee.setPhoneNo(rs.getString("PhoneNo"));
            employee.setStartDate(rs.getString("Start_Date"));
            employee.setManagerID(rs.getInt("MGR_ID"));
            employee.setHotelID(rs.getInt("Hotel_ID"));
            employee.setBranchID(rs.getInt("Branch_ID"));
            employee.setPassword(rs.getString("Emp_Password"));

            if (employee.getEmployeeID() == employee.getManagerID()) {
                employee.setEmployeeIsManager(true);
            }
            return employee;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

}
