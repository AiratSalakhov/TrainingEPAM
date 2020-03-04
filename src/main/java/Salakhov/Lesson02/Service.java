package Lesson02;

public class Service implements InterfaceBD<HumanDTO> {
//    Convertor<Human, HumanDTO> humanToDTO = new Convertor<>();
//    Convertor<HumanDTO, Human> dtoToHuman = new Convertor<>();
//    Convertor<HumanDTO> humanToDTO = new Convertor<>();
//    Convertor<Human> dtoToHuman = new Convertor<>();
    Convertor convertor = new Convertor();
    DataBase<Human> humanDB = new DataBase<>();

    //получение одной сущности
    public HumanDTO getOne(int num){
        // возвращаем в формате DTO
//        return humanToDTO.convert(humanDB.getOne(num));
        return convertor.convertToDTO(humanDB.getOne(num));
    }

    //получение всех сущностей
    public HumanDTO[] getAll(){
        DataBase<HumanDTO> dtoHumanDB = new DataBase<>();
        for (int i=0; i<10; i++) {
//            dtoHumanDB[i] = humanToDTO.convert(humanDB.getOne(i));
            dtoHumanDB.setOne(convertor.convertToDTO(humanDB.getOne(i)), i);
        }
        return dtoHumanDB.getAll();
    }

    //сохранение одной сущности
//    public void setOne(HumanDTO t, int num){humanDB.setOne(dtoToHuman.convert(t), num);   }
    public void setOne(HumanDTO t, int num){
        humanDB.setOne(convertor.convertToHuman(t), num);
    }

    //сохранение списка сущностей
    public void setAll(HumanDTO[] t){
        for (int i=0; i<10; i++) {
//            HumanDB[i] = dtoToHuman.convert(t.getOne(i));
            humanDB.setOne(convertor.convertToHuman(t[i]), i);
        }
    }
}
