package Lesson02;

public class Convertor<T, V> {
    public V convert(T t){
        V v = new Object();
        v.setid(t.getid());
        v.setname(t.getname());
        v.setAddress(t.getAddress());
        v.setBirthDate(t.getBirthDate().getYear(), t.getBirthDate().getMonth(), t.getBirthDate().getDay());
        return v;
    }
}
