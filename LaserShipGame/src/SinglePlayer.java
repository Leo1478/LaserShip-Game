import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class SinglePlayer extends JPanel implements KeyListener {


    private BufferedImage backGround;

    Ship ship = new Ship(100, 100);
    Enemy enemy = new Enemy();
    EnemyRectangle enemyRectangle = new EnemyRectangle();
    EnemyTriangle enemyTriangle = new EnemyTriangle();

    float backgroundPosition = 0;

    boolean init = false;

    // constructor
    void SinglePlayer(){

    }


    void singleLoop() {

        backgroundPosition -= 0.5;

        if(backgroundPosition == -1500){
            backgroundPosition = 0;
        }



        ship.resetSpeed();

        ship.hitBoxPos();

        ship.shipInBound();

        ship.moveShip();

        if (ship.shipAlive & ship.laserSpawnRate(10)) {
            ship.setLaser();
        }

        ship.moveLaser();


        //if (ship.shipAlive & enemy.spawnRate(50)) {
        //    enemy.setEnemy();
        //}

        if (ship.shipAlive & enemy.spawnRate(50)) {
            enemyRectangle.setEnemy();
        }
        if (ship.shipAlive & enemy.spawnRate(50)) {
            enemyTriangle.setEnemy();
        }

        //enemy.removeEnemy(ship.laserHitEnemy(enemy));

        enemyRectangle.removeEnemy(ship.laserHitEnemy(enemyRectangle));
        enemyTriangle.removeEnemy(ship.laserHitEnemy(enemyTriangle));

        //enemy.moveEnemies();
        enemyRectangle.moveEnemy();
        enemyTriangle.moveEnemy();

        //if (ship.shipHitEnemy(enemy)) {
            //ship.removeShip();
        //}

        if (ship.shipHitEnemy(enemyRectangle)) {
            ship.removeShip();
        }

        if (ship.shipHitEnemy(enemyTriangle)) {
            ship.removeShip();
        }







    }

    void init(){

        if (init == false) {

            loadImage();
            init = true;
        }
    }


    void draw(Graphics g) {

        init();
        background(g);
        ship.drawShip(g);
        ship.drawLaser(g);
        ship.drawBox(g);
        //enemy.draw(g);
        enemyRectangle.draw(g);
        enemyTriangle.draw(g);

    }


    void loadImage(){
        try {
            backGround = ImageIO.read(new File("F:\\java stuff\\LaserShipGame\\images\\space_background.jpg"));
        } catch (IOException e) {
        }
    }

    void background(Graphics g){

        g.drawImage(backGround,(int) backgroundPosition, 0, null );
        g.drawImage(backGround, (int) backgroundPosition + 1500, 0, null);


    }


    //use parameter true for keyPressed, false for keyReleased
    void move(KeyEvent e, boolean bool){

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.up = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.down = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.left = bool;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.right = bool;
        }



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

}






