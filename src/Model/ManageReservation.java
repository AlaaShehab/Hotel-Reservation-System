import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ManageReservation {
    public boolean addReservation(Reservation reservation) throws SQLException, ParseException {
        Statement stat = SQLConnection.getInstance().getConnection().createStatement();
        String query = "SET FOREIGN_KEY_CHECKS=0;";
        stat.executeUpdate(query);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date checkINdate = sdf.parse(reservation.getCheckINDate());
        java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
        java.util.Date checkOUTdate = sdf.parse(reservation.getCheckOUTDate());
        java.sql.Date sqlDateOUT = new Date(checkOUTdate.getTime());
        PreparedStatement addReservation = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Reservation VALUES (?,?,?,?,?,?,?,?);");
        addReservation.setInt(1, reservation.getReservationID());
        addReservation.setInt(2, reservation.getHotelID());
        addReservation.setInt(3, reservation.getBranchID());
        addReservation.setInt(4, reservation.getRoomNO());
        addReservation.setInt(5, reservation.getUserID());
        addReservation.setDate(6,sqlDateIN);
        addReservation.setDate(7,sqlDateOUT);
        addReservation.setBoolean(8, reservation.isPaid());
        if(addReservation.executeUpdate() == -1){
            return false;
        }
        return true;
    }
    public boolean cancelReservation(int reservationID){
        try {
            Statement stat = SQLConnection.getInstance().getConnection().createStatement();
            String query = "DELETE FROM Reservation WHERE Reservation_ID = "+reservationID+";";
            if(stat.executeUpdate(query) == -1){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    public boolean editReservationInfo(Reservation reservation) throws SQLException {
        PreparedStatement checkReservation = SQLConnection.getInstance().getConnection().prepareStatement(
                "SELECT * FROM Reservation WHERE Reservation_ID = ?;");
        checkReservation.setInt(1, reservation.getReservationID());
        ResultSet rs = checkReservation.executeQuery();
        if (rs.next()) {
            PreparedStatement editReservation = SQLConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Reservation SET Room_No = ?, Check_IN = ?, Check_OUT = ?, Paid = ? WHERE Reservation_ID = ?;");
            editReservation.setInt(1, reservation.getRoomNO());
            editReservation.setString(2, reservation.getCheckINDate());
            editReservation.setString(3, reservation.getCheckOUTDate());
            editReservation.setBoolean(4, reservation.isPaid());
            editReservation.setInt(5, reservation.getReservationID());
            if (!editReservation.execute()) {
                return true;
            }
        }
        return false;
    }
    public boolean checkRefund(int reservationID) throws ParseException {
        ReservationSearch reservationSearch =new ReservationSearch();
        RoomSearch roomSearch = new RoomSearch();
        Reservation reservation = reservationSearch.getReservationInfo(reservationID);
        Room room = roomSearch.getRoomByReservationID(reservationID);
        LocalDate currentDate = LocalDate.now();
        java.sql.Date sqlCurrDate= java.sql.Date.valueOf(currentDate);
        String checkINDate = reservation.getCheckINDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date checkINdate = sdf.parse(checkINDate);
        java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
        if(sqlDateIN.after(sqlCurrDate) && reservation.isPaid()){
            return true;
        }
        return false;
    }
}