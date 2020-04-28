package Model;

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
    private String city;
    private String country;
    private int postalCode;

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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "hotelName=" + hotelName +
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
                ", city=" + city +
                ", country=" + country +
                ", postal code=" + postalCode +
                '}';
    }
}