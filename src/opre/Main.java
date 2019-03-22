package opre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * A program egy statikus tobbszintu, globalisan preemptiv, statikus prioritasos utemezo mukodeset szimulalja.
 * A futtatott algoritmusok:
 * kernel szint (1): prio 1/magas, SJF utemezo |SHORTEST JOB FIRST|
 * felhasznaloi szint (2): prio 0/alacsony RR utemezo, idoszelet: 2 |ROUND ROBIN|
 */
public class Main {

    public static int time = 0;
    public static LinkedList<Task> tasks = new LinkedList<>();
    public static LinkedList<Task> finishedTasks = new LinkedList<>();
    public static StringBuilder queue = new StringBuilder();

    public static void main(String[] args) {
        schedRR rr = new schedRR();
        schedSJF sjf = new schedSJF();



        //input beolvasasa es beerkezesi sorba rendezese
        int ind = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = br.readLine();
            while (!input.equals("")) {
                String[] data = input.split(",");
                Task currentTask = new Task(data[0].charAt(0), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                if (tasks.size() == 0) {tasks.add(currentTask);}
                else {
                    int i=0;
                    while(tasks.size() > i && tasks.get(i).startTime <= currentTask.startTime) i++; //ha egy idoben erkeztek, az input sorrend szamit
                    tasks.add(i, currentTask);
                }
                input = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        schedGlobal global = new schedGlobal(rr, sjf);


        while (global.step() && time < 50){
            for(Task t: tasks) {
                if(t.startTime == time){
                    if(t.prio == 0) {rr.addTask(t);}
                    if(t.prio == 1) {sjf.addTask(t);}
                }
            }
            time++;
        }

        System.out.println(queue.toString());

        StringBuilder waitB = new StringBuilder();
        for(Task t: tasks){
            waitB.append(t.id);
            waitB.append(':');
            int wait = 0;
            for(Task s: finishedTasks){
                if(t.id == s.id){
                    waitB.append(s.wait);
                }
            }
            waitB.append(',');
        }
        waitB.setLength(waitB.length() - 1);
        System.out.print(waitB.toString());





    }
}
