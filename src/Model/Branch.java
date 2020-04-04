public class Branch {
    private int Branch_ID;
    private int Hotel_ID;
    private int MGR_ID;
    private float rating;
    private boolean gym;
    private boolean spa;
    private boolean garage;
    private boolean car_rental;
    private boolean beach;
    private boolean pool;
    private String Hotel_Name;

    public void set_Branch_ID (int id) {
        Branch_ID = id;
    }
    public int get_Branch_ID () {
        return Branch_ID;
    }
    public void set_Hotel_ID (int id) {
        Hotel_ID = id;
    }
    public int get_Hotel_ID () {
        return Hotel_ID;
    }
    public void set_MGR_ID (int id) {
        MGR_ID = id;
    }
    public int get_MGR_ID () {
        return MGR_ID;
    }
    public void set_Rating (float rate) {
        rating = rate;
    }
    public float get_Rating () {
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
    public void setCar_rental(boolean car_rental) {
        this.car_rental = car_rental;
    }
    public boolean hasCar_rental() {
        return car_rental;
    }
    public void set_Hotel_Name (String name) {
        Hotel_Name = name;
    }
    public String get_Hotel_Name () {
        return Hotel_Name;
    }
    @Override
    public String toString() {
        return "Branch{" +
                ", Hotel_Name=" + Hotel_Name +
                ", Hotel_ID=" + Hotel_ID +
                ", Branch_ID=" + Branch_ID +
                "rating='" + rating + '\'' +
                ", beach='" + beach + '\'' +
                ", pool=" + pool +
                ", gym=" + gym +
                ", garage='" + garage + '\'' +
                ", spa=" + spa +
                ", car_rental=" + car_rental +
                ", MGR_ID=" + MGR_ID +
                '}';
    }
}