package Salakhov.Lesson02;

import java.util.Date;

public class Human {
    private int id;
    private String name;
    private Address address;
    private Date birthDate;

    private class Address {
        private String city;
        private String street;
        private String house;

        public Address(String city, String street, String house) {
            this.city = city;
            this.street = street;
            this.house = house;
        }

        public Address() {
        }
    }

    public Human(int id, String name, String city, String street, String house, Date birthDate) {
        this.id = id;
        this.name = new String(name);
        address = new Address(city, street, house);
        this.birthDate = birthDate;
    }

    public Human() {
        address = new Address();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String newName) {
        this.name = new String(newName);
    }

    public void setBirthDate(int year, int month, int day) {
        this.birthDate = new Date(year, month, day);
    }

    public void setAddress(String city, String street, String house) {
        this.address.city = new String(city);
        this.address.street = new String(street);
        this.address.house = new String(house);
    }

    public String getCity() {
        return this.address.city;
    }

    public String getStreet() {
        return this.address.street;
    }

    public String getHouse() {
        return this.address.house;
    }

    public void setCity(String city) {
        this.address.city = new String(city);
    }

    public void setStreet(String street) {
        this.address.street = new String(street);
    }

    public void setHouse(String house) {
        this.address.house = new String(house);
    }
}
