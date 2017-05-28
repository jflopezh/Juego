package juego;

import java.awt.Rectangle;

/**
 *
 * @author aleja
 */
public class Moneda {
    private int x;
    private int y;

    public Moneda(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Rectangle getLimites() {
        return new Rectangle(x, y, 55, 55);
    }
}
