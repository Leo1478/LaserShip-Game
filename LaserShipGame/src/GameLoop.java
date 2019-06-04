import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



class GameLoop {


    

    //objects
    Output output;
    JFrame j;
    JPanel panel;
    KeyListener listener;

    LaserShip l;
    GameLoop g;
    Menu menu = new Menu();
    SinglePlayer single = new SinglePlayer();
    MultiPlayer multi = new MultiPlayer();

    fpsTracker fps = new fpsTracker();


    //constructor
    GameLoop(){

        init();
    }

    //main game loop for everything
    void gameLoop() {

        //init();

        fps.time();


        while (GameVariables.inGame) {

            GameVariables.frame++;

            if (GameVariables.menu) {

                menu.menuLoop();


            }


            if (GameVariables.single) {
                single.SingleLoop();

            }


            if (GameVariables.multi) {

                multi.MultiLoop();

            }


            j.repaint();
            delay(7);


        }

    }

    void init() {

        settings();

    }

    void settings() {




        j = new JFrame();
        output = new Output();
        panel = new JPanel();
        Output o = new Output();

        j.getContentPane().add(BorderLayout.CENTER, output);

        j.setTitle("LaserShipGame");
        j.setSize(1500, 1000);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        j.add(o);

        panel.setBackground(Color.PINK);


        JButton jb = new JButton("test");

        panel.add(jb);
        panel.add(output);

        //j.add(panel);



        //j.add(panel);


        //menu.buttons(j);




        //panel.add(o);



        //menu.buttons(panel);



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
            if (GameVariables.multi) {

                multi.draw(g);
            }


        }


    }


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
