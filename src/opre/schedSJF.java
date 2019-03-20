package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly egy SJF utemezot reprezental.
 */

public class schedSJF {
	
	private Task runningTask = null; //az aktualis task
	private boolean isRunning = false; 
	private int runningTime = 0; //az aktualis task ennyi ideje fut
	private int remTime = 0; //az aktualis task COU loketebol hatralevo ido
	private LinkedList<Task> waitList = new LinkedList<Task>();
	
	public void start(){
		isRunning = true;
	}
	
	public void stop(){
		
	}
	
	public void step(){
		
	}
}
