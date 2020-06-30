class EnemyRectangle extends Enemy{

    int speed = 10;

    void moveEnemy(){
        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            x[numEnemy]--;
        }
    }
}
