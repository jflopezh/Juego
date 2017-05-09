package juego;

import javax.swing.JFrame;

/**
 *
 * @author Estudiante
 */
public class Juego extends JFrame {

    public Juego() {
        add(new Panel());
    }
    
    public static void main(String[] args) {
        Juego frame = new Juego();
        frame.setTitle("Juego");
        frame.setSize(1500, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
