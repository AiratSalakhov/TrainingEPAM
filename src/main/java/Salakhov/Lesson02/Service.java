package Salakhov.Lesson02;

public class Service implements InterfaceBd<HumanDto> {
    Converter converter = new Converter();
    DataBase humanDb = new DataBase();

    public HumanDto getOne(int num) {
        HumanDto humanDto = converter.convertToDto(humanDb.getOne(num));
        System.out.println("из сервиса получаем DTO номер " + num + " " + humanDto);
        return humanDto;
    }

    public HumanDto[] getAll() {
        HumanDto[] dtoHumanDb = new HumanDto[10];
        for (int i = 0; i < 10; i++) {
            dtoHumanDb[i] = converter.convertToDto(humanDb.getOne(i));
        }
        System.out.println("из сервиса получаем массив DTO " + dtoHumanDb);
        return dtoHumanDb;
    }

    public void setOne(HumanDto humanDto, int num) {
        System.out.println("через сервис сохраняем DTO номер " + num + " " + humanDto);
        humanDb.setOne(converter.convertToHuman(humanDto), num);
    }

    public void setAll(HumanDto[] dtoHumanDb) {
        for (int i = 0; i < 10; i++) {
            humanDb.setOne(converter.convertToHuman(dtoHumanDb[i]), i);
        }
        System.out.println("через сервис сохраняем массив DTO " + dtoHumanDb);
    }
}
