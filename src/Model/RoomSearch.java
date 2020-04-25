import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.text.ParseException;
import java.util.ArrayList;

public class RoomSearch {
    public ArrayList<Room> getRoomswithTimeRange(int Hotel_ID, int Branch_ID, String checkINDate, String checkOUTDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date checkINdate = sdf.parse(checkINDate);
        java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
        java.util.Date checkOUTdate = sdf.parse(checkOUTDate);
        java.sql.Date sqlDateOUT = new Date(checkOUTdate.getTime());
        if(sqlDateIN.after(sqlDateOUT)){
            System.out.println("Enter Dates Correctly!");
            return null;
        }
        String query = "SELECT * FROM Room as ro NATURAL JOIN reservation where ((Check_IN between '"+checkINDate
                +"' and '"+checkOUTDate+"') or (Check_OUT between Check_IN and '"+checkOUTDate+"'))" +
                " and ro.Hotel_ID = " + Hotel_ID + " and ro.Branch_ID = " + Branch_ID +";";
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
    public ArrayList<Room> getRoomswithPriceRange(int Hotel_ID, int Branch_ID, double min, double max){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE (Price BETWEEN "+min+" AND "+max+") and ro.Hotel_ID = " + Hotel_ID
                + " and ro.Branch_ID = " + Branch_ID + ";";
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
    public ArrayList<Room> getRoomswithBedsNo(int Hotel_ID, int Branch_ID, int bedsNO){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE Beds_No = " + bedsNO +" and ro.Hotel_ID = " + Hotel_ID +
                " and ro.Branch_ID = " + Branch_ID + ";";
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
    public ArrayList<Room> getRoomswithBathRoomNO(int Hotel_ID, int Branch_ID, int bathRoomNO){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE BathRooms_No = " + bathRoomNO +" and ro.Hotel_ID = " + Hotel_ID +
                " and ro.Branch_ID = " + Branch_ID + ";";
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
    public ArrayList<Room> getRoomswithType(int Hotel_ID, int Branch_ID, String roomType){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE Type = '" + roomType +"' and ro.Hotel_ID = " + Hotel_ID +
                " and ro.Branch_ID = " + Branch_ID + ";";
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
    public ArrayList<Room> getRoomswithView(int Hotel_ID, int Branch_ID, String roomView){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE View = '" + roomView +"' and ro.Hotel_ID = " + Hotel_ID +
                " and ro.Branch_ID = " + Branch_ID + ";";
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
    public ArrayList<Room> getAvailableRooms(int hotelID, int branchID, String checkINdate, int resPeriod) throws ParseException {
        java.sql.Date sqlCurrDate;
        if(checkINdate == null || checkINdate.isEmpty()){
            LocalDate currentDate = LocalDate.now();
            sqlCurrDate= java.sql.Date.valueOf(currentDate);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date checkIN = sdf.parse(checkINdate);
            sqlCurrDate = new Date(checkIN.getTime());
        }
        String checkOUTdate = getCheckOUTDate(sqlCurrDate, resPeriod);
        String query = "SELECT * FROM Room where Room_No NOT IN (SELECT Room_No FROM Room natural join reservation" +
                " where Hotel_ID = "+hotelID+" and Branch_ID = "+branchID+" and (Check_IN between '"+sqlCurrDate +
                "' and '"+checkOUTdate+"') or (Check_OUT between Check_IN and '"+checkOUTdate+"')) and Hotel_ID = "+hotelID+" and Branch_ID = "
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
    public double getRoomPrice(int roomNo, int hotelID, int branchID){
        String query = "SELECT * FROM Room AS ro left outer join reservation as rs on ro.Hotel_ID = rs.Hotel_ID and " +
                "ro.Branch_ID=rs.Branch_ID and ro.Room_No=rs.Room_No" +
                " WHERE ro.Room_No = " + roomNo +" and ro.Hotel_ID = "+hotelID+" and ro.Branch_ID = "+branchID+";";
        ResultSet rs = null;
        try {
            rs = SQLConnection.getInstance().getData(query);
            rs.next();
            Room room = null;
            if (rs.getInt("Reservation_ID") == 0)
                room = constructRoom(rs, true);
            else
                room = constructRoom(rs, true);
            return room.getPrice();
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
    private String getCheckOUTDate(java.sql.Date checkINdate, int resPeriod){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(checkINdate);
        c.add(Calendar.DAY_OF_MONTH, resPeriod);
        String newDate = sdf.format(c.getTime());
        return newDate;
    }
    public ArrayList<Room> filterRooms(Room room) throws ParseException {
        String query = "SELECT * FROM Room where Room_No NOT IN (SELECT Room_No FROM Room natural join reservation" +
                " where Hotel_ID = "+room.getHotelID()+" and Branch_ID = "+room.getBranchID();
        if (room.getMinPrice() != -1 && room.getMaxPrice()==-1)
            query += " and Price BETWEEN "+room.getMinPrice()+" AND "+room.getMaxPrice();
        if(room.getBedsNO() != -1)
            query += " and Beds_No="+room.getBedsNO();
        if (room.getBathRoomNO() != -1)
            query += " and BathRooms_No="+room.getBathRoomNO();
        if (room.getFloorNO() != -1)
            query += " and Floor_No="+room.getFloorNO();
        if (!room.getRoomType().isEmpty())
            query += " and Type='"+room.getRoomType()+"'";
        if (!room.getRoomView().isEmpty())
            query += " and View='"+room.getRoomView()+"'";
        if(!room.getCheckINdate().isEmpty() && !room.getCheckOUTdate().isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date checkINdate = sdf.parse(room.getCheckINdate());
            java.sql.Date sqlDateIN = new Date(checkINdate.getTime());
            java.util.Date checkOUTdate = sdf.parse(room.getCheckOUTdate());
            java.sql.Date sqlDateOUT = new Date(checkOUTdate.getTime());
            if(sqlDateIN.after(sqlDateOUT)){
                System.out.println("Enter Dates Correctly!");
                return null;
            }
            query += " and ((Check_IN between '"+room.getCheckINdate() +"' and '"+room.getCheckOUTdate()
                    +"') or (Check_OUT between Check_IN and '"+room.getCheckOUTdate()+"'))";
        }
        query += ") and Hotel_ID = "+room.getHotelID()+" and Branch_ID = " +room.getBranchID()+";";
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