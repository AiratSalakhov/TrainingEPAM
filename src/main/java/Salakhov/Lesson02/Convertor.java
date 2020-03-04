package Lesson02;

public class Convertor {
    public HumanDTO convertToDTO(Human human){
        System.out.println("Конвертируем в DTO...");
        HumanDTO v = new HumanDTO();
        v.setid(human.getid());
        v.setname(human.getname());
        v.setAddress(human.getCity(), human.getStreet(), human.getHouse());
        v.setBirthDate(human.getBirthDate().getYear(), human.getBirthDate().getMonth(), human.getBirthDate().getDay());
        return v;
    }
    public Human convertToHuman(HumanDTO humanDTO){
        Human v = new Human();
        System.out.println("Конвертируем в Human...");
        v.setid(humanDTO.getid());
        v.setname(humanDTO.getname());
        v.setAddress(humanDTO.getCity(), humanDTO.getStreet(), humanDTO.getHouse());
        v.setBirthDate(humanDTO.getBirthDate().getYear(), humanDTO.getBirthDate().getMonth(), humanDTO.getBirthDate().getDay());
        return v;
    }
}
