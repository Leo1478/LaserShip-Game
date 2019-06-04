import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;


class Menu extends JPanel implements KeyListener {


    LaserShip l;
    GameLoop g;

    JButton singleButton;
    JButton multiButton;
    JLabel text;
    JPanel panel;

    //keys for menu





    void menuLoop() {



    }



    void buttons(JPanel panel){

        text = new JLabel("trsjghxdfbvncvhmnhmghjdghfthdfh");
        text.setPreferredSize(new Dimension(100, 900));
        panel.add(text);

        singleButton = new JButton("single player" + "dfghtf hdgf jyj jfhdf h");
        panel.add(singleButton);
    }


    void draw(Graphics g) {


        //drawScreen(g);
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
