import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ManageUserTest {
    ManageUser manageUser = new ManageUser();
    @Test
    public void addUser() throws SQLException {
        User user = new User();
        user.setFirstName("Bassant");
        user.setLastName("Ahmed");
        user.setEmail("BAhmed@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("1234");
        assertTrue(manageUser.addUser(user));
    }

    @Test
    public void signIN() throws SQLException {
        User user = new User();
        user.setFirstName("Alaa");
        user.setLastName("Samir");
        user.setEmail("ASamir@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("4567");
        user.setUserID(5);
        manageUser.addUser(user);
        User result = manageUser.signIN(user.getEmail(), user.getPassword());
        assertEquals(result.toString(), user.toString());
    }

    @Test
    public void editUserInfo() throws SQLException {
        User user = new User();
        user.setFirstName("Aya");
        user.setLastName("Ashraf");
        user.setEmail("AAshraf@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("4567");
        user.setUserID(6);
        manageUser.addUser(user);
        //edit info
        user.setPassword("1234");
        user.setLastName("Osman");
        assertTrue(manageUser.editUserInfo(user));
    }

    @Test
    public void getUserByID() throws SQLException {
        User user = new User();
        user.setFirstName("Amira");
        user.setLastName("Ali");
        user.setEmail("Ali@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("4567");
        user.setUserID(7);
        manageUser.addUser(user);
        User result = manageUser.getUserByID(7);
        assertEquals(result.toString(), user.toString());
    }

    @Test
    public void getUserByEmail() throws SQLException {
        User user = new User();
        user.setFirstName("Islam");
        user.setLastName("Samir");
        user.setEmail("Islam@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setPassword("4567");
        user.setUserID(9);
        manageUser.addUser(user);
        User result = manageUser.getUserByEmail("Islam@gmail.com");
        assertEquals(result.toString(), user.toString());
    }

    @Test
    public void userRoomFiltration() throws ParseException {
        Filter filter = new Filter();
        filter.setRoomView("sea");
        filter.setMinPrice(900);
        filter.setMaxPrice(1500);
        ArrayList<Room> rooms = manageUser.userRoomFiltration(filter);
        for (Room ro:
                rooms) {
            assertEquals(ro.getRoomNO(), 3);
        }
    }

    @Test
    public void getUserReservations() {
        ArrayList<Reservation> reservations = manageUser.getUserReservations("BAhmed@gmail.com");
        for (Reservation reservation: reservations){
            assertEquals(2, reservation.getUserID());
        }
    }
}