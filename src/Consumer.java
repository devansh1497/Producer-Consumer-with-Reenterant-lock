
public class Consumer<E> {

    BlockingQueue<E> queue;

    public Consumer(BlockingQueue<E> queue) {
        this.queue = queue;
        new Thread(this::consume).start();
    }

    private void consume() {
        while (true) {
            System.out.println("Consumer: " + Thread.currentThread().getName() + " consumed message: "+ queue.get());
        }
    }
}
