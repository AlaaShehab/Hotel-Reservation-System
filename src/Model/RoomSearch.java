import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RoomSearch {
    public ArrayList<Room> getRoomswithTimeRange(String checkINDate, String checkOUTDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date checkINdate = sdf.parse(checkINDate);
        java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
        java.util.Date checkOUTdate = sdf.parse(checkOUTDate);
        java.sql.Date sqlDateOUT = new Date(checkOUTdate.getTime());
        if(sqlDateIN.after(sqlDateOUT)){
            System.out.println("Enter Dates Correctly!");
            return null;
        }
        String query = "SELECT * FROM Room NATURAL JOIN reservation where (Check_IN between '"+checkINDate
                +"' and '"+checkOUTDate+"') and (Check_OUT between Check_IN and '"+checkOUTDate+"');";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public ArrayList<Room> getRoomswithPriceRange(double min, double max){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE Price BETWEEN "+min+" AND "+max+" ;";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                if (rs.getInt("Reservation_ID") == 0)
                    rooms.add(constructRoom(rs, false));
                else
                    rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public ArrayList<Room> getRoomswithBedsNo(int bedsNO){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE Beds_No = " + bedsNO +" ;";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                if (rs.getInt("Reservation_ID") == 0)
                    rooms.add(constructRoom(rs, false));
                else
                    rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public ArrayList<Room> getRoomswithBathRoomNO(int bathRoomNO){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE BathRooms_No = " + bathRoomNO +" ;";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                if (rs.getInt("Reservation_ID") == 0)
                    rooms.add(constructRoom(rs, false));
                else
                    rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public ArrayList<Room> getRoomswithType(String roomType){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE Type = '" + roomType +"';";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                if (rs.getInt("Reservation_ID") == 0)
                    rooms.add(constructRoom(rs, false));
                else
                    rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public ArrayList<Room> getRoomswithView(String roomView){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE View = '" + roomView +"';";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                if (rs.getInt("Reservation_ID") == 0)
                    rooms.add(constructRoom(rs, false));
                else
                    rooms.add(constructRoom(rs, true));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    public Room getRoomByReservationID(int reservationID){
        String query = "SELECT * FROM Room NATURAL JOIN reservation where Reservation_ID ="
                +reservationID+";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            Room room = constructRoom(rs, true);
            return room;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Room> getAvailableRooms(int hotelID, int branchID, String checkINdate, String checkOUTdate){
        String query = "SELECT * FROM Room where Room_No NOT IN (SELECT Room_No FROM Room natural join reservation" +
                " where Hotel_ID = "+hotelID+" and Branch_ID = "+branchID+" and Check_IN = '"+checkINdate+
                "' AND Check_OUT = '"+checkOUTdate+"') and Hotel_ID = "+hotelID+" and Branch_ID = "
                +branchID+";";
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = SQLConnection.getInstance().getData(query);
            while (rs.next()) {
                rooms.add(constructRoom(rs, false));
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return rooms;
    }
    private Room constructRoom(ResultSet rs, boolean isReserved){
        Room room = new Room();
        try {
            room.setRoomNO(rs.getInt("Room_No"));
            room.setBranchID(rs.getInt("Branch_ID"));
            room.setHotelID(rs.getInt("Hotel_ID"));
            room.setFloorNO(rs.getInt("Floor_No"));
            room.setBedsNO(rs.getInt("Beds_No"));
            room.setBathRoomNO(rs.getInt("BathRooms_No"));
            room.setPrice(rs.getDouble("Price"));
            room.setRoomType(rs.getString("Type"));
            room.setRoomView(rs.getString("View"));
            room.setReserved(isReserved);
            return room;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
