import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<E> {

    private final int maxSize;
    private final ReentrantLock lock;
    private final Queue<E> queue;
    private final Condition isNotFull;
    private final Condition isNotEmpty;


    public BlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.lock = new ReentrantLock();
        this.queue = new LinkedList<>();
        this.isNotEmpty = lock.newCondition();
        this.isNotFull = lock.newCondition();
    }

    public void put(E element) {
        lock.lock();
        try {
            while (queue.size() == maxSize) {
                try {
                    isNotFull.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread() + " produced message: " + element);
            queue.add(element);
            isNotEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E get() {
        lock.lock();
        E element;
        try {
            while (queue.isEmpty()) {
                try {
                    isNotEmpty.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            element = queue.poll();
            isNotFull.signalAll();
        } finally {
            lock.unlock();
        }
        return element;
    }
}
