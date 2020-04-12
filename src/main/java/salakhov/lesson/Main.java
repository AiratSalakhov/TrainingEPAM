package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int WRITERS_NUMBER = 20;
    private static final int READERS_NUMBER = 10;
    private static final int UPDATERS_NUMBER = 10;
    public static volatile Integer value = 0;
    public static volatile int runningThreads;
    private static Chat chat = new Chat();
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
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

        log.info("=========================");
        log.info("CHAT...");
        ExecutorService executor = Executors.newFixedThreadPool(WRITERS_NUMBER + READERS_NUMBER + UPDATERS_NUMBER);
        for (int i = 0; i < WRITERS_NUMBER; i++) {
            executor.execute(new ChatWriter(chat, lock));
        }
        for (int i = 0; i < READERS_NUMBER; i++) {
            executor.execute(new ChatReader(chat, lock));
        }
        for (int i = 0; i < UPDATERS_NUMBER; i++) {
            executor.execute(new ChatUpdater(chat, lock));
        }
        while (true) {
            Thread.sleep(10000);
            lock.lock();
            int running = runningThreads;
            lock.unlock();
            log.info("MAIN - running threads {}", running);
            if (running == 0) {
                break;
            }
        }
        executor.shutdown();
        log.info("CHAT completed.");
    }
}
