package Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

public class Converter {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Converter.class.getName());

    public HumanDto convertToDto(Human human) {
        System.out.println("Конвертируем в DTO...");
        log.info("Конвертируем в DTO... ===> log (CON+FILE)");
        HumanDto humanDto = new HumanDto();
        humanDto.setId(human.getId());
        humanDto.setName(human.getName());
        humanDto.setAddress(human.getCity(), human.getStreet(), human.getHouse());
        humanDto.setBirthDate(human.getBirthDate().getYear(), human.getBirthDate().getMonth(), human.getBirthDate().getDay());
        return humanDto;
    }

    public Human convertToHuman(HumanDto humanDto) {
        Human human = new Human();
        System.out.println("Конвертируем в Human...");
        log.info("Конвертируем в Human... ===> log (CON+FILE)");
        human.setId(humanDto.getId());
        human.setName(humanDto.getName());
        human.setAddress(humanDto.getCity(), humanDto.getStreet(), humanDto.getHouse());
        human.setBirthDate(humanDto.getBirthDate().getYear(), humanDto.getBirthDate().getMonth(), humanDto.getBirthDate().getDay());
        return human;
    }
}
