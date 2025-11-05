package entities;

public class Deed {

    private int number;
    private String type;
    private int basicRent;
    private int rentOne;
    private int rentTwo;
    private int rentThree;
    private int rentFour;
    private int rentHotel;
    private int housePrice;
    private int hotelPrice;
    private int mortgage;


    public Deed (int number, String type, int basicRent, int rentOne, int rentTwo, int rentThree, int rentFour, int rentHotel, int housePrice, int hotelPrice, int mortgage){
        this.number = number;
        this.type = type;
        this.basicRent = basicRent;
        this.rentOne = rentOne;
        this.rentTwo = rentTwo;
        this.rentThree = rentThree;
        this.rentFour = rentFour;
        this.rentHotel = rentHotel;
        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
        this.mortgage = mortgage;
    }

    public Deed(int number, String type, int basicRent, int rentTwo, int rentThree, int rentFour, int mortgage) {
        this.number = number;
        this.type = type;
        this.basicRent = basicRent;
        this.rentTwo = rentTwo;
        this.rentThree = rentThree;
        this.rentFour = rentFour;
        this.mortgage = mortgage;
    }

    public Deed(int mortgage) {
        this.mortgage = mortgage;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public int getBasicRent(){
        return basicRent;
    }

    public int getRentOne() {
        return rentOne;
    }

    public int getRentTwo() {
        return rentTwo;
    }

    public int getRentThree() {
        return rentThree;
    }

    public int getRentFour() {
        return rentFour;
    }

    public int getRentHotel() {
        return rentHotel;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getHotelPrice() {
        return hotelPrice;
    }

    public int getMortgage() {
        return mortgage;
    }

    @Override
    public String toString() {
        return "Deed{" + "number=" + number + ", type='" + type + '\'' + ", basicRent=" + basicRent + ", rentOne=" + rentOne + ", rentTwo=" + rentTwo + ", rentThree=" + rentThree + ", rentFour=" + rentFour + ", rentHotel=" + rentHotel + ", housePrice=" + housePrice + ", hotelPrice=" + hotelPrice + ", mortgage=" + mortgage + '}';
    }
}
