public class Filter {
    private String hotelName = "";
    private String country = "";
    private String city = "";
    private int bedsNO = -1;
    private int bathRoomNO = -1;
    private String roomType = "";
    private String roomView = "";
    private int minPrice = -1;
    private int maxPrice = -1;
    private String checkINdate = "";
    private String checkOUTdate = "";

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
}
