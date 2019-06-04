import javax.swing.*;
import java.awt.*;

public class Ship extends JPanel {

    Ship(int startx, int starty) {

        x = startx;
        y = starty;

    }

    int frame = 0;

    //x and y positions
    int x;
    int y;

    //hitbox position
    int topWingBoxX;
    int topWingBoxY;

    int bottomWingBoxX;
    int bottomWingBoxY;

    int noseBoxX;
    int noseBoxY;

    boolean shipAlive = true;

    int speedUp = -5;
    int speedDown = 5;
    int speedLeft = -5;
    int speedRight = 5;

    //laser position
    int lasX;
    int lasY;
    int laserX[] = new int[100];
    int laserY[] = new int[100];

    //laserspeed
    int laserSpeedX = 8;
    int laserSpeedY = 8;

    //move direction
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    //amount of lasers
    int amountLaser = 0;
    int totalLaser = 0;
    int maxLaser = 100;


    void resetSpeed() {
        speedUp = -5;
        speedDown = 5;
        speedLeft = -5;
        speedRight = 5;
    }

    void hitBoxPos() {

        topWingBoxX = x;
        topWingBoxY = y - 50;

        bottomWingBoxX = x;
        bottomWingBoxY = y + 50;

        noseBoxX = x + 150;
        noseBoxY = y;
    }

    void resetKeys() {
        up = false;
        down = false;
        left = false;
        right = false;

    }

    //makes sure ship stays in bound
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

    //add laser coordinates to laser array
    void setLaser() {

        lasX = x;
        lasY = y;

        laserX[amountLaser] = lasX;
        laserY[amountLaser] = lasY;

        amountLaser++;
        totalLaser++;

        if (amountLaser == maxLaser - 1) {
            amountLaser = 0;
        }
        if (totalLaser >= maxLaser) {
            totalLaser = maxLaser;

        }

    }

    //every 10 frames, return true --->spawn laser
    boolean laserSpawnRate(int rate) {

        if (GameVariables.frame % rate == 0 & amountLaser <= maxLaser) {

            return true;
        }
        return false;
    }

    //lasers move forward
    void moveLaser() {

        for (int numLaser = 0; numLaser < 100; numLaser++) {
            laserX[numLaser] = laserX[numLaser] + laserSpeedX;
        }

    }

    //checks if lasers collide with enemies
    //returns which enemy is hit
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

    //return true if ship hits enemy
    boolean shipHitEnemy(Enemy enemy) {

        for (int numEnemy = 0; numEnemy < enemy.max; numEnemy++) {

            if (topWingHit(numEnemy, enemy) || bottomWingHit(numEnemy, enemy) || noseHit(numEnemy, enemy)) {

                return true;
            }
        }
        return false;

    }

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


    void removeShip() {

        shipAlive = false;
    }


    void PaintComponent(Graphics g) {


    }

    void drawRect(Graphics g) {

        g.setColor(Color.blue);
        g.fillRect(200, 200, 200, 200);
    }

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

    void drawLaser(Graphics g) {

        g.setColor(Color.ORANGE);
        for (int numLas = 0; numLas < totalLaser; numLas++) {
            g.fillRect(laserX[numLas], laserY[numLas] - 2, 30, 4);
        }
    }

    void drawBox(Graphics g) {

        g.setColor(Color.MAGENTA);
        g.fillOval(topWingBoxX, topWingBoxY, 10, 10);
        g.fillOval(bottomWingBoxX, bottomWingBoxY, 10, 10);
        g.fillOval(noseBoxX, noseBoxY, 10, 10);

    }

}
