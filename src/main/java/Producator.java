import com.sun.jdi.event.ThreadStartEvent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producator implements Runnable {
    BlockingQueue q;

    public Producator(BlockingQueue q) {
        this.q = q;
    }

    public void run() {
        Random r = new Random();
        Client c;
        for (int i=0; i<5; i++) {
            int nr1 = r.nextInt(10);
            int nr2 = r.nextInt(10);
            c = new Client(i, nr1, nr2);
            try {
                Thread.sleep(2000);
                q.put(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
