import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class ManageReservationTest {
    ManageReservation manageReservation = new ManageReservation();

    @Test
    public void addReservation() throws SQLException, ParseException {
        Reservation reservation = new Reservation();
        reservation.setReservationID(3);
        reservation.setHotelID(1);
        reservation.setBranchID(1);
        reservation.setRoomNO(1);
        reservation.setUserID(1);
        reservation.setCheckINDate("2020-07-20");
        reservation.setCheckOUTDate("2020-07-29");
        reservation.setPaid(true);
        assertTrue(manageReservation.addReservation(reservation));
    }

    @Test
    public void cancelReservation() {
        assertTrue(manageReservation.cancelReservation(3));
    }

    @Test
    public void editReservationInfo() throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationID(3);
        reservation.setHotelID(1);
        reservation.setBranchID(1);
        reservation.setRoomNO(2);
        reservation.setUserID(1);
        reservation.setCheckINDate("2020-07-22");
        reservation.setCheckOUTDate("2020-07-29");
        reservation.setPaid(false);
        assertTrue(manageReservation.editReservationInfo(reservation));
    }

    @Test
    public void checkRefund() throws ParseException {
        assertFalse(manageReservation.checkRefund(3));
    }
}