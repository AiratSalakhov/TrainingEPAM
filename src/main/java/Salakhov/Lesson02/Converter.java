package main.java.Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

public class Converter {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Converter.class.getName());
    public HumanDTO convertToDTO(Human human){
        System.out.println("Конвертируем в DTO...");
        log.info("Конвертируем в DTO... ===> log (CON+FILE)");
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
        log.info("Конвертируем в Human... ===> log (CON+FILE)");
        v.setid(humanDTO.getid());
        v.setname(humanDTO.getname());
        v.setAddress(humanDTO.getCity(), humanDTO.getStreet(), humanDTO.getHouse());
        v.setBirthDate(humanDTO.getBirthDate().getYear(), humanDTO.getBirthDate().getMonth(), humanDTO.getBirthDate().getDay());
        return v;
    }
}
