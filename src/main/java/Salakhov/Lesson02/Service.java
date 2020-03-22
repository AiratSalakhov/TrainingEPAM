package Salakhov.Lesson02;

import org.slf4j.LoggerFactory;

public class Service implements InterfaceBd<HumanDto> {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Service.class.getName());
    Converter converter = new Converter();
    DataBase humanDb = new DataBase();

    public HumanDto getOne(int num) {
        HumanDto humanDto = converter.convertToDto(humanDb.getOne(num));
        System.out.println("из сервиса получаем DTO номер " + num + " " + humanDto);
        log.info("из сервиса получаем DTO номер " + num + " " + humanDto + " ===> log (CON)");
        return humanDto;
    }

    public HumanDto[] getAll() {
        HumanDto[] humanDtos = new HumanDto[10];
        for (int i = 0; i < 10; i++) {
            humanDtos[i] = converter.convertToDto(humanDb.getOne(i));
        }
        System.out.println("из сервиса получаем массив DTO " + humanDtos);
        return humanDtos;
    }

    public void setOne(HumanDto humanDto, int num) {
        System.out.println("через сервис сохраняем DTO номер " + num + " " + humanDto);
        log.info("через сервис сохраняем DTO номер " + num + " " + humanDto + " ===> log (CON)");
        humanDb.setOne(converter.convertToHuman(humanDto), num);
    }

    public void setAll(HumanDto[] humanDtos) {
        for (int i = 0; i < 10; i++) {
            humanDb.setOne(converter.convertToHuman(humanDtos[i]), i);
        }
        System.out.println("через сервис сохраняем массив DTO " + humanDtos);
    }
}
