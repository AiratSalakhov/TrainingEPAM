package Lesson02;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

public class Convertor {
/*
public class Convertor<T, V> {
    public V convert(T t){
        V v = new V();
        v.setid(t.getid());
        v.setname(t.getname());
        v.setAddress(t.getAddress());
        v.setBirthDate(t.getBirthDate().getYear(), t.getBirthDate().getMonth(), t.getBirthDate().getDay());
        return v;
    }

 */
//*
    public HumanDTO convertToDTO(Human human){
        HumanDTO v = new HumanDTO();

        v.setid(human.getid());
        v.setname(human.getname());
        v.setAddress(human.getCity(), human.getStreet(), human.getHouse());
        v.setBirthDate(human.getBirthDate().getYear(), human.getBirthDate().getMonth(), human.getBirthDate().getDay());
        return v;
    }
    public Human convertToHuman(HumanDTO humanDTO){
        Human v = new Human();
        v.setid(humanDTO.getid());
        v.setname(humanDTO.getname());
        v.setAddress(humanDTO.getCity(), humanDTO.getStreet(), humanDTO.getHouse());
        v.setBirthDate(humanDTO.getBirthDate().getYear(), humanDTO.getBirthDate().getMonth(), humanDTO.getBirthDate().getDay());
        return v;
    }

 //*/
}
