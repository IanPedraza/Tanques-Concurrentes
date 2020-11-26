package tankes;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends JFrame {

    private final Tanque tanque1, tanque2;
    private final Nivel nivel1, nivel2;
    private final Agua agua1, agua2;
    
    private final Productor productor1;
    private final Consumidor consumidor1;

    private final ProductorSemaforo productor2;
    private final ConsumidorSemaforo consumidor2;

    private final Lock mutex;
    private final Semaphore semaphore;

    public Main() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setTitle("Tanques");

        /* MUTEX */
        nivel1 = new Nivel();
        agua1 = new Agua();
        tanque1 = new Tanque(agua1);
        mutex = new ReentrantLock();
        productor1 = new Productor(tanque1, nivel1, mutex);
        consumidor1 = new Consumidor(tanque1, nivel1, mutex);
        
        JPanel panelTanque = new JPanel();
        panelTanque.setLayout(new BorderLayout());
        panelTanque.add(tanque1, BorderLayout.CENTER);

        JLabel labelMutex = new JLabel("Mutex");
        labelMutex.setHorizontalAlignment(JLabel.CENTER);
        labelMutex.setVerticalAlignment(JLabel.CENTER);

        panelTanque.add(labelMutex, BorderLayout.NORTH);
        /* MUTEX */

        /* SEMAFOROS */
        semaphore = new Semaphore(1);
        nivel2 = new Nivel();
        agua2 = new Agua();
        tanque2 = new Tanque(agua2);
        productor2 = new ProductorSemaforo(tanque2, nivel2, semaphore);
        consumidor2 = new ConsumidorSemaforo(tanque2, nivel2, semaphore);

        JPanel panelTanque2 = new JPanel();
        panelTanque2.setLayout(new BorderLayout());
        panelTanque2.add(tanque2, BorderLayout.CENTER);

        JLabel labelSemaphore = new JLabel("Semaphore");
        labelSemaphore.setHorizontalAlignment(JLabel.CENTER);
        labelSemaphore.setVerticalAlignment(JLabel.CENTER);

        panelTanque2.add(labelSemaphore, BorderLayout.NORTH);

        /* SEMAFOROS */
        
        JPanel panelTanques = new JPanel();
        panelTanques.setLayout(new GridLayout());
        panelTanques.add(panelTanque);
        panelTanques.add(panelTanque2);
        
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(640, 20));
        
        add(spacer, BorderLayout.NORTH);
        add(panelTanques, BorderLayout.CENTER);

        productor1.start();
        consumidor1.start();

        productor2.start();
        consumidor2.start();
    }

    public static void main(String[] args) {
        Main fr = new Main();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
