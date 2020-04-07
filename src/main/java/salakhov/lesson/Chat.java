package salakhov.lesson;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Chat {
    private static final int CHAT_VOLUME = 25;
    private static final int MESSAGE_LIMIT = 100;
    private static int messageCounter;
    private ConcurrentLinkedQueue<String> stringQueue =
            new ConcurrentLinkedQueue<String>(new ArrayList<>(CHAT_VOLUME));

    public boolean isEmpty() {
        return stringQueue.isEmpty();
    }

    public boolean isFull() {
        return stringQueue.size() == CHAT_VOLUME;
    }
    public boolean isMessageLimitReached() {
        return messageCounter>=MESSAGE_LIMIT;
    }
}
