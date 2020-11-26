package tankes;

import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Productor extends Thread {

    private final Nivel nivel;
    private final Tanque tanque;
    private final Lock mutex;

    public Productor(Tanque panel, Nivel rc, Lock mutex) {
        this.tanque = panel;
        this.nivel = rc;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            if (nivel.getNivel() > 50) {
                if (mutex.tryLock()) {
                    try {
                        //mutex.lock();
                        System.out.println("Productor Mutex");
                        tanque.getAgua().getAgua().add(new Rectangle2D.Double(50, nivel.getNivel(), 100, 5));
                        tanque.repaint();
                        nivel.setNivel(nivel.getNivel() - 5);
                        sleep((int) (500 + Math.random() * 200));
                    } catch (Exception e) {
                        System.out.println("Productor no puede acceder al recurso compartido");
                    } finally {
                        mutex.unlock();
                        //System.out.println("Unlocked by productor Mutex");
                    }
                }

            }
        }
    }
}
