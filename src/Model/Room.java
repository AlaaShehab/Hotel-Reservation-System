public class Room {
    private int roomNO = -1;
    private int hotelID  = -1;
    private String hotelName = "";
    private String country = "";
    private String city = "";
    private int branchID = -1;
    private int floorNO = -1;
    private int bedsNO = -1;
    private int bathRoomNO = -1;
    private double price = -1;
    private String roomType = "";
    private String roomView = "";
    private boolean isReserved;
    private int minPrice = -1;
    private int maxPrice = -1;
    private String checkINdate = "";
    private String checkOUTdate = "";

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

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCheckINdate() {
        return checkINdate;
    }

    public void setCheckINdate(String checkINdate) {
        this.checkINdate = checkINdate;
    }

    public String getCheckOUTdate() {
        return checkOUTdate;
    }

    public void setCheckOUTdate(String checkOUTdate) {
        this.checkOUTdate = checkOUTdate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
                ", hotelName=" + hotelName +
                ", country=" + country +
                ", city=" + city +
                '}';
    }
}