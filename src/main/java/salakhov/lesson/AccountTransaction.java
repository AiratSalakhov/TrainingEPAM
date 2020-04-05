package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountTransaction {
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void transfer(Account accountFrom, Account accountTo, int amount) {
        log.info("Transfer started in {} with {}", Thread.currentThread().getName(), amount);
        log.info("Entering synchronized block accounFrom in {}", Thread.currentThread().getName());
        synchronized (accountFrom) {
            log.info("Entered synchronized block accounFrom in {}", Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.info("Transfer interrupted while sleeping in {}", Thread.currentThread().getName());
            }
            log.info("Entering synchronized block accounTo in {}", Thread.currentThread().getName());
            synchronized (accountTo) {
                log.info("Entered synchronized block accounTo in {}", Thread.currentThread().getName());
                accountFrom.setAccout(accountFrom.getAccout() - amount);
                accountTo.setAccout(accountTo.getAccout() + amount);
            }
        }
        System.out.println(Thread.currentThread().getName() + " transfer completed!");
    }
}
