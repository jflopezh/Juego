package juego;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 */
public class Panel extends JPanel implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (paradoP == 1) {
            paradoP = 0;
        } else {
            paradoP = 1;
        }
        t = true;
        x+=20;
        caminarP++;
        if (caminarP == 4) {
            caminarP = 0;
        }
        repaint();
    }
    
    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:

                    break;
                case KeyEvent.VK_RIGHT:

                    break;
                case KeyEvent.VK_UP:

                    break;
                case KeyEvent.VK_DOWN:

                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    
                    break;
                case KeyEvent.VK_RIGHT:
                    
                    break;
                case KeyEvent.VK_UP:

                    break;
                case KeyEvent.VK_DOWN:

                    break;
                default:
                    throw new AssertionError();
            }
        }
        
    }
    
    private int caminarP, paradoP, xBg, x, y;
    private Timer timer;
    boolean t;
    
    public Panel() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.x = 0;
        this.y = 70;
        this.timer = new Timer(100, this);
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 2201; i+=22) {
            g.drawImage(loadImage("blue_background.png"), (xBg + i), 0, this);
        }
        for (int i = 0; i < 2201; i+=112) {
            g.drawImage(loadImage("ground_loop.png"), (xBg + i), 712, this);
        }
        int[] f = new int[41];
        if (!t) {
            for (int h : f) {
                h = (int) (Math.random()*7+1);
            }
        }
        int k = 0;
        for (int i = 0; i < 2201; i+=55) {
            if (f[k] == 7) {
                g.drawImage(loadImage("coin.png"), (xBg + i), 647, this);
            }
            k++;
        }
//        g.drawImage(loadImage("standing.png"), (x + 50), 579, (x + 168), 712, (142 * paradoP), 0, ((142 * paradoP) + 142), 133, this);
        g.drawImage(loadImage("walking.png"), (x + 50), 578, (x + 168), 712, (124 * caminarP-20), 0, ((124 * caminarP) + 98), 134, this);
    }
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
