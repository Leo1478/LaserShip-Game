import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Enemy extends Entity {

    //objects
    Random rand = new Random();


    //position of enemies
    int eneX;
    int eneY;

    int[] x = new int[100];
    int[] y = new int[100];


    Rectangle[] hitbox = new Rectangle[100];    // hitbox of the enemy


    int height = 50;    // height and width of the enemy
    int width = 50;

    //amount of enemies
    int amount = 0;
    int total = 0;
    int max = 100;

    int speed = 1;



    void updateHitbox(){

        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            hitbox[numEnemy].x = x[numEnemy];
            hitbox[numEnemy].y = y[numEnemy];
        }
    }



    //spawn rate for enemies
    boolean spawnRate(int rate) {
        if (GameVariables.frame % rate == 0 & amount <= max) {
            return true;
        }
        return false;
    }

    // add enemy coordinates to array
    void setEnemy() {

        eneX = rand.nextInt(500) + 1000;
        eneY = rand.nextInt(800) + 100;

        x[amount] = eneX;
        y[amount] = eneY;

        hitbox[amount] = new Rectangle();
        hitbox[amount].height = height;
        hitbox[amount].width = width;

        amount++;
        total++;

        if (amount == max) {
            amount = 0;
        }
        if (total >= max) {
            total = max;
        }
    }


    //put enemy position to -100 (off screen) after they die
    void removeEnemy(int num) {

        if (num < 100) {
            x[num] = -100;
            x[num] = -100;
        }
    }


    void moveEnemy() {

        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            x[numEnemy]-= speed;
        }
    }



    void draw(Graphics g){

        g.setColor(Color.GREEN);
        for (int numEn = 0; numEn < total; numEn++) {
            g.fillRect(x[numEn], y[numEn], width, height);
        }

    }


}
