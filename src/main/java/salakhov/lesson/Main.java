package salakhov.lesson;


import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            String inputLine;
            Scanner scanner = new Scanner(System.in);
            DbProcessor.connect();
            forExit:
            while (true) {
                System.out.println("Таблица products (prod_id, category, title, actor, price, special, common_prod_id)");
                System.out.println("1 - добавить запись, <пробел> значение_title (остальное по умолчанию)");
                System.out.println("2 - изменить запись, <пробел> prod_id <пробел> новое_значение_title");
                System.out.println("3 - просмотреть запись, <пробел> искомая_строка в title");
                System.out.println("4 - удалить запись, <пробел> prod_id");
                System.out.println("5 - выход");
                inputLine = scanner.nextLine().trim();
                Scanner scanner1 = new Scanner(inputLine);
                if (!scanner1.hasNextInt()) {
                    continue;
                }
                int command = scanner1.nextInt();
                switch (command) {
                    case (1): {
                        if (!scanner1.hasNextLine()) {
                            System.out.println("Не указан title!");
                            break;
                        }
                        String newTitle = scanner1.nextLine().trim();
                        DbProcessor.insert(newTitle);
                        break;
                    }
                    case (2): {
                        if (!scanner1.hasNextInt()) {
                            System.out.println("Не указан prod_id!");
                            break;
                        }
                        int productId = scanner1.nextInt();
                        String newTitle = scanner1.nextLine().trim();
                        if (!DbProcessor.isProdIdExists(productId)) {
                            System.out.println("Не найден prod_id!");
                            break;
                        }
                        DbProcessor.update(newTitle, productId);
                        break;
                    }
                    case (3): {
                        String searchString = scanner1.hasNext() ? ("WHERE title LIKE '%" +
                                scanner1.nextLine().trim() + "%'") : "";
                        for (String str : DbProcessor.search(searchString)) {
                            System.out.println(str);
                        }
                        break;
                    }
                    case (4): {
                        if (!scanner1.hasNextInt()) {
                            System.out.println("Не указан prod_id!");
                            break;
                        }
                        int productId = scanner1.nextInt();
                        if (!DbProcessor.isProdIdExists(productId)) {
                            System.out.println("Не найден prod_id!");
                            break;
                        }
                        DbProcessor.delete(productId);
                        break;
                    }
                    case (5): {
                        break forExit;
                    }
                }
            }
        } catch (SQLException e) {
            log.info("SQL Exception {}", e.getMessage());
        } finally {
            try {
                DbProcessor.disconnect();
            } catch (SQLException e) {
                log.info("SQL Exception on disconnect {}", e.getMessage());
            }
        }
        log.info("Приложение завершено!");
    }
}
