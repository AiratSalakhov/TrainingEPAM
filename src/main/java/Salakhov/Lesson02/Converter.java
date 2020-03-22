package Salakhov.Lesson02;

public class Converter {
    public HumanDto convertToDto(Human human) {
        System.out.println("Конвертируем в DTO...");
        HumanDto v = new HumanDto();
        v.setId(human.getId());
        v.setName(human.getName());
        v.setAddress(human.getCity(), human.getStreet(), human.getHouse());
        v.setBirthDate(human.getBirthDate().getYear(), human.getBirthDate().getMonth(), human.getBirthDate().getDay());
        return v;
    }

    public Human convertToHuman(HumanDto humanDto) {
        Human v = new Human();
        System.out.println("Конвертируем в Human...");
        v.setId(humanDto.getId());
        v.setName(humanDto.getName());
        v.setAddress(humanDto.getCity(), humanDto.getStreet(), humanDto.getHouse());
        v.setBirthDate(humanDto.getBirthDate().getYear(), humanDto.getBirthDate().getMonth(), humanDto.getBirthDate().getDay());
        return v;
    }
}
