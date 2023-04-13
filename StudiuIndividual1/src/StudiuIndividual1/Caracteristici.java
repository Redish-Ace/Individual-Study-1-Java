package StudiuIndividual1;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Caracteristici {
    protected double[] size = new double[1000];
    protected String[] name = new String[1000];
    protected void updateArrays(){}
    protected void setList(){}
    protected void getList(){}
    public void menu(){}

    // Seteaza un timer care va apela metoda menu dupa 5 secunde
    protected void setTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                menu();
            }
        }, 5000);
    }
}
