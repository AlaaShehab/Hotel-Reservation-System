import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationSearchTest {
    ManageReservation manageReservation = new ManageReservation();
    ReservationSearch reservationSearch = new ReservationSearch();

    @Test
    public void getReservationsOfToday() {
        ArrayList<Reservation> reservations = reservationSearch.getReservationsOfToday();
        for(Reservation res: reservations){
            assertEquals(3, res.getResrvationID());
        }
    }

    @Test
    public void getPaidReservations() {
        ArrayList<Reservation> reservations = reservationSearch.getPaidReservations();
        for(Reservation res: reservations){
            assertEquals(1, res.getResrvationID());
        }
    }

    @Test
    public void getReservationInfo() throws SQLException, ParseException {
        Reservation reservation = new Reservation();
        reservation.setResrvationID(3);
        reservation.setHotelID(1);
        reservation.setBranchID(1);
        reservation.setRoomNO(1);
        reservation.setUserID(1);
        reservation.setCheckINDate("2020-07-20");
        reservation.setCheckOUTDate("2020-07-29");
        reservation.setPaid(true);
        manageReservation.addReservation(reservation);
        Reservation result = reservationSearch.getReservationInfo(3);
        assertEquals(result.toString(), reservation.toString());
    }

    @Test
    public void filterReservationsByCheckINDate() throws ParseException {
        ArrayList<Reservation> reservations = reservationSearch.getReservationsByCheckINDate("2020-08-20");
        for(Reservation res: reservations){
            assertEquals(2, res.getResrvationID());
        }
    }

    @Test
    public void filterReservations() throws ParseException {
        Reservation reservation = new Reservation();
        reservation.setHotelID(1);
        reservation.setBranchID(1);
        reservation.setUserEmail("samirhanan32@gmail.com");
        reservation.setUserFN("bassant");
        reservation.setUserLN("ahmed");;
        reservation.setPhoneNO("0120288702");
        ArrayList<Reservation> reservations = reservationSearch.filterReservations(reservation);
        for(Reservation res: reservations){
            assertEquals(1, res.getResrvationID());
        }
    }
}