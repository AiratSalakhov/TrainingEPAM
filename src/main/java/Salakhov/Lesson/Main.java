package main.java.Salakhov.Lesson;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Human[] human = new Human[10];
        human[0] = new Human("ФИО1", 20, "город1", "улица1", "дом1", "квартира1");
        human[1] = new Human("ФИО6", 21, "город2", "улица1", "дом1", "квартира2");
        human[2] = new Human("ФИО3", 22, "город3", "улица1", "дом1", "квартира3");
        human[3] = new Human("ФИО4", 17, "город4", "улица1", "дом1", "квартира4");
        human[4] = new Human("ФИО1", 20, "город1", "улица1", "дом1", "квартира1"); // дубль [0]
        human[5] = new Human("ФИО6", 25, "город0", "улица1", "дом1", "квартира6");
        human[6] = new Human("ФИО0", 26, "город7", "улица1", "дом1", "квартира7");
        human[7] = new Human("ФИО3", 22, "город3", "улица1", "дом1", "квартира3"); // дубль [2]
        human[8] = new Human("ФИО6", 25, "город6", "улица1", "дом1", "квартира6"); // дубль [5]
        human[9] = new Human("ФИО10", 29, "город10", "улица1", "дом1", "квартира10");

        System.out.println("1. Заполнить ArrayList этими объектами.");
        ArrayList<Human> arrayList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            arrayList.add(human[i]);
            System.out.println(human[i]);
        }
        System.out.println("===========================");
        System.out.println("2. Найти дубли в коллекции и вывести их в консоль.");
        System.out.println("3. Удалить дубли из коллекции, должно остаться 7 объектов.");
        // сделаем в один проход
        for (int i=0; i<arrayList.size(); i++) {
            for (int j=i+1; j<arrayList.size(); j++) {
                if (arrayList.get(i).toString().equals(arrayList.get(j).toString())) {
                    // это дубль, выведем его
                    System.out.println(arrayList.get(j).toString());
                    // и сразу удалим
                    arrayList.remove(j);
                    j--;
                }
            }
        }
        System.out.println("===========================");
        System.out.println("4. Отсортировать людей по ФИО");
        Comparator<Human> humanComparatorByName = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {return o1.getFio().compareTo(o2.getFio());}
        };
        arrayList.sort(humanComparatorByName);
        System.out.println(arrayList);
        System.out.println("===========================");
        System.out.println("5. Отсортировать людей по возрасту");
        Comparator<Human> humanComparatorByAge = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                if (o1.getAge() == o2.getAge()) return 0;
                return o1.getAge() > o2.getAge() ? 1 : -1;
            }
        };
        arrayList.sort(humanComparatorByAge);
        System.out.println(arrayList);
        System.out.println("===========================");
        System.out.println("6. Отсортировать людей по адресу (лексикографическая сортировка полного адреса)");
        Comparator<Human> humanComparatorByAddress = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getAddress().toString().compareTo(o2.getAddress().toString());
            }
        };
        arrayList.sort(humanComparatorByAddress);
        System.out.println(arrayList);
        System.out.println("===========================");
        System.out.println("7. Создать класс User добавить в него поля ФИО, и роль которое является перечислением и содержит в себе ADMIN, USER, MODERATOR");
//        8. Необходимо написать метод в который входным значением будет являтся объект класса User, метод должен на основании
//           роли пользователя выводить приветственное сообщение, что-то вроде \"Приветствуем ФИО с ролью \" + ОПИСАНИЕ_РОЛИ"
//           описание роли должно вычисляться на основании роли пользователя, запрещено использовать if и switch, а так же описание роли в перечислении.

        User user = new User("Иванов Иван Иванович", User.Roles.ADMIN);
        user.greeting();

        System.out.println("===========================");
        System.out.println("9. Написать программу сортирующую HashMap по ключу. (Создание и генерация данными какими захотите)");

        HashMap hashMap = new HashMap();
        hashMap.put("key5", "value of key5");
        hashMap.put("key3", "value of key8");
        hashMap.put("key2", "value of key4");
        hashMap.put("key6", "value of key6");
        System.out.println(hashMap);

        ArrayList<String> arrayListOfKeys = new ArrayList<String>(hashMap.keySet());
        arrayListOfKeys.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String key : arrayListOfKeys) {
            System.out.println("key=" + key + ", value=" + hashMap.get(key));
        }
        System.out.println("===========================");
        System.out.println("10. Написать программу сортирующую HashMap по значнию. (Создание и генерация данными какими захотите)");
        ArrayList<HashMap.Entry> arrayList1 = new ArrayList<>(hashMap.entrySet());
        arrayList1.sort(new Comparator<HashMap.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return o1.getValue().toString().compareTo(o2.getValue().toString());
            }
        });
        for (HashMap.Entry arrayListElement : arrayList1) {
            System.out.println("key=" + arrayListElement.getKey() + ", value=" + arrayListElement.getValue());
        }

        System.out.println("===========================");
        System.out.println("11. Заполнить рандомными значениями LinkedList, вывести содержимое каждого элемента и его индекс.");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i=0; i<10; i++) {
            int v = (int)(Math.random()*100); linkedList.add(v);
            System.out.println("index=" + i + ", value=" + linkedList.get(i));
        }
    }
}
