import java.awt.*;

/**
 * test class used to test features
 */
class Test {

    /**
     * all graphics for test class
     * @param g
     */
    void draw(Graphics g){

        //drawSmallGrid(g);
        //drawGrid(g);
        //drawButtonHitBox(g);

    }

    /**
     * creates a grid on the screen
     * @param g
     */
    void drawGrid(Graphics g){

        g.setColor(Color.red);
        for(int x = 0 ; x <= 1500 ; x += 100){
            g.drawRect(x, 0, 1, 1000);
        }
        for(int y = 0 ; y <= 1000 ; y += 100){
            g.drawRect(0, y, 1500, 1);
        }
    }


    void drawSmallGrid(Graphics g){
        g.setColor(Color.blue);
        for(int x = 0 ; x <= 1500 ; x += 10){
            g.drawRect(x, 0, 1, 1000);
        }
        for(int y = 0 ; y <= 1000 ; y += 10){
            g.drawRect(0, y, 1500, 1);
        }

    }

    void drawButtonHitBox(Graphics g){
        g.setColor(Color.green);
        g.drawRect(610, 440, 280, 110);
        g.drawRect(610, 610, 280, 110);
        g.drawRect(610, 780, 280, 110);
    }

}
