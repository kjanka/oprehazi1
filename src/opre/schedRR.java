package opre;

import java.util.LinkedList;


/**
 * Ez az osztaly egy RR utemezot reprezental.
 * Minden task 2 orajelet kap a futasra, ezutan a varakozasi sor vegere kerul.
 *
 */

public class schedRR {
	

	private Task runningTask = null; //az aktualis task
	public boolean isRunning = false; //megy e az utemezo (csak ha nincs magas prio task eppen)
	private int runningTime = 0; //az aktualis task ennyi ideje fut
	public LinkedList<Task> waitList = new LinkedList<Task>();


	public void addTask (Task task){
	    waitList.addLast(task);
    }

	public void start(){
		isRunning = true;
		runningTask = waitList.removeFirst();
	}
	
	public void stop(){
		isRunning = false;
		if(runningTask != null){
		    waitList.addLast(runningTask);
		    runningTask = null;
        }
		runningTime = 0;
	}

	public void step(){


		if(!isRunning) {
			runningTask.waits();
			for (int i = 0; i <= waitList.size(); i++) {
				waitList.get(i).waits();
			}
			return;
		}

		runningTime++;

		if(runningTime == 2){
			if(runningTask!=null){
				runningTask.run();
				if((runningTask.burstTime - runningTask.ran) >= 1) {
					waitList.addLast(runningTask);
				}else{
					Main.finalList.addLast(runningTask);
				}

				runningTask = null;
			}
			if(waitList.size() > 0){
				runningTask = waitList.removeFirst();
			}

			runningTime = 0;
		}else {
			if (runningTask != null) {
				runningTask.run();
			}
		}





	}
	
}
