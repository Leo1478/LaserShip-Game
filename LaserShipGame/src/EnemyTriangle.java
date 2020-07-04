import java.awt.*;

class EnemyTriangle extends Enemy{



    int speed = 2;

    int height = 100;
    int width = 100;

    void moveEnemy() {
        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            x[numEnemy] -= speed;
        }
    }


    void updateHitbox(){

        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            hitbox[numEnemy].x = x[numEnemy];
            hitbox[numEnemy].y = y[numEnemy];
        }
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

    void draw(Graphics g){

        g.setColor(Color.BLUE);

        for (int numEn = 0; numEn < total; numEn ++) {
            g.fillRect(x[numEn], y[numEn], width, height);
        }

    }

}
