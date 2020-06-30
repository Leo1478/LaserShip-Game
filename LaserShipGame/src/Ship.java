import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class Ship extends Entity{

    int x;
    int y;

    Ship(int x, int y) {    // constructor create ship with position
        this.x = x;
        this.y = y;
    }

    Entity[] entity;

    int health;    // how much health the ship has

    private int topWingBoxX;    // hitbox position
    private int topWingBoxY;
    private int bottomWingBoxX;
    private int bottomWingBoxY;
    private int noseBoxX;
    private int noseBoxY;

    boolean shipAlive = true;    // if ship is alive

    private int speedUp = -5;    // speed of ship
    private int speedDown = 5;
    private int speedLeft = -5;
    private int speedRight = 5;


    private int lasX;    // laser start position
    private int lasY;
    private int laserX[] = new int[100];    // all laser position
    private int laserY[] = new int[100];

    private int laserSpeedX = 8;    // laser speed
    private int laserSpeedY = 8;

    boolean up = false;    // if ship is moving in a direction
    boolean down = false;
    boolean left = false;
    boolean right = false;

    private int amountLaser = 0;    // amount of lasers
    private int totalLaser = 0;
    private int maxLaser = 100;

    /**
     * reset the speed of the ship
     */
    void resetSpeed() {
        speedUp = -5;
        speedDown = 5;
        speedLeft = -5;
        speedRight = 5;
    }

    /**
     * set the position of the hit box based on where ths ship is
     */
    void hitBoxPos() {

        topWingBoxX = x;
        topWingBoxY = y - 50;

        bottomWingBoxX = x;
        bottomWingBoxY = y + 50;

        noseBoxX = x + 150;
        noseBoxY = y;
    }

    /**
     * reset the movement of the ship if key is not pressed
     */
    void resetKeys() {
        up = false;
        down = false;
        left = false;
        right = false;

    }

    /**
     * if ship is out of bounds, speed is 0
     */
    void shipInBound() {

        if (x < 0) {
            speedLeft = 0;
        }
        if (x > 1400) {
            speedRight = 0;
        }
        if (y < 100) {
            speedUp = 0;
        }
        if (y > 900) {
            speedDown = 0;
        }
    }

    /**
     * move ship based on movement key pressed
     */
    void moveShip() {
        if (up) {
            y = y + speedUp;
        }
        if (down) {
            y = y + speedDown;
        }
        if (left) {
            x = x + speedLeft;
        }
        if (right) {
            x = x + speedRight;
        }
    }

    /**
     * add new laser to laser array
     */
    void setLaser() {

        lasX = x;
        lasY = y;

        laserX[amountLaser] = lasX;
        laserY[amountLaser] = lasY;

        amountLaser ++;
        totalLaser ++;

        if (amountLaser == maxLaser - 1) {
            amountLaser = 0;
        }
        if (totalLaser >= maxLaser) {
            totalLaser = maxLaser;

        }
    }

    /**
     * if a laser should be spawned, return true
     * @param rate rate of laser, how many frames per laser
     * @return if a new laser should be spawned or not
     */
    boolean laserSpawnRate(int rate) {

        return (GameVariables.frame % rate == 0 & amountLaser <= maxLaser);
    }

    /**
     * move all lasers forward
     */
    void moveLaser() {

        for (int numLaser = 0; numLaser < 100; numLaser++) {
            laserX[numLaser] = laserX[numLaser] + laserSpeedX;
        }

    }


    /**
     * check if a laser collides with enemies
     * @param enemy enemy to check with
     * @return which enemy is hit
     */
    int laserHitEnemy(Enemy enemy) {

        for (int numLaser = 0; numLaser < totalLaser; numLaser++) {

            for (int numEnemy = 0; numEnemy < enemy.total; numEnemy++) {

                if (laserX[numLaser] > enemy.x[numEnemy] - 25
                        & laserX[numLaser] < enemy.x[numEnemy] + 25
                        & laserY[numLaser] > enemy.y[numEnemy] - 25
                        & laserY[numLaser] < enemy.y[numEnemy] + 25) {

                    return numEnemy;
                }
            }
        }
        return 101; // no enemy is hit
    }

    /**
     * check if the ship hits an enemy
     * @param enemy enemy to check with
     * @return if the ship hits an enemy
     */
    boolean shipHitEnemy(Enemy enemy) {

        for (int numEnemy = 0; numEnemy < enemy.max; numEnemy++) {

            if (topWingHit(numEnemy, enemy) || bottomWingHit(numEnemy, enemy) || noseHit(numEnemy, enemy)) {

                return true;
            }
        }
        return false;

    }

    /**
     * checks if tip wing hit box hits
     * @param numEnemy
     * @param enemy
     * @return
     */
    boolean topWingHit(int numEnemy, Enemy enemy) {

        if (topWingBoxX < enemy.x[numEnemy] + 25
                & topWingBoxX > enemy.x[numEnemy] - 25
                & topWingBoxY < enemy.y[numEnemy] + 25
                & topWingBoxY > enemy.y[numEnemy] - 25 ) {
            return true;
        }
        return false;
    }

    boolean bottomWingHit(int numEnemy, Enemy enemy) {

        if (bottomWingBoxX < enemy.x[numEnemy] + 25
                & bottomWingBoxX > enemy.x[numEnemy] - 25
                & bottomWingBoxY < enemy.y[numEnemy] + 25
                & bottomWingBoxY > enemy.y[numEnemy] - 25 ) {
            return true;
        }
        return false;
    }

    boolean noseHit(int numEnemy, Enemy enemy) {

        if (noseBoxX < enemy.x[numEnemy] + 25
                & noseBoxX > enemy.x[numEnemy] - 25
                & noseBoxY < enemy.y[numEnemy] + 25
                & noseBoxY > enemy.y[numEnemy] - 25 ) {
            return true;
        }
        return false;

    }



    /**
     * remove ship if it dies
     */
    void removeShip() {

        shipAlive = false;
    }


    void PaintComponent(Graphics g) {

    }


    void drawRect(Graphics g) {

        g.setColor(Color.blue);
        g.fillRect(200, 200, 200, 200);
    }

    /**
     * draw the ship
     * @param g
     */
    void drawShip(Graphics g) {

        if (shipAlive) {

            g.setColor(Color.red);
            g.drawRect(x, y - 5, 150, 10);

            int xValues[] = {x, x, x + 100};
            int yValues[] = {y + 50, y - 50, y};
            Polygon triangle = new Polygon(xValues, yValues, 3);
            g.fillPolygon(triangle);
        }
    }

    /**
     * draw the lasers
     * @param g
     */
    void drawLaser(Graphics g) {

        g.setColor(Color.ORANGE);
        for (int numLas = 0; numLas < totalLaser; numLas++) {
            g.fillRect(laserX[numLas], laserY[numLas] - 2, 30, 4);
        }
    }

    /**
     * draw the hit box
     * @param g
     */
    void drawBox(Graphics g) {

        g.setColor(Color.MAGENTA);
        g.fillOval(topWingBoxX, topWingBoxY, 10, 10);
        g.fillOval(bottomWingBoxX, bottomWingBoxY, 10, 10);
        g.fillOval(noseBoxX, noseBoxY, 10, 10);

    }

    /**
     * check which movement keys are pressed
     * @param e
     * @param bool
     */
    void move(KeyEvent e, boolean bool){

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = bool;
        }

    }

}
