import java.util.UUID;

public class StringProducer implements Producer{

    BlockingQueue<String> queue;

    public StringProducer(BlockingQueue<String> queue) {
        this.queue = queue;
        new Thread(this::produce).start();
    }

    public void produce() {
        while (true) {
            String msg = UUID.randomUUID().toString();
            queue.put(msg);
        }
    }
}
