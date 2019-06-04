import java.util.Timer;
import java.util.TimerTask;


//prints out fps every second
public class fpsTracker extends TimerTask {

    int seconds = 0;

    public void run() {

        seconds++;

        System.out.println(GameVariables.frame / seconds);


    }


    void time() {

        fpsTracker myTask = new fpsTracker();
        Timer myTimer = new Timer();

        myTimer.schedule(myTask, 1000, 1000);


    }

}
