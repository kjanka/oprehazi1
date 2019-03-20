package opre;

/**
 * Ez az osztaly egy Task-ot reprezental.
 */

public class Task {
    public char id;
    public int prio;
    public int startTime;
    public int burstTime;
	
	public int wait = 0;
	public int ran = 0;

    public Task(char id, int p, int s, int b) {
        this.id = id;
        this.prio = p;
        this.startTime = s;
        this.burstTime = b;
		this.ran = b;
    }

    public Task(){}
	
	public void run(){
		ran++;
	}
	
	public void waits(){
		wait++;
	}

}
