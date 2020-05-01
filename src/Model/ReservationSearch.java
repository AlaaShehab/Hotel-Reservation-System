import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationSearch {
    public ArrayList<Reservation> getReservationsOfToday(){
        LocalDate currentDate = LocalDate.now();
        String query = "SELECT * FROM Reservation " +
                "WHERE Check_IN = '" + currentDate +"';";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public ArrayList<Reservation> getReservationsByCheckINDate(String date) throws ParseException {
        String query = "SELECT * FROM Reservation " +
                "WHERE Check_IN = '" + date +"';";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public ArrayList<Reservation> getReservationsByUserName(String firstName, String lastName){
        String query = "SELECT * FROM Reservation NATURAL JOIN User " +
                "WHERE First_Name = '" + firstName +"' AND Last_Name = '"+lastName+"';";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public ArrayList<Reservation> getReservationsByPhoneNo(String phoneNO){
        String query = "SELECT * FROM Reservation NATURAL JOIN User " +
                "WHERE PhoneNo = " + phoneNO +";";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public ArrayList<Reservation> getReservationsByRoomNo(int roomNO){
        String query = "SELECT * FROM Reservation " +
                "WHERE Room_No = " + roomNO +";";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public ArrayList<Reservation> getPaidReservations(){
        String query = "SELECT * FROM Reservation " +
                "WHERE Paid = " + true +";";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public Reservation getReservationInfo(int reservationID){
        String query = "SELECT * FROM Reservation " +
                "WHERE Reservation_ID = "+ reservationID + ";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            Reservation reservation = constructReservation(rs);
            return reservation;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public ArrayList<Reservation> filterReservations (Reservation reservation){
        String query = "SELECT * FROM Reservation NATURAL JOIN User " +
                "WHERE Hotel_ID = "+ reservation.getHotelID()+" AND Branch_ID = "
                + reservation.getBranchID();
        if (!reservation.getUserEmail().isEmpty())
            query += " AND Email='"+reservation.getUserEmail()+"'";
        if(!reservation.getUserFN().isEmpty())
            query += " AND First_Name = '" + reservation.getUserFN();
        if(!reservation.getUserLN().isEmpty())
            query += "' AND Last_Name = '"+reservation.getUserLN()+"'";
        if(!reservation.getPhoneNO().isEmpty())
            query += " AND PhoneNo = " + reservation.getPhoneNO();
        if (reservation.getRoomNO() != -1)
            query += " AND Room_No = " + reservation.getRoomNO();
        if (!reservation.getCheckINDate().isEmpty())
            query += " AND Check_IN = '" + reservation.getCheckINDate() +"'";
        query += " AND Paid = " + reservation.isPaid();
        query += ";";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                reservations.add(constructReservation(rs));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return reservations;
    }
    public Reservation constructReservation(ResultSet rs){
        Reservation reservation = new Reservation();
        try {
            reservation.setReservationID(rs.getInt("Reservation_ID"));
            reservation.setBranchID(rs.getInt("Branch_ID"));
            reservation.setHotelID(rs.getInt("Hotel_ID"));
            reservation.setUserID(rs.getInt("User_ID"));
            reservation.setRoomNO(rs.getInt("Room_No"));
            reservation.setCheckINDate(rs.getString("Check_IN"));
            reservation.setCheckOUTDate(rs.getString("Check_OUT"));
            reservation.setPaid(rs.getBoolean("Paid"));

            ManageDataBase manageDataBase = new ManageDataBase();
            Branch branch = manageDataBase.getBranchInfo(reservation.getHotelID(), reservation.getBranchID());
            reservation.setHotelName(branch.getHotelName());
            reservation.setCity(branch.getCity());
            reservation.setCountry(branch.getCountry());

            ManageUser manageUser = new ManageUser();
            User user = manageUser.getUserByID(reservation.getUserID());
            reservation.setUserEmail(user.getEmail());
            reservation.setUserFN(user.getFirstName());
            reservation.setUserLN(user.getLastName());
            reservation.setPhoneNO(user.getPhoneNumber());

            return reservation;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

}