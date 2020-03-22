package Salakhov.Lesson;

public class Address {
    private String city;
    private String street;
    private String house;
    private String room;

    public Address(String city, String street, String house, String room) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.room = room;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouse() {
        return this.house;
    }

    public String getRoom() {
        return this.room;
    }

    @Override
    public String toString() {
        return "Address (" + this.city + ", " + this.street + ", " + this.house + ", " + this.room + ")";
    }

}
