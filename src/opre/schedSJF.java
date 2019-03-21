package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly egy SJF utemezot reprezental.
 */

public class schedSJF {
	
	private Task runningTask = null; //az aktualis task
	public boolean isRunning = true;
	private int runningTime = 0; //az aktualis task ennyi ideje fut
	private int remTime = 0; //az aktualis task COU loketebol hatralevo ido
	public LinkedList<Task> waitList = new LinkedList<Task>();

	public void addTask (Task task){
		waitList.addLast(task);
	}
	
	public void step(){
		if(runningTask!=null) {
			isRunning = true;
			runningTask.run();
			for(Task t: waitList){
				t.waits();
			}
			if(runningTask.burstTime - runningTask.ran ==0 ){
				Main.finalList.addLast(runningTask);
				runningTask = null;

			}
		}else if(waitList.size() >=0) {
			 {
			 	isRunning = true;
				int nextTaskInd = 0;
				for (int i = 0; i <= waitList.size(); i++) {
					if (waitList.get(i).burstTime < waitList.get(nextTaskInd).burstTime) {
						nextTaskInd = i;
					}
				}
				runningTask = waitList.remove(nextTaskInd);
				runningTask.run();
				 for(Task t: waitList){
					 t.waits();
				 }
			}
		}else{
			isRunning = false;
		}
	}
}
