package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ChatReader implements Runnable {
    private static final int MIN_SECONDS_BETWEEN_READS = 30;
    private static final int MAX_SECONDS_BETWEEN_READS = 50;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private ReentrantLock reentrantLock;
    private Chat chat;

    public ChatReader(Chat chat, ReentrantLock lock) {
        this.chat = chat;
        this.reentrantLock = lock;
    }

    @Override
    public void run() {
        reentrantLock.lock();
        Main.runningThreads++;
        reentrantLock.unlock();
        while (true) {
            try {
                int wait = (int) (Math.random() *
                        (MAX_SECONDS_BETWEEN_READS - MIN_SECONDS_BETWEEN_READS) * 1000) +
                        MIN_SECONDS_BETWEEN_READS * 1000;
                log.info("reader {} waits {} ms...", Thread.currentThread().getName(), wait);
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                log.info("reader {} wait interrupted {}", Thread.currentThread().getName(), e.getMessage());
            }
            reentrantLock.lock();
            if (chat.isMessageLimitReached() && chat.isEmpty()) {
                reentrantLock.unlock();
                break;
            }
            if (!chat.isEmpty()) {
                String message = chat.getMessage();
                log.info("reader {} gets message '{}'", Thread.currentThread().getName(), message);
            } else {
                log.info("chat is empty, reader {} waiting...", Thread.currentThread().getName());
            }
            reentrantLock.unlock();
        }
        log.info("reader {} terminated - message limit reached and chat is empty", Thread.currentThread().getName());
        reentrantLock.lock();
        Main.runningThreads--;
        reentrantLock.unlock();
    }
}
