import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ManageUser {
    public boolean addUser(User user) throws SQLException {
        PreparedStatement addUser = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO User (First_Name, Last_Name, PhoneNo, Email, password, salt)" +
                        " VALUES (?,?,?,?,?,?);");
        addUser.setString(1, user.getFirstName());
        addUser.setString(2, user.getLastName());
        addUser.setString(3, user.getPhoneNumber());
        addUser.setString(4, user.getEmail());
        //encrypt password
        String salt = PasswordUtils.getSalt(30);
        String securePassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
        addUser.setString(5, securePassword);
        addUser.setString(6, salt);
        if(addUser.executeUpdate() == -1){
            return false;
        }
        return true;
    }

    public User signIN(String email, String password) throws SQLException {
        PreparedStatement checkUser = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM User WHERE Email = ?;");
        checkUser.setString(1, email);
        ResultSet rs = checkUser.executeQuery();
        if (rs.next()) {
            boolean passwordMatch = PasswordUtils.verifyUserPassword(password, rs.getString("password"), rs.getString("salt"));
            if (passwordMatch) {
                User user = constructUser(rs);
                return user;
            }
        }
        return null;
    }

    public boolean editUserInfo(User user) throws SQLException {
        PreparedStatement checkUser = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM User WHERE Email = ?;");
        checkUser.setString(1, user.getEmail());
        ResultSet rs = checkUser.executeQuery();
        if (rs.next()) {
            PreparedStatement editUser = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE User SET First_Name = ?, Last_Name = ?, PhoneNo = ?," +
                            "password = ?, salt = ? WHERE Email = ?;");
            editUser.setString(1, user.getFirstName());
            editUser.setString(2, user.getLastName());
            editUser.setString(3, user.getPhoneNumber());
            String salt = PasswordUtils.getSalt(30);
            String securePassword = PasswordUtils.generateSecurePassword(user.getPassword(), salt);
            editUser.setString(4, securePassword);
            editUser.setString(5, salt);
            editUser.setString(6, user.getEmail());
            if (!editUser.execute()) {
                return true;
            }
        }
        return false;
    }

    public User getUserByID(int userID){
        String query = "SELECT * FROM User " +
                "WHERE User_ID = "+ userID + ";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            User user = constructUser(rs);
            return user;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public User getUserByEmail(String userEmail){
        String query = "SELECT * FROM User " +
                "WHERE Email = '"+ userEmail + "';";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            User user = constructUser(rs);
            return user;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Room> userRoomFiltration(Filter filter) throws ParseException {
        String query = "SELECT * FROM Room where (Room_No,Branch_ID,Hotel_ID)" +
                " NOT IN (SELECT Room_No,Branch_ID,Hotel_ID FROM Room natural join reservation";
        if(!filter.getCheckINdate().isEmpty() && !filter.getCheckOUTdate().isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date checkINdate = sdf.parse(filter.getCheckINdate());
            java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
            java.util.Date checkOUTdate = sdf.parse(filter.getCheckOUTdate());
            java.sql.Date sqlDateOUT = new Date(checkOUTdate.getTime());
            if(sqlDateIN.after(sqlDateOUT)){
                System.out.println("Enter Dates Correctly!");
                return null;
            }
            query += " where ((Check_IN between '"+filter.getCheckINdate() +"' and '"+filter.getCheckOUTdate()
                    +"') or (Check_OUT between Check_IN and '"+filter.getCheckOUTdate()+"')))";
        }else{
            query += ")";
        }
        if (filter.getMinPrice() != -1 && filter.getMaxPrice()==-1)
            query += " and Price BETWEEN "+filter.getMinPrice()+" AND "+filter.getMaxPrice();
        if(filter.getBedsNO() != -1)
            query += " and Beds_No="+filter.getBedsNO();
        if (filter.getBathRoomNO() != -1)
            query += " and BathRooms_No="+filter.getBathRoomNO();
        if (!filter.getRoomType().isEmpty())
            query += " and Type='"+filter.getRoomType()+"'";
        if (!filter.getRoomView().isEmpty())
            query += " and View='"+filter.getRoomView()+"'";
        if (!filter.getHotelName().isEmpty())
            query += " and Hotel_ID IN (SELECT Hotel_ID FROM hotel where hotel_name = '"+filter.getHotelName()+"')";
        if (!filter.getCity().isEmpty() && !filter.getCountry().isEmpty())
            query += " and (Hotel_ID,Branch_ID) IN (SELECT Hotel_ID,Branch_ID FROM location where city = '"+filter.getCity()
                    +"' and country='"+filter.getCountry()+"')";
        query += ";";
        ArrayList<Room> rooms = new ArrayList<>();
        RoomSearch roomSearch = new RoomSearch();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                rooms.add(roomSearch.constructRoom(rs, false));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }

    public ArrayList<Reservation> getUserReservations(String userEmail){
        ReservationSearch reservationSearch = new ReservationSearch();
        String query = "SELECT * FROM Reservation NATURAL JOIN User " +
                "WHERE Email='"+userEmail+"';";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(reservationSearch.constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }

    private User constructUser(ResultSet rs){
        User user = new User();
        try {
            user.setUserID(Integer.parseInt(rs.getString("User_ID")));
            user.setFirstName(rs.getString("First_Name"));
            user.setLastName(rs.getString("Last_Name"));
            user.setPhoneNumber(rs.getString("PhoneNo"));
            user.setEmail(rs.getString("Email"));
            return user;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
