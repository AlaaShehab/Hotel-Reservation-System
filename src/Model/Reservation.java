public class Reservation {
    private int reservationID = -1;
    private int hotelID = -1;
    private int branchID = -1;
    private int userID = -1;
    private int roomNO = -1;
    private String checkINDate = "";
    private String checkOUTDate = "";
    private boolean isPaid = false;
    private String userEmail = "";
    private String userFN = "";
    private String userLN = "";
    private String phoneNO = "";

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFN() {
        return userFN;
    }

    public void setUserFN(String userFN) {
        this.userFN = userFN;
    }

    public String getUserLN() {
        return userLN;
    }

    public void setUserLN(String userLN) {
        this.userLN = userLN;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
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