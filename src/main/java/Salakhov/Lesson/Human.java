package main.java.Salakhov.Lesson;

public class Human {
    private String fio;
    private int age;
    private Address address;

    public Human(String fio, int age, String city, String street, String house, String room) {
        this.fio = fio;
        this.age = age;
        this.address = new Address(city, street, house, room);
    }

    public String getFio() {
        return this.fio;
    }
    public int getAge() {
        return this.age;
    }
    public Address getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.address.getCity();
    }
    public String getStreet() {
        return this.address.getStreet();
    }
    public String getHouse() {
        return this.address.getHouse();
    }
    public String getRoom() {
        return this.address.getRoom();
    }
    @Override
    public String toString(){
        return "Class Human ("+this.fio+", "+this.age+", "+this.address+")";
    }
}
