import java.awt.*;

public class EnemyTriangle extends Enemy{



    int speed = 2;

    void moveEnemy() {
        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            x[numEnemy] -= 2;
        }
    }


    void draw(Graphics g){

        g.setColor(Color.BLUE);

        for (int numEn = 0; numEn < total; numEn ++) {
            g.fillRect(x[numEn] - 25 , y[numEn] - 25, 50, 50);
        }

    }
}
