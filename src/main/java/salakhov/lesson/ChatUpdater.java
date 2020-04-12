package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ChatUpdater implements Runnable {
    private static final int MIN_SECONDS_BETWEEN_UPDATES = 40;
    private static final int MAX_SECONDS_BETWEEN_UPDATES = 40;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private ReentrantLock reentrantLock;
    private Chat chat;

    public ChatUpdater(Chat chat, ReentrantLock lock) {
        this.reentrantLock = lock;
        this.chat = chat;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        Main.runningThreads++;
        reentrantLock.unlock();
        while (true) {
            try {
                int wait = (int) (Math.random() *
                        (MAX_SECONDS_BETWEEN_UPDATES - MIN_SECONDS_BETWEEN_UPDATES) * 1000) +
                        MIN_SECONDS_BETWEEN_UPDATES * 1000;
                log.info("updater {} waits {} ms...", Thread.currentThread().getName(), wait);
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                log.info("updater {} wait interrupted {}", Thread.currentThread().getName(), e.getMessage());
            }
            reentrantLock.lock();
            if (chat.isMessageLimitReached() && chat.isEmpty()) {
                reentrantLock.unlock();
                break;
            }
            if (!chat.isEmpty()) {
                String newMessage = "updater " + Thread.currentThread().getName() + " new message " +
                        (int) (Math.random() * 100);
                String message = chat.updateMessage(newMessage);
                log.info("updater {} updated message '{}' to '{}'", Thread.currentThread().getName(), message, newMessage);
            } else {
                log.info("chat is empty, updater {} waiting...", Thread.currentThread().getName());
            }
            reentrantLock.unlock();
        }
        log.info("updater {} terminated - message limit reached and chat is empty", Thread.currentThread().getName());
        reentrantLock.lock();
        Main.runningThreads--;
        reentrantLock.unlock();
    }
}
