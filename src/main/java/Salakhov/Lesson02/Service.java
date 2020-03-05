package main.java.Salakhov.Lesson02;

public class Service implements InterfaceBD<HumanDTO> {
    Converter converter = new Converter();
    DataBase humanDB = new DataBase();

    //получение одной сущности
    public HumanDTO getOne(int num){
        // возвращаем в формате DTO
        HumanDTO hdto = converter.convertToDTO(humanDB.getOne(num));
        System.out.println("из сервиса получаем DTO номер " + num + " " + hdto);
        return hdto;
    }

    //получение всех сущностей
    public HumanDTO[] getAll(){
        HumanDTO[] dtoHumanDB = new HumanDTO[10];
        for (int i=0; i<10; i++) {
            dtoHumanDB[i] = converter.convertToDTO(humanDB.getOne(i));
        }
        System.out.println("из сервиса получаем массив DTO " + dtoHumanDB);
        return dtoHumanDB;
    }

    //сохранение одной сущности
    public void setOne(HumanDTO t, int num){
//        System.out.println("convertor.convertToHuman(t), num ");
//        System.out.println(" " + convertor.convertToHuman(t) + ", " + num);
        System.out.println("через сервис сохраняем DTO номер " + num + " " + t);
        humanDB.setOne(converter.convertToHuman(t), num);
    }

    //сохранение списка сущностей
    public void setAll(HumanDTO[] t){
        for (int i=0; i<10; i++) {
            humanDB.setOne(converter.convertToHuman(t[i]), i);
        }
        System.out.println("через сервис сохраняем массив DTO " + t);
    }
}
