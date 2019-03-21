package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly a globalis utemezot reprezentalja.
 */

public class schedGlobal {
	private LinkedList<Task> taskList;


	private schedRR rr;
	private schedSJF sjf;
	
	private Task runningTask = null;

	public schedGlobal(LinkedList<Task> tList, schedRR rr, schedSJF sjf){
		this.taskList = tList;
		this.rr = rr;
		this.sjf = sjf;
	}
	
	public void start(){
		
	}

	public boolean step() {
		if (rr.waitList.size() == 0 && sjf.waitList.size() ==0 && taskList.size() ==0) {
			return false;
		}
		boolean ok = true;
		while (ok) {
			if (taskList.getFirst().startTime <= Main.time-1) {
				if(taskList.getFirst().prio ==1) sjf.addTask(taskList.removeFirst());
				if(taskList.getFirst().prio ==0) rr.addTask(taskList.removeFirst());
			}else {
				ok = false;
			}
		}

		if(!sjf.isRunning){
			rr.step();
		}else{
			sjf.step();
		}


		return true;
	}
}
