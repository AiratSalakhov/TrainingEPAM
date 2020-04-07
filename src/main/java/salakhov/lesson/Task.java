package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends Thread {
    private Logger log = LoggerFactory.getLogger(Main.class.getName());

    @Override
    public void run() {
        int oldValue;
        int newValue;
        System.out.println(this.getName() + " started!");
        for (int i = 0; i < 100; i++) {
            oldValue = Main.value;
            try {
                sleep((int) (Math.random() * 10));
            } catch (InterruptedException e) {
                log.info("Interrupted {} ", this.getName());
            }
            newValue = ++Main.value;
            if (oldValue + 1 != newValue) {
                log.info("Race condition {} + 1 = {} in ()", oldValue, newValue, this.getName());
            }
        }
        System.out.println(this.getName() + " stopped!");
    }
}

