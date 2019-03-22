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
			return true;
		}
		else if(sjf.isRunning) {
			sjf.step();
			if(rr.isRunning) rr.stop();
			return true;
		}else{
			if(!rr.isRunning) rr.start();
			rr.step();
			return true;
		}
	}
}
