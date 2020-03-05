package main.java.Salakhov.Lesson;

public class Human {
    private String fio;
    private int age;
    private Address address;

    public Human(String fio, int age, String city, String street, String house, String room) {
        this.fio = fio;
        this.age = age;
        this.address = new Address(String city, String street, String house, String room);
    }

//    public Human() {address = new Address();}

    public String getfio() {
        return this.fio;
    }
    public int getAge() {
        return this.age;
    }
    public Address getAddress() {
        return this.address;
    }

/*
    public void setid(int id) {
        this.id = id;
    }
    public void setname(String newValue) {
        this.name = new String(newValue);
    }
    public void setBirthDate(int year, int month, int day) {
        this.birthDate = new Date(year, month, day);
    }
    public void setAddress(String city, String street, String house) {
        this.address.city = new String(city);
        this.address.street = new String(street);
        this.address.house = new String(house);
    }

 */
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
/*
    public void setCity(String newValue) {
        this.address.city = new String(newValue);
    }
    public void setStreet(String newValue) {
        this.address.street = new String(newValue);
    }
    public void setHouse(String newValue) {
        this.address.house = new String(newValue);
    }


 */
}
