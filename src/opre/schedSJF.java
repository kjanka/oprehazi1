package opre;

import java.util.LinkedList;

/**
 * Ez az osztaly egy SJF utemezot reprezental.
 */

public class schedSJF {

	private Task runningTask = null; //az aktualis task
	public boolean isRunning = false; //jelenleg fut e az utemezo, azaz van e aktiv task vagy a varolistan van e
	public LinkedList<Task> waitList = new LinkedList<Task>();

	public void addTask(Task task) {
		waitList.addLast(task);
	}

	/*
	Inditaskor ha nem ures a varolista, a cpu-nak ezt az utemezot kell kiszolgalnia.
	 */

	public void start(){
		if(!waitList.isEmpty()){
			isRunning = true;
		}
	}

	public void step() {
		if (runningTask == null) {
			if (waitList.isEmpty()) { //ha nincs aktiv vagy varolistas taskunk, a cpu adhat keretet az alacsonyabb prios taskoknak
				isRunning = false;
				return;
			} else{ //ha nincs futtatott task, a varolistan megkeressuk rendszer idejehez kepest mar beerkezettek kozul a legrovidebb loketideju taskot
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
		if (!waitList.isEmpty()) { //a varolistan minden taskot varakoztatunk
			isRunning = true;
			for (Task t : waitList) {
				t.waits();
			}
		}
		isRunning = true;
		runningTask.run(); //a taskot futtatjuk
		if (runningTask.burstTime - runningTask.ran == 0) {
			if(waitList.isEmpty()){isRunning = false;}
			Main.finishedTasks.addLast(runningTask); //ha a tasknak vege, beirjuk a befejezett taskok koze VEGSO OUTPUT
			runningTask = null;
		}
	}
}
