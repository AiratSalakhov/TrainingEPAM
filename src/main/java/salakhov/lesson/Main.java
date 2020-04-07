package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static volatile Integer value = 0;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("=========================");
        log.info("race condition...");
        Task task0 = new Task();
        Task task1 = new Task();
        Task task2 = new Task();
        task0.start();
        task1.start();
        task2.start();
        try {
            task0.join();
            task1.join();
            task2.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("=========================");
        log.info("without race condition...");
        TaskSynchronized task3 = new TaskSynchronized();
        TaskSynchronized task4 = new TaskSynchronized();
        TaskSynchronized task5 = new TaskSynchronized();
        task3.start();
        task4.start();
        task5.start();
        try {
            task3.join();
            task4.join();
            task5.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("=========================");
        log.info("DEADLOCK...");
        Account accountFrom = new Account(1000);
        Account accountTo = new Account(1);
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
        Thread task6 = new Thread(() -> AccountTransaction.transfer(accountFrom, accountTo, 150));
        Thread task7 = new Thread(() -> AccountTransaction.transfer(accountTo, accountFrom, 50));
        task6.start();
        task7.start();
        try {
            for (int i = 0; i < 10; i++) {
                log.info("Waiting for completion in {} sec", 10 - i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e.getMessage());
        }
        if (task6.isAlive() && task7.isAlive()) {
            log.info("{} and {} in Deadlock state - not finished in 10 sec... Interrupt program manually !!!",
                    task6.getName(), task7.getName());
        }
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
        log.info("=========================");
        log.info("W/O DEADLOCK...");
        Thread task8 = new Thread(() -> AccountTransactionWoDedlock.transfer(accountFrom, accountTo, 150));
        Thread task9 = new Thread(() -> AccountTransactionWoDedlock.transfer(accountTo, accountFrom, 50));
        task8.start();
        task9.start();
        try {
            task8.join();
            task9.join();
        } catch (InterruptedException e) {
            log.info("Interrupted main thread! {}", e);
        }
        log.info("Amount of accounts: acc1={}, acc2={}", accountFrom, accountTo);
        /*
        FutureTask<String> futureTask = new FutureTask<>(() -> { return "callable"; });
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.execute(futureTask);
String str = futureTask.get();
         */
    }
}
