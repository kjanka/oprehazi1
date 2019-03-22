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
        ran++;
        /*
        A futasi sorrendhez hozzaadjuk a task id-jat, ha nem o volt az utolso (is), aki kapott cpu-t
        VEGSO OUTPUT
         */
            String currentQ = Main.queue.toString();
            if(currentQ.length()==0){
                Main.queue.append(this.id);
            }else {
                if (currentQ.charAt(currentQ.length() - 1) != (this.id)) {
                    Main.queue.append(this.id);
                }
            }

	}
	/*
	ha varakoztatjuk a taskot, a varakozasi idejet noveljuk eggyel
	VEGSO OUTPUT
	 */
	public void waits(){
		wait++;
	}

}
