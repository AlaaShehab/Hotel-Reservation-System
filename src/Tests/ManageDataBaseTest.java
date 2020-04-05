import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ManageDataBaseTest {
    private ManageDataBase mdb = new ManageDataBase();

    @Test
    void addBranch() throws SQLException {
        Branch branch = new Branch();
        branch.setBranchID(3);
        branch.setHotelID(1);
        branch.setManagerID(2);
        branch.setRating(4);
        branch.setBeach(true);
        branch.setCarRental(true);
        branch.setGarage(true);
        branch.setGym(false);
        branch.setPool(false);
        branch.setSpa(false);
        assertTrue(mdb.addBranch(branch));
    }

    @Test
    void addEmployee() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("22232");
        employee.setStartDate("2020-06-14");
        employee.setPhoneNo("012");
        employee.setHotelID(1);
        employee.setBranchID(1);
        employee.setEmployeeID(7);
        employee.setFirstName("Hanan");
        employee.setLastName("Samir");
        employee.setEmail("Samir@gmail.com");
        employee.setAddress("Alexandria");
        assertTrue(mdb.addEmployee(employee));
    }

    @Test
    void getBranchInfo() {
        Branch branch = new Branch();
        branch.setBranchID(3);
        branch.setHotelID(1);
        branch.setManagerID(2);
        branch.setHotelName("tolip");
        branch.setRating(4);
        branch.setBeach(true);
        branch.setCarRental(true);
        branch.setGarage(true);
        branch.setGym(false);
        branch.setPool(false);
        branch.setSpa(false);
        Branch result = mdb.getBranchInfo(branch.getHotelID(), branch.getBranchID());
        assertEquals(result.toString(), branch.toString());
    }

    @Test
    void editBranchInfo() throws SQLException {
        Branch branch = new Branch();
        branch.setBranchID(6);
        branch.setHotelID(1);
        branch.setManagerID(2);
        branch.setHotelName("san stefano");
        branch.setRating(4);
        branch.setBeach(true);
        branch.setCarRental(true);
        branch.setGarage(true);
        branch.setGym(false);
        branch.setPool(false);
        branch.setSpa(false);
        mdb.addBranch(branch);
        branch.setRating(2);
        branch.setManagerID(3);
        branch.setBeach(false);
        branch.setCarRental(false);
        branch.setGarage(false);
        branch.setGym(true);
        branch.setPool(true);
        branch.setSpa(true);
        assertTrue(mdb.editBranchInfo(branch));
    }

    @Test
    void getEmployeeInfo() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("12345");
        employee.setStartDate("2003-03-01");
        employee.setPhoneNo("01165935672");
        employee.setHotelID(1);
        employee.setBranchID(1);
        employee.setEmployeeID(4);
        employee.setFirstName("Alaa");
        employee.setLastName("Shehab");
        employee.setEmail("Alaa@gmail.com");
        employee.setAddress("Alexandria");
        mdb.addEmployee(employee);
        Employee result = mdb.getEmployeeInfo(4);
        assertEquals(result.toString(), employee.toString());
    }

    @Test
    void editEmployeeInfo() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("1");
        employee.setStartDate("2000-06-14");
        employee.setPhoneNo("011");
        employee.setHotelID(1);
        employee.setBranchID(1);
        employee.setEmployeeID(12);
        employee.setFirstName("Hanan");
        employee.setLastName("Ali");
        employee.setEmail("amira@gmail.com");
        employee.setAddress("cairo");
        mdb.addEmployee(employee);
        //edit info
        employee.setPassword("13343");
        employee.setPhoneNo("0112");
        employee.setFirstName("amira");
        employee.setLastName("ahmed");
        employee.setAddress("Alex");
        assertTrue(mdb.editEmployeeInfo(employee));
    }

    @Test
    void signIN() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("6573925");
        employee.setStartDate("2004-05-22");
        employee.setPhoneNo("01200062378");
        employee.setHotelID(1);
        employee.setBranchID(2);
        employee.setEmployeeID(6);
        employee.setFirstName("Salem");
        employee.setLastName("Mohamed");
        employee.setEmail("Salem@gmail.com");
        employee.setAddress("Alexandria");
        mdb.addEmployee(employee);
        Employee result = mdb.signIN(employee.getEmail(), employee.getPassword());
        assertEquals(result.toString(), employee.toString());
    }

    @Test
    void removeEmployee() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("22232");
        employee.setStartDate("2000-06-14");
        employee.setPhoneNo("01259234900");
        employee.setHotelID(1);
        employee.setBranchID(1);
        employee.setEmployeeID(5);
        employee.setFirstName("Ahmed");
        employee.setLastName("Ali");
        employee.setEmail("AhmedAli@gmail.com");
        employee.setAddress("Alexandria");
        mdb.addEmployee(employee);
        assertTrue(mdb.removeEmployee(5));
    }

    @Test
    void removeBranch() throws SQLException {
        Branch branch = new Branch();
        branch.setBranchID(5);
        branch.setHotelID(1);
        branch.setManagerID(2);
        branch.setRating(4);
        branch.setBeach(true);
        branch.setCarRental(true);
        branch.setGarage(true);
        branch.setGym(false);
        branch.setPool(false);
        branch.setSpa(false);
        mdb.addBranch(branch);
        assertTrue(mdb.removeBranch(branch.getHotelID(), branch.getBranchID()));
    }

    @Test
    void getHotelBranches() throws SQLException {
        ArrayList<Branch> branches = createBranches();
        ArrayList<Branch> result = mdb.getHotelBranches(2);
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).toString(), branches.get(i).toString());
        }
    }

    @Test
    void getManagerBranches() throws SQLException {
        ArrayList<Branch> branches = createBranches();
        ArrayList<Branch> result = mdb.getManagerBranches(7);
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).toString(), branches.get(i).toString());
        }
    }

    @Test
    void addHotel() throws SQLException {
        int hotelID = 2;
        String hotelName = "Azur";
        assertTrue(mdb.addHotel(hotelName, hotelID));

    }

    @Test
    void changePassword() throws SQLException, ParseException {
        Employee employee = new Employee();
        employee.setPassword("2222");
        employee.setStartDate("2010-08-03");
        employee.setPhoneNo("01155782546");
        employee.setHotelID(1);
        employee.setBranchID(2);
        employee.setEmployeeID(13);
        employee.setFirstName("Hanan");
        employee.setLastName("Ali");
        employee.setEmail("HA@gmail.com");
        employee.setAddress("cairo");
        mdb.addEmployee(employee);
        employee.setPassword("13343");
        mdb.editEmployeeInfo(employee);
        Employee result = mdb.signIN(employee.getEmail(), employee.getPassword());
        assertEquals(result.toString(), employee.toString());
    }

    private ArrayList<Branch> createBranches() throws SQLException {
        Branch branch1 = new Branch();
        branch1.setBranchID(10);
        branch1.setHotelID(2);
        branch1.setManagerID(2);
        branch1.setRating(4);
        branch1.setBeach(true);
        branch1.setCarRental(true);
        branch1.setGarage(true);
        branch1.setGym(false);
        branch1.setPool(false);
        branch1.setSpa(false);
        branch1.setHotelName("Azur");

        Branch branch = new Branch();
        branch.setBranchID(12);
        branch.setHotelID(3);
        branch.setManagerID(7);
        branch.setRating(4);
        branch.setBeach(true);
        branch.setCarRental(true);
        branch.setGarage(true);
        branch.setGym(false);
        branch.setPool(false);
        branch.setSpa(false);
        branch.setHotelName("San Stefano");
        mdb.addBranch(branch);
        //mdb.addBranch(branch1);
        ArrayList<Branch> branches = new ArrayList<>();
        branches.add(branch);
        //branches.add(branch1);
        return branches;
    }

}