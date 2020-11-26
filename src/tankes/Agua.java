package tankes;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Agua {

    private ArrayList<Rectangle2D> agua;
    
    public Agua() {
        this.agua = new ArrayList();
    }

    public ArrayList<Rectangle2D> getAgua() {
        return agua;
    }

    public void setAgua(ArrayList<Rectangle2D> agua) {
        this.agua = agua;
    }

}
