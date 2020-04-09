package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Chat {
    private static final int CHAT_VOLUME = 25;
    private static final int MESSAGE_LIMIT = 100;
    private static volatile int messageCounter;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private static ConcurrentLinkedQueue<String> stringQueue =
            new ConcurrentLinkedQueue<String>(new ArrayList<>(CHAT_VOLUME));

    public boolean isEmpty() {
        return stringQueue.isEmpty();
    }

    public boolean isFull() {
        return stringQueue.size() == CHAT_VOLUME;
    }

    public boolean isMessageLimitReached() {
        return messageCounter >= MESSAGE_LIMIT;
    }

    public String updateMessage(String newMessage) {
        int messageNumber = (int) (Math.random() * (stringQueue.size() - 1)) + 1;
        log.info("CHAT - updater {} will change message number {}, chat size {}, message count {}/{}", Thread.currentThread().getName(),
                messageNumber, stringQueue.size(), messageCounter, MESSAGE_LIMIT);
        ConcurrentLinkedQueue<String> stringQueueNew =
                new ConcurrentLinkedQueue<String>(new ArrayList<>(CHAT_VOLUME));
        String oldMessage = "";
        while (stringQueue.size() > 0) {
            String message = stringQueue.poll();
            if (--messageNumber == 0) {
                oldMessage = message;
                message = newMessage;
            }
            stringQueueNew.add(message);
        }
        stringQueue = stringQueueNew;

        log.info("CHAT - updater {} changes old message '{}' to '{}', chat size {}, message count {}/{}", Thread.currentThread().getName(),
                oldMessage, newMessage, stringQueue.size(), messageCounter, MESSAGE_LIMIT);
        return oldMessage;
    }

    public String getMessage() {
        String message = stringQueue.poll();
        log.info("CHAT - reader {} gets message '{}', chat size {}, message count {}/{}", Thread.currentThread().getName(),
                message, stringQueue.size(), messageCounter, MESSAGE_LIMIT);
        return message;
    }

    public void putMessage(String message) {
        stringQueue.add(message);
        messageCounter++;
        log.info("CHAT - writer {} puts message '{}', chat size {}, message count {}/{}", Thread.currentThread().getName(),
                message, stringQueue.size(), messageCounter, MESSAGE_LIMIT);
    }
}
