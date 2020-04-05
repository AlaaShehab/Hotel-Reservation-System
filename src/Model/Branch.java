
public class Branch {
    private int branchID;
    private int hotelID;
    private int managerID;
    private float rating;
    private boolean gym;
    private boolean spa;
    private boolean garage;
    private boolean carRental;
    private boolean beach;
    private boolean pool;
    private String hotelName;
    private String location;

    public void setBranchID(int id) {
        branchID = id;
    }
    public int getBranchID() {
        return branchID;
    }
    public void setHotelID(int id) {
        hotelID = id;
    }
    public int getHotelID() {
        return hotelID;
    }
    public void setManagerID(int id) {
        managerID = id;
    }
    public int getManagerID() {
        return managerID;
    }
    public void setRating(float rate) {
        rating = rate;
    }
    public float getRating() {
        return rating;
    }
    public void setBeach(boolean beach) {
        this.beach = beach;
    }
    public boolean hasBeach(){
        return beach;
    }
    public void setPool(boolean pool) {
        this.pool = pool;
    }
    public boolean hasPool() {
        return pool;
    }
    public void setGym(boolean gym) {
        this.gym = gym;
    }
    public boolean hasGym() {
        return gym;
    }
    public void setGarage(boolean garage) {
        this.garage = garage;
    }
    public boolean hasGarage() {
        return garage;
    }
    public void setSpa(boolean spa) {
        this.spa = spa;
    }
    public boolean hasSpa() {
        return spa;
    }
    public void setCarRental(boolean carRental) {
        this.carRental = carRental;
    }
    public boolean hasCarRental() {
        return carRental;
    }
    public void setHotelName(String name) {
        hotelName = name;
    }
    public String getHotelName() {
        return hotelName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Branch{" +
                ", hotelName=" + hotelName +
                ", hotelID=" + hotelID +
                ", branchID=" + branchID +
                ", rating='" + rating + '\'' +
                ", beach='" + beach + '\'' +
                ", pool=" + pool +
                ", gym=" + gym +
                ", garage='" + garage + '\'' +
                ", spa=" + spa +
                ", carRental=" + carRental +
                ", managerID=" + managerID +
                '}';
    }
}