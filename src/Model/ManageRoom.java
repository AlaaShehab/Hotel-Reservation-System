package Model;

import Model.SQLConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;;

public class ManageRoom {
    public boolean addRoom(Room room) throws SQLException {
        PreparedStatement addRoom = SQLConnection.getInstance().getConnection().prepareStatement(
                "INSERT INTO Room VALUES (?,?,?,?,?,?,?,?,?);");
        addRoom.setInt(1, room.getRoomNO());
        addRoom.setInt(2, room.getHotelID());
        addRoom.setInt(3, room.getBranchID());
        addRoom.setInt(4, room.getFloorNO());
        addRoom.setInt(5, room.getBedsNO());
        addRoom.setInt(6,room.getBathRoomNO());
        addRoom.setDouble(7,room.getPrice());
        addRoom.setString(8, room.getRoomType());
        addRoom.setString(9, room.getRoomView());
        if(addRoom.executeUpdate() == -1){
            return false;
        }
        return true;
    }

}