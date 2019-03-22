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
		this.ran = 0;
    }

    public Task(){}
	
	public void run(){
        //System.out.println(this.id + " ran now, " + Main.time);
        ran++;

            String currentQ = Main.queue.toString();
            if(currentQ.length()==0){
                Main.queue.append(this.id);
            }else {
                if (currentQ.charAt(currentQ.length() - 1) != (this.id)) {
                    Main.queue.append(this.id);
                }
            }

	}
	
	public void waits(){
		wait++;
	}

}
