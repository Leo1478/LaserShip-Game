import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Menu extends JPanel implements KeyListener {


    //keys for menu

    private BufferedImage SingleButton;

    String imagePath = "images\\";


    void menuLoop() {



    }

    void loadImage(){
        try {
            SingleButton = ImageIO.read(new File("F:\\downloads\\LaserShip-Game-master\\LaserShip-Game-master\\LaserShipGame\\images\\Single.png"));
        } catch (IOException e) {
            System.out.println("can't load image");
        }
    }


    void drawSingleButton(Graphics g){

        g.drawImage(SingleButton, 0, 0, null);

    }



    void draw(Graphics g) {

        loadImage();

        drawScreen(g);
        drawSingleButton(g);
    }

    private void drawScreen(Graphics g) {

        g.setColor(Color.cyan);
        g.fillRect(100, 100, 1300, 800);
    }


    @Override
    public void keyPressed(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    void keys(KeyEvent e) {

        select(e);

    }

    //use parameter true for keyPressed, false for keyReleased
    void move(KeyEvent e, boolean bool) {

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            GameVariables.up = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GameVariables.down = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            GameVariables.left = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            GameVariables.right = bool;
        }


    }

    void select(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_S) {
            GameVariables.single = true;
            GameVariables.menu = false;
            GameVariables.multi = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_M) {
            GameVariables.multi = true;
            GameVariables.menu = false;
            GameVariables.single = false;
        }


    }


}
