package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
        countParadoP++;
        if (saltando || caida) {
            if (count == 0) {
                yini = y;
            }
            count++;            
            if (caida) {
                y = 5 * count * count;
            } else {
                y = yini + ((-50 * count) + (5 * count * count));
            }
        } else if (countParadoP % 2 == 0) {
            paradoP = (countParadoP / 2) % 2;
        }
        if (estado == 2 || estado == 3) {
            if (!saltando) {
                caminarP++;
                if (caminarP == 4) {
                    caminarP = 0;
                }
            }
            if (estado == 2 && detenido != 2) {
                if (x < 450) {
                    x += 15;
                } else if (xBg > -1035) {
                    xBg -= 15;
                } else if (x < 1020) {
                    x += 15;
                }
            } else if (estado == 3 && detenido != 1) {
                if (x > 450) {
                    x -= 15;
                } else if (xBg < 0) {
                    xBg += 15;
                } else if (x > -20) {
                    x -= 15;
                }
            }
        }
        checkCollisions();
        repaint();
        
    }
    
    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    estado = 1;
                    caminarP = 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    estado = 0;
                    caminarP = 1;
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
                    if (detenido == 2) {
                        detenido = 0;
                    }
                    estado = 3;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (detenido == 1) {
                        detenido = 0;
                    }
                    estado = 2;
                    break;
                case KeyEvent.VK_UP:
                    saltando = true;
                    montado = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println(y);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
    }
    
    private ArrayList<Moneda> coins;
    private int[] piedras;
    private int caminarP, paradoP, xBg, x, y, yini, monedasRecogidas, count, countParadoP;
    private Timer timer;
    private int estado;
    private int montado;
    private boolean saltando;
    private boolean caida;
    private int detenido;
    
    public Panel() {
        this.coins = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            coins.add(new Moneda(((int) (Math.random()*40+1))*55, 490));
        }
        this.piedras = new int[10];
        for (int i = 0; i < 10; i++) {
            piedras[i] = ((int) (Math.random()*30+4))*65;
        }
        this.estado = 0;
        this.monedasRecogidas = 0;
        this.caminarP = 1;
        this.montado = -1;
        this.saltando = false;
        this.caida = false;
        this.count = 0;
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.x = 0;
        this.y = 0;
        this.timer = new Timer(50, this);
        timer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 2201; i += 22) {
            g.drawImage(loadImage("blue_background.png"), (xBg + i), 0, this);
        }
        g.setColor(Color.WHITE);
        Font f = new Font("xxx", Font.BOLD, 40);
        g.setFont(f);
        g.drawImage(loadImage("coin.png"), 1070, 18, 40, 40, this);
        g.drawString(String.valueOf(monedasRecogidas), 1125, 52);
        for (int i = 0; i < 2241; i += 112) {
            g.drawImage(loadImage("ground_loop.png"), (xBg + i), 552, this);
        }
        for (int i = 0; i < 2346; i += 335) {
            g.drawImage(loadImage("clouds.png"), (xBg + i), 150, this);
        }
        for (Moneda m : coins) {
            g.drawImage(loadImage("coin.png"), m.getX() + xBg, m.getY(), this);
        }
        for (int i = 0; i < 10; i++) {
            g.drawImage(loadImage("stone.png"), piedras[i] + xBg, 494, 65, 59, this);
        }
        if (saltando && (estado == 0 || estado == 2)) {
            g.drawImage(loadImage("jump.png"), (x + 50), (y + 410), (x + 146), (y + 552), 0, 0, 96, 142, this);
        } else if (saltando && (estado == 1 || estado == 3)) {
            g.drawImage(loadImage("jump.png"), (x + 146), (y + 410), (x + 50), (y + 552), 0, 0, 96, 142, this);
        } else {
            switch (this.estado) {
                case 0:
                    g.drawImage(loadImage("standing.png"), (x + 50), (y + 419), (x + 145), (y + 552), (142 * paradoP), 0, ((142 * paradoP) + 95), 133, this);
                    break;
                case 1:
                    g.drawImage(loadImage("standing.png"), (x + 145), (y + 419), (x + 50), (y + 552), (142 * paradoP), 0, ((142 * paradoP) + 95), 133, this);
                    break;
                case 2:
                    g.drawImage(loadImage("walking.png"), (x + 50), (y + 418), (x + 158), (y + 552), (124 * caminarP-10), 0, ((124 * caminarP) + 98), 134, this);
                    break;
                case 3:
                    g.drawImage(loadImage("walking.png"), (x + 158), (y + 418), (x + 50), (y + 552), (124 * caminarP-10), 0, ((124 * caminarP) + 98), 134, this);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    public Rectangle getLimitesP() {
        return new Rectangle(x + 50, y + 418, 98, 134);
    }
    
    public void checkCollisions() {
        Rectangle personaje = getLimitesP();
        if (personaje.intersectsLine(0, 552, 2200, 552)) {
            saltando = false;
            caida = false;
            count = 0;
            y = 0;
        }
        Rectangle coin;
        for (int i = 0; i < coins.size(); i++) {
            coin = new Rectangle(coins.get(i).getX() + xBg, coins.get(i).getY(), 55, 55);
            if (personaje.intersects(coin)) {
                coins.remove(coins.get(i));
                monedasRecogidas++;
            }
        }
        Rectangle pies = new Rectangle(x + 50, y + 537, 98, 15);
        Rectangle piedra;
        if (montado != -1 && !saltando) {
            piedra  = new Rectangle(piedras[montado] + xBg, 490, 65, 10);
            if (!pies.intersects(piedra)) {
                count = 0;
                caida = true;
                montado = -1;
            }
        }
        if (montado == -1 || caida) {
            for (int i = 0; i < 10; i++) {
                piedra = new Rectangle(piedras[i] + xBg, 490, 65, 20);
//                if (personaje.intersectsLine(piedras[i] + xBg, 552, piedras[i] + xBg - 10, 484)) {
//                    detenido = 2;
//                }
//                if (personaje.intersectsLine(piedras[i] + xBg + 75, 552, piedras[i] + xBg + 75, 584)) {
//                    detenido = 1;
//                }
                if (pies.intersects(piedra)) {
                    saltando = false;
                    count = 0;
                    y = -58; 
                    caida = false;
                    montado = i;
                }
            }
        }
    }
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
