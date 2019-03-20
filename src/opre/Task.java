package opre;

/**
 * Ez az osztaly egy Task-ot reprezental.
 */

public class Task {
    public char id;
    public int prio;
    public int startTime;
    public int burstTime;


    public Task(char id, int p, int s, int b) {
        this.id = id;
        this.prio = p;
        this.startTime = s;
        this.burstTime = b;
    }

    public Task(){}

}
