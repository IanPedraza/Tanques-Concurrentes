package tankes;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

public class ConsumidorSemaforo extends Thread {

    private final Nivel nivel;
    private final Tanque tanque;
    private final Semaphore semaphore;

    public ConsumidorSemaforo(Tanque panel, Nivel rc, Semaphore semaphore) {
        this.tanque = panel;
        this.nivel = rc;
        this.semaphore = semaphore;

    }

    @Override
    public void run() {
        while (true) {
            if (nivel.getNivel() < 245) {
                try {
                    if (semaphore.tryAcquire()) {
                        //semaphore.acquire();
                        System.out.println("Consumidor Semaphore");
                        tanque.getAgua().getAgua().remove(tanque.getAgua().getAgua().size() - 1);
                        tanque.repaint();
                        nivel.setNivel(nivel.getNivel() + 5);
                        sleep((int) (500 + Math.random() * 200));
                        semaphore.release();
                    }
                } catch (Exception e) {
                    System.out.println("Error in consumidor Semaphore" + e.toString());
                }
            }
        }
    }
}
