package tankes;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Tanque extends JPanel {

    private Agua agua;

    public Tanque(Agua agua) {
        this.agua = agua;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.BLACK);
        g2.draw(new Rectangle2D.Double(50, 50, 100, 200));
        
        g2.setColor(Color.BLUE);
        
        for (int i = 0; i < agua.getAgua().size(); i++) {
            g2.fill((Rectangle2D) agua.getAgua().get(i));
        }
    }

    public Agua getAgua() {
        return agua;
    }

    public void setAgua(Agua agua) {
        this.agua = agua;
    }
}
