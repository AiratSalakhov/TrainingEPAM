package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ChatWriter implements Runnable {
    private static final int MIN_SECONDS_BETWEEN_WRITES = 20;
    private static final int MAX_SECONDS_BETWEEN_WRITES = 60;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private ReentrantLock reentrantLock;
    private Chat chat;

    public ChatWriter(Chat chat, ReentrantLock lock) {
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
                        (MAX_SECONDS_BETWEEN_WRITES - MIN_SECONDS_BETWEEN_WRITES) * 1000) +
                        MIN_SECONDS_BETWEEN_WRITES * 1000;
                log.info("writer {} waits {} ms...", Thread.currentThread().getName(), wait);
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                log.info("Writer {} wait interrupted {}", Thread.currentThread().getName(), e.getMessage());
            }
            reentrantLock.lock();
            if (chat.isMessageLimitReached()) {
                reentrantLock.unlock();
                break;
            }
            if (!chat.isFull()) {
                log.info("writer {} puts message", Thread.currentThread().getName());
                chat.putMessage("writer " + Thread.currentThread().getName() + " message");
            } else {
                log.info("chat is full, writer {} waiting...", Thread.currentThread().getName());
            }
            reentrantLock.unlock();
        }
        log.info("writer {} terminated - message limit reached", Thread.currentThread().getName());
        reentrantLock.lock();
        Main.runningThreads--;
        reentrantLock.unlock();
    }
}
