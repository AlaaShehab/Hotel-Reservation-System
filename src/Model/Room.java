public class Room {
    private int roomNO;
    private int hotelID;
    private int branchID;
    private int floorNO;
    private int bedsNO;
    private int bathRoomNO;
    private double price;
    private String roomType;
    private String roomView;
    private boolean isReserved;

    public int getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(int roomNO) {
        this.roomNO = roomNO;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getFloorNO() {
        return floorNO;
    }

    public void setFloorNO(int floorNO) {
        this.floorNO = floorNO;
    }

    public int getBedsNO() {
        return bedsNO;
    }

    public void setBedsNO(int bedsNO) {
        this.bedsNO = bedsNO;
    }

    public int getBathRoomNO() {
        return bathRoomNO;
    }

    public void setBathRoomNO(int bathRoomNO) {
        this.bathRoomNO = bathRoomNO;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomView() {
        return roomView;
    }

    public void setRoomView(String roomView) {
        this.roomView = roomView;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "hotelID=" + hotelID +
                ", branchID=" + branchID +
                ", roomNO=" + roomNO +
                ", floorNO=" + floorNO +
                ", bedsNO=" + bedsNO +
                ", bathRoomNO=" + bathRoomNO +
                ", price=" + price +
                ", roomType=" + roomType +
                ", roomView=" + roomView +
                ", reserved=" + isReserved +
                '}';
    }
}
