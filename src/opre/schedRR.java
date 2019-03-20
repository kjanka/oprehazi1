package opre;

import java.util.LinkedList;


/**
 * Ez az osztaly egy RR utemezot reprezental.
 */

public class schedRR {
	
	private static int timeSlice = 2;
	private Task runningTask = null; //az aktualis task
	private boolean isRunning = false; //megy e az utemezo (csak ha nincs magas prio task eppen)
	private int runningTime = 0; //az aktualis task ennyi ideje fut
	private LinkedList<Task> waitList = new LinkedList<Task>();


	public void addTask (Task task){
	    waitList.addLast(task);
    }

	public void start(){
		isRunning = true;

	}
	
	public void stop(){
		isRunning = false;
		if(runningTask != null){
		    waitList.addLast(runningTask);
		    runningTask = null;
        }
		runningTime = 0;
	}
	
	
}
