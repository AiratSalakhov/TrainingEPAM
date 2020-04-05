package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static volatile Integer value = 0;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.start();
        task2.start();
        task3.start();
        try {
            task1.join();
            task2.join();
            task3.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("=========================");
        log.info("without race condition...");
        TaskSynchronized task4 = new TaskSynchronized();
        TaskSynchronized task5 = new TaskSynchronized();
        TaskSynchronized task6 = new TaskSynchronized();
        task4.start();
        task5.start();
        task6.start();
        try {
            task4.join();
            task5.join();
            task6.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("=========================");
        log.info("DEADLOCK...");
        Account accountFrom = new Account(1000);
        Account accountTo = new Account(1);
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
        Thread task7 = new Thread(() -> AccountTransaction.transfer(accountFrom, accountTo, 150));
        Thread task8 = new Thread(() -> AccountTransaction.transfer(accountTo, accountFrom, 50));
        task7.start();
        task8.start();
        try {
            for (int i = 0; i < 10; i++) {
                log.info("Waiting for completion in {} sec", 10 - i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e.getMessage());
        }
        if (task7.isAlive() && task8.isAlive()) {
            log.info("{} and {} in Deadlock state - not finished in 10 sec... Interrupt program manually !!!",
                    task7.getName(), task8.getName());
        }
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
        log.info("=========================");
        log.info("W/O DEADLOCK...");
        Thread task9 = new Thread(() -> AccountTransactionWoDedlock.transfer(accountFrom, accountTo, 150));
        Thread task10 = new Thread(() -> AccountTransactionWoDedlock.transfer(accountTo, accountFrom, 50));
        task9.start();
        task10.start();
        try {
            task9.join();
            task10.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
    }
}
