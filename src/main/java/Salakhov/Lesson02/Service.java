package Lesson02;

public class Service implements InterfaceBD<HumanDTO> {
    Convertor<Human, HumanDTO> humanToDTO = new Convertor<>();
    Convertor<HumanDTO, Human> dtoToHuman = new Convertor<>();
    DataBase<Human> humanDB = new DataBase<>();

    //получение одной сущности
    public HumanDTO getOne(int num){
        // возвращаем в формате DTO
        return humanToDTO.convert(humanDB.getOne(num));
    }

    //получение всех сущностей
    public HumanDTO[] getAll(){
        DataBase<HumanDTO> dtoHumanDB = new DataBase<>();
        for (int i=0; i<10; i++) {
            dtoHumanDB[i] = humanToDTO.convert(humanDB.getOne(i));
        }
        return dtoHumanDB;
    }

    //сохранение одной сущности
    public void setOne(HumanDTO t, int num){
        humanDB.setOne(dtoToHuman.convert(t), num);
    }

    //сохранение списка сущностей
    public void setAll(HumanDTO[] t){
        for (int i=0; i<10; i++) {
            HumanDB[i] = dtoToHuman.convert(t.getOne(i));
        }
    }
}
