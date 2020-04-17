public class Reservation {
    private int reservationID;
    private int hotelID;
    private int branchID;
    private int userID;
    private int roomNO;
    private String checkINDate;
    private String checkOUTDate;
    private boolean isPaid;

    public int getResrvationID() {
        return reservationID;
    }

    public void setResrvationID(int resrvationID) {
        this.reservationID = resrvationID;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRoomNO() {
        return roomNO;
    }

    public void setRoomNO(int roomNO) {
        this.roomNO = roomNO;
    }

    public String getCheckINDate() {
        return checkINDate;
    }

    public void setCheckINDate(String checkINDate) {
        this.checkINDate = checkINDate;
    }

    public String getCheckOUTDate() {
        return checkOUTDate;
    }

    public void setCheckOUTDate(String checkOUTDate) {
        this.checkOUTDate = checkOUTDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", hotelID=" + hotelID +
                ", branchID=" + branchID +
                ", userID=" + userID +
                ", roomNO=" + roomNO +
                ", checkINDate=" + checkINDate +
                ", checkINDate=" + checkINDate +
                ", isPaid=" + isPaid +
                '}';
    }
}