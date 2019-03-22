package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly a globalis utemezot reprezentalja.
 */

public class schedGlobal {



	private schedRR rr;
	private schedSJF sjf;
	


	public schedGlobal(schedRR rr, schedSJF sjf){
		this.rr = rr;
		this.sjf = sjf;
	}

	public boolean step() {
		if(Main.time == 0){
			rr.start();
			sjf.start();
			return true;


		}
		if (!sjf.isRunning && !rr.isRunning && sjf.waitList.isEmpty() && rr.waitList.isEmpty() && Main.tasks.isEmpty()){
			return false;
		}

		if(!sjf.waitList.isEmpty() || sjf.isRunning) {
			sjf.step();
			rr.stop();
			rr.step();
			return true;
		}else{
			if(!rr.isRunning) rr.start();
			rr.step();
			return true;
		}


	}
}
