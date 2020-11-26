package tankes;

import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumidor extends Thread {

    private final Nivel nivel;
    private final Tanque tanque;
    private final Lock mutex;

    public Consumidor(Tanque panel, Nivel rc, Lock mutex) {
        this.tanque = panel;
        this.nivel = rc;
        this.mutex = mutex;
    }

    
    @Override
    public void run() {
        while (true) {
            if (nivel.getNivel() < 245) {
                if (mutex.tryLock()) {
                    try {
                        //mutex.lock();
                        System.out.println("Consumidor Mutex");
                        tanque.getAgua().getAgua().remove(tanque.getAgua().getAgua().size() - 1);
                        tanque.repaint();
                        nivel.setNivel(nivel.getNivel() + 5);
                        sleep((int) (500 + Math.random() * 200));
                    } catch (Exception e) {
                        System.out.println("Unlocked by consumidor mutex");
                    } finally {
                        mutex.unlock();
                        //System.out.println("Unlocked by consumidor Mutex");
                    }
                }
            }
        }
    }
}
