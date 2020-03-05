package main.java.Salakhov.Lesson02;

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
            this.city = city;
            this.street = street;
            this.house = house;
        }

        public Address() {}
    }

    public HumanDTO(int id, String name, String city, String street, String house, Date birthDate) {
        this.id = id;
        this.name = name;
        address = new Address(city, street, house);
        this.birthDate = birthDate;
    }

    public HumanDTO() {
        this.id = (int)(Math.random()*10000);
        String[] names = {"Вася", "Петя", "Саша", "Гена", "Дима"};
        String[] cities = {"Тольятти", "Самара", "Москва", "Воронеж", "Казань"};
        String[] streets = {"Юбилейная", "Ленина", "Революционная", "Южная", "Северная"};
        String[] houses = {"31Е", "20", "38", "1", "135/6"};
        this.name = names[(int)(Math.random()*4)];
        address = new Address(cities[(int)(Math.random()*4)], streets[(int)(Math.random()*4)], houses[(int)(Math.random()*4)]);
        this.birthDate = new Date(110-(int)(Math.random()*60), 1+(int)(Math.random()*11), 1+(int)(Math.random()*27));
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
        this.name = newValue;
    }
    public void setBirthDate(int year, int month, int day) {
        this.birthDate = new Date(year, month, day);
    }
    public void setAddress(String city, String street, String house) {
        this.address.city = city;
        this.address.street = street;
        this.address.house = house;
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
    public void setCity(String newValue) {
        this.address.city = newValue;
    }
    public void setStreet(String newValue) {
        this.address.street = newValue;
    }
    public void setHouse(String newValue) {
        this.address.house = newValue;
    }

    @Override
    public String toString() {
        return  "id=" + this.id +
                ", name=" + this.name +
                ", city=" + this.getCity() +
                ", street=" + this.getStreet() +
                ", house=" + this.getHouse() +
                ", birthDate=" + this.birthDate;
    }
}
