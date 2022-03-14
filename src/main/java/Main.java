import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main (String[] args) {
        BlockingQueue q = new ArrayBlockingQueue(10);
        Producator p = new Producator(q);
        Consumator c = new Consumator(q);
        Thread thread1 = new Thread(p);
        thread1.start();
        Thread thread2 = new Thread(c);
        thread2.start();
        //c.run();
    }
}
