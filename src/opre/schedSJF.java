package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly egy SJF utemezot reprezental.
 */

public class schedSJF {

	private Task runningTask = null; //az aktualis task
	public boolean isRunning = false;
	public LinkedList<Task> waitList = new LinkedList<Task>();

	public void addTask(Task task) {
		waitList.addLast(task);
	}

	public void start(){
		if(!waitList.isEmpty()){
			isRunning = true;
		}
	}

	public void step() {
		if (runningTask == null) {
			if (waitList.isEmpty()) {
				isRunning = false;
				return;
			} else{
				int minJind = 0;
				Task nextTask = waitList.getFirst();
				for (int i = 0; i <= waitList.size() - 1; i++) {
					if (waitList.get(i).burstTime < nextTask.burstTime) {
						minJind = i;
						nextTask = waitList.get(i);
					}
				}
				runningTask = waitList.remove(minJind);
			}
		}
		if (!waitList.isEmpty()) {
			isRunning = true;
			for (Task t : waitList) {
				t.waits();
			}
		}
		isRunning = true;
		runningTask.run();
		if (runningTask.burstTime - runningTask.ran == 0) {
			if(waitList.isEmpty()){isRunning = false;}
			Main.finishedTasks.addLast(runningTask);
			runningTask = null;
		}
	}
}
