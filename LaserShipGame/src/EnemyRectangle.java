class EnemyRectangle extends Enemy{

    void moveEnemy(){
        for (int numEnemy = 0; numEnemy < total; numEnemy++) {

            x[numEnemy]-= speed;
        }
    }

}
