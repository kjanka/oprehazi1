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
	private int runningTime = 0; //ennyi ideje fut a scheduler
	public LinkedList<Task> waitList = new LinkedList<Task>();


	public void addTask (Task task){
	    waitList.addLast(task);
    }

	public void start(){
		isRunning = true;
		if(!waitList.isEmpty()) runningTask = waitList.removeFirst();
		runningTime = 0;
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
			if(!waitList.isEmpty()){
				for (Task t: waitList){
					t.waits();
				}
			}

		}

		if(!waitList.isEmpty() && (runningTime == 2 || runningTask == null)) {
			if (runningTask != null) waitList.addLast(runningTask);
			runningTask = waitList.removeFirst();
			runningTime = 0;
		}


		if(runningTask!=null) {
			if (runningTime == 2) runningTime = 0;
			runningTask.run();
			runningTime++;
			System.out.println("rr RAN " + runningTask.id + " time: " + runningTime + "system: " + Main.time);
			if(runningTask.burstTime - runningTask.ran == 0) {
				Main.finishedTasks.addLast(runningTask);
				runningTask = null;
			}
		}

		for(Task t : waitList) t.waits();




	}
		}





	

