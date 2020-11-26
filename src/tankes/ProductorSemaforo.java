package tankes;

import java.awt.geom.*;
import java.util.concurrent.Semaphore;

public class ProductorSemaforo extends Thread {

    private final Nivel nivel;
    private final Tanque tanque;
    private final Semaphore semaphore;

    public ProductorSemaforo(Tanque panel, Nivel rc, Semaphore semaphore) {
        this.tanque = panel;
        this.nivel = rc;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            if (nivel.getNivel() > 50) {
                try {
                    if (semaphore.tryAcquire()) {
                        //semaphore.acquire();
                        System.out.println("Productor Semaphore");
                        tanque.getAgua().getAgua().add(new Rectangle2D.Double(50, nivel.getNivel(), 100, 5));
                        tanque.repaint();
                        nivel.setNivel(nivel.getNivel() - 5);
                        sleep((int) (500 + Math.random() * 200));
                        semaphore.release();
                    }
                } catch (Exception e) {
                    System.out.println("Error in productor Semaphore" + e.toString());
                }
            }
        }
    }
}
