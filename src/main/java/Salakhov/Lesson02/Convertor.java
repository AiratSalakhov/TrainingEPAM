package Lesson02;

public class Convertor<T> {
/*
public class Convertor<T, V> {
    public V convert(T t){
        V v = new <V>();
        v.setid(t.getid());
        v.setname(t.getname());
        v.setAddress(t.getAddress());
        v.setBirthDate(t.getBirthDate().getYear(), t.getBirthDate().getMonth(), t.getBirthDate().getDay());
        return v;
    }

 */
//*
    public HumanDTO convertToDTO(T t){
        HumanDTO v = new HumanDTO();
        v.setid(t.getid());
        v.setname(t.getname());
        v.setAddress(t.getAddress());
        v.setBirthDate(t.getBirthDate().getYear(), t.getBirthDate().getMonth(), t.getBirthDate().getDay());
        return v;
    }
    public Human convertToHuman(T t){
        Human v = new Human();
        v.setid(t.getid());
        v.setname(t.getname());
        v.setAddress(t.getAddress());
        v.setBirthDate(t.getBirthDate().getYear(), t.getBirthDate().getMonth(), t.getBirthDate().getDay());
        return v;
    }

 //*/
}
