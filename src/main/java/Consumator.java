import java.util.concurrent.BlockingQueue;

public class Consumator implements Runnable{
    BlockingQueue q;

    public Consumator (BlockingQueue q) {
        this.q = q;
    }

    public void run() {
        try {
            for (int i=0; i<5; i++) {
                Client c = (Client)q.take();
                System.out.println(c.id + " " + c.timp_sosire + " " + c.timp_servire);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
