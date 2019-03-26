package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly a globalis utemezot reprezentalja.
 */

public class schedGlobal {
	private schedRR rr;
	private schedSJF sjf;
	/*
	A globalis utemezo ala tartozik a ket masik utemezo:
	rr utemezo a low prio taskoknak (0)
	sjf utemezo a high prio taskoknak (1)

	 */

	public schedGlobal(schedRR rr, schedSJF sjf){
		this.rr = rr;
		this.sjf = sjf;
	}

	public boolean step() {
		/*
		0 idopontban elinditjuk mindket utemezot, betoltik a varolistajukrol az elso taskot vagy uresen maradnak
		0 idopontban igazzal terunk vissza
		 */
		if(Main.time == 0){
			rr.start();
			sjf.start();
			return true;
		}
		/*
		ha egyik utemezo sem fut, mindket varolista is ures es az osszes taskot feldolgoztuk mar, akkor vege mindennek
		hamissal terunk vissza
		 */
		if (!sjf.isRunning && !rr.isRunning && sjf.waitList.isEmpty() && rr.waitList.isEmpty() && Main.time> Main.tasks.getLast().startTime){
			return false;
		}
		/*
		ha az sjf (high prio) utemezonek van aktiv taskja vagy a varolistaja nem ures (tehat van mit futtatni),
		akkor az kap cpu-t
		ha az sjf utemezo ures, az rr futtathat
		 */
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
