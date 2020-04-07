package salakhov.lesson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatWriter {
    private static final int MIN_SECONDS_BETWEEN_WRITES = 20;
    private static final int MAX_SECONDS_BETWEEN_WRITES = 60;
    private static Logger log = LoggerFactory.getLogger(Main.class.getName());
    private Chat chat;

    public ChatWriter(Chat chat) {
        this.chat = chat;
    }

    public void writeToChat() {
        while (!chat.isMessageLimitReached()) {
            try {
                Thread.currentThread().sleep((int) (Math.random() *
                        (MAX_SECONDS_BETWEEN_WRITES - MIN_SECONDS_BETWEEN_WRITES) * 1000) +
                        MIN_SECONDS_BETWEEN_WRITES * 1000);
            } catch (InterruptedException e) {

            }

        }
    }
}
