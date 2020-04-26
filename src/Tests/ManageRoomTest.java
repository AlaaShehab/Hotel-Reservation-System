import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManageRoomTest {
    ManageRoom manageRoom = new ManageRoom();
    @Test
    public void addRoom() throws SQLException {
        Room room = new Room();
        room.setHotelID(1);
        room.setBranchID(1);
        room.setRoomNO(3);
        room.setFloorNO(2);
        room.setBathRoomNO(3);
        room.setBedsNO(4);
        room.setRoomView("sea");
        room.setRoomType("Suite");
        room.setPrice(7000);
        assertTrue(manageRoom.addRoom(room));
    }
}