package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountTransactionWoDedlock {
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static synchronized void transfer(Account accountFrom, Account accountTo, int amount) {
        log.info("Transfer started in {} with {}", Thread.currentThread().getName(), amount);
        accountFrom.setAccout(accountFrom.getAccout() - amount);
        accountTo.setAccout(accountTo.getAccout() + amount);
        System.out.println(Thread.currentThread().getName() + " transfer completed!");
    }
}
