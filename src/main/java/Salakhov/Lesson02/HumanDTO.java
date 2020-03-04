package Lesson02;

import java.util.Date;

public class HumanDTO {
    private int id;
    private String name;
    private Address address;
    private Date birthDate;

    private class Address {
        private String city;
        private String street;
        private String house;

        public Address(String city, String street, String house) {
            this.city = new String(city);
            this.street = new String(street);
            this.house = new String(house);
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
        public void setCity(String newValue) {
            this.city = new String(newValue);
        }
        public void setStreet(String newValue) {
            this.street = new String(newValue);
        }
        public void setHouse(String newValue) {
            this.house = new String(newValue);
        }

    }

    public HumanDTO(int id, String name, String city, String street, String house, Date birthDate) {
        this.id = id;
        this.name = new String(name);
        address = new Address(city, street, house);
        this.birthDate = birthDate;
    }

    public int getid() {
        return this.id;
    }
    public String getname() {
        return this.name;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }
    public Address getAddress() {
        return this.address;
    }
    public void setid(int id) {
        this.id = id;
    }
    public void setname(String newValue) {
        this.name = new String(newValue);
    }
    public void setBirthDate(int year, int month, int day) {
        this.birthDate = new Date(year, month, day);
    }
    public void setAddress(Address newValue) {
        this.address.city = new String(newValue.getCity());
        this.address.street = new String(newValue.getStreet());
        this.address.house = new String(newValue.getHouse());
    }
}
