import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



class GameLoop {


    //objects
    Output output;
    JFrame jFrame;

    Menu menu = new Menu();
    SinglePlayer single = new SinglePlayer();

    fpsTracker fps = new fpsTracker();

    //constructor
    GameLoop(){
        init();
    }

    //main game loop for everything
    void gameLoop() {

        fps.time();
        while (GameVariables.inGame) {

            GameVariables.frame++;

            if (GameVariables.menu) {
                menu.menuLoop();
                System.out.println("menu");
            }

            if (GameVariables.single) {
                single.singleLoop();
            }

            jFrame.repaint();
            delay(7);
        }
    }

    void init() {
        settings();
    }

    void settings() {

        jFrame = new JFrame();
        output = new Output();

        output = new Output();

        jFrame.setTitle("LaserShipGame");
        jFrame.setSize(1500, 1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.add(output);



    }

    void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {

        }
    }


    class Output extends JPanel {

        Output() {

            KeyListener listener = new KeyInput();
            addKeyListener(listener);
            setFocusable(true);

        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            //loop for displaying graphics
            if (GameVariables.menu) {

                menu.draw(g);
            }
            if (GameVariables.single) {

                single.draw(g);
            }
        }
    }

    /**
     * class for key inputs
     */
    private class KeyInput implements KeyListener {

        //loop for inputs

        @Override
        public void keyPressed(KeyEvent e) {

            menu.move(e, true);
            menu.keys(e);

            single.move(e, true);

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

            menu.move(e, false);
            menu.keys(e);

            single.move(e, false);

        }
    }
}
