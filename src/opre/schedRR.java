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
	private int runningTime = 0; //ennyi ideje futtatjuk a jeéenlegi taskot
	public LinkedList<Task> waitList = new LinkedList<Task>();

	public void addTask (Task task){
	    waitList.addLast(task);
    }

    /*
    Inditasnal a varolista elso elemet betoltjuk, ez lesz a futtatott task
    A futasi ido 0 lesz, 2 orajelet kap minden task.
     */
	public void start(){
		isRunning = true;
		if(!waitList.isEmpty()) runningTask = waitList.removeFirst();
		runningTime = 0;
	}
	/*
	Ha leallitjuk az utemezot, a jelenleg futtatott task a varolista vegere kerul
	akkor is, ha nem kapta meg a 2 orajelet (ehezes elkerulese)
	A jelenleg futattott task NULL lesz, a futasi ido 0.
	 */
	public void stop(){
		isRunning = false;
		if(runningTask != null){
		    waitList.addLast(runningTask);
		    runningTask = null;
        }
		runningTime = 0;
	}
	public void step(){
		/*
		Minden varolistas elemet varakoztatunk, akar fut az utemezo, akar nem.
		Ha nem fut, le van allitva, tehat nincs aktiv task, minden ehhez az utemezohoz tartozo,
		rendszeridohoz kepest mar beerkezett task varolistan van.
		*/
		if(!isRunning) {
			if(!waitList.isEmpty()){
				for (Task t: waitList){
					t.waits();
				}
			}
			return;
		}
		/*
		Betoltjuk a kovetkezo taskot a varolistarol a kovetkezo esetekben:
		nincs aktiv task (de van varolistan)
		a jelenleg futtatott task mar megkapta a 2 orajelet
		 */
		if(!waitList.isEmpty() && (runningTime == 2 || runningTask == null)) {
			if (runningTask != null) waitList.addLast(runningTask);
			runningTask = waitList.removeFirst();
			runningTime = 0;
		}

		/*
		Ha van aktiv task, futtatjuk.
		Noveljuk eggyel a futasi idot.
		 */
		if(runningTask!=null) {
			if (runningTime == 2) runningTime = 0;
			runningTask.run();
			runningTime++;
			if(runningTask.burstTime - runningTask.ran == 0) {
				if(waitList.isEmpty()){isRunning = false;}
				Main.finishedTasks.addLast(runningTask); //ha a tasknak vege, beirjuk a befejezett taskok koze VEGSO OUTPUT
				runningTask = null;
			}
		}
		/*
		Minden varolistas taskot varakoztatunk.
		 */
		for(Task t : waitList) t.waits();

	}
		}





	

