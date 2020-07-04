import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * class for the game loop
 */
class GameLoop{


    //objects
    private Output output;
    private JFrame jFrame;

    private Menu menu = new Menu();
    private SinglePlayer single = new SinglePlayer();

    private fpsTracker fps = new fpsTracker();
    private Test test = new Test();

    //constructor
    GameLoop(){
        init();
    }


    void init() {
        settings();
    }

    void settings() {

        jFrame = new JFrame();    // create a jframe
        output = new Output();    // output object

        jFrame.setTitle("LaserShipGame");
        jFrame.setSize(1524, 1057);
        // real size is 1500 X 1000
        // but have to set to 1524 X 1057 so that it starts at 1500 X 1000
        // because JPanel ???

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(output);    // add output object to jframe
        jFrame.setVisible(true);


    }

    /**
     * pause the current thread
     * @param time amount of time to sleep in milliseconds
     */
    void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {

        }
    }


    /**
     * main game loop for everything
     */
    void gameLoop() {

        fps.time();    // print the fps count

        while (GameVariables.inGame) {    // while in game

            GameVariables.frame++;

            if (GameVariables.menu) {
                menu.menuLoop();
            }

            if (GameVariables.single) {
                single.singleLoop();
            }

            jFrame.repaint();
            delay(7);
        }

        jFrame.setVisible(false);    // close the jFrame after user quit

    }


    /**
     * class for key inputs
     */
    private class KeyInput implements KeyListener{

        //loop for inputs

        @Override
        public void keyPressed(KeyEvent e) {
            menu.keys(e);

            single.move(e, true);

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            menu.keys(e);

            single.move(e, false);

        }

    }

    /**
     * class for mouse inputs
     */
    private class MouseInput implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            if(GameVariables.menu){
                menu.click(e);
                System.out.println("clicked");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    /**
     * class for graphics outputs
     */
    class Output extends JPanel {

        Output() {    // add keylistener and mouselistener temporarily to output object, and add output object to jframe

            KeyListener listener = new KeyInput();
            MouseListener mouseListener = new MouseInput();
            addKeyListener(listener);
            addMouseListener(mouseListener);
            setFocusable(true);

        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            // display graphics
            if (GameVariables.menu) {

                menu.draw(g);
            }
            if (GameVariables.single) {

                single.draw(g);
            }

            test.draw(g);

        }
    }

}
