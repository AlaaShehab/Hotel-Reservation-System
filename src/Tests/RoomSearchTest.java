import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomSearchTest {
    ManageRoom manageRoom = new ManageRoom();
    RoomSearch roomSearch = new RoomSearch();

    @Test
    public void getRoomswithTimeRange() throws ParseException {
        ArrayList<Room> rooms = roomSearch.getRoomswithTimeRange(1,1,"2020-08-20","2020-08-29");
        for(Room room:rooms){
            assertEquals(1, room.getRoomNO());
        }
    }

    @Test
    public void getRoomswithPriceRange() {
        ArrayList<Room> rooms = roomSearch.getRoomswithPriceRange(1,1,0,2000);
        for(Room room:rooms){
            assertEquals(1, room.getRoomNO());
        }
    }

    @Test
    public void getRoomByReservationID() {
        Room room = roomSearch.getRoomByReservationID(1);
        assertEquals(1, room.getRoomNO());
    }

    @Test
    public void getAvailableRooms() throws ParseException {
        ArrayList<Room> rooms = roomSearch.getAvailableRooms(1,1,"2020-07-20",5);
        for(Room room:rooms){
            assertEquals(2, room.getRoomNO());
        }
    }

    @Test
    public void getRoomPrice() throws SQLException {
        Room room = new Room();
        room.setHotelID(1);
        room.setBranchID(1);
        room.setRoomNO(4);
        room.setFloorNO(2);
        room.setBathRoomNO(3);
        room.setBedsNO(4);
        room.setRoomView("sea");
        room.setRoomType("Suite");
        room.setPrice(8000);
        manageRoom.addRoom(room);
        double result = roomSearch.getRoomPrice(4,1,1);
        assertEquals(8000, result,0);
    }

    @Test
    public void filterRooms() throws ParseException {
        Room room = new Room();
        room.setHotelID(1);
        room.setBranchID(1);
        room.setBathRoomNO(2);
        room.setCheckINdate("2020-07-20");
        room.setCheckOUTdate("2020-07-29");
        RoomSearch roomSearch = new RoomSearch();
        ArrayList<Room> rooms = roomSearch.filterRooms(room);
        for (Room ro: rooms) {
            assertEquals(2, ro.getRoomNO());
        }
    }
}