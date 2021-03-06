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
            while ((input != null) && !("").equals(input)) {
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

/*
A globalis utemezot futtatjuk.
Az input listabol rendszer ido szerint adjuk be a taskokat az alsobb szinten levo ket utemezobe,
igy azok varolistaja ures lesz, ha "rendszer idohoz kepest" nincs beerkezett task.
*/
        while (global.step()){
            for(Task t: tasks) {
                if(t.startTime == time){
                    if(t.prio == 0) {rr.addTask(t);}
                    if(t.prio == 1) {sjf.addTask(t);}
                }
            }
            time++;
        }

        /*
        VEGSO OUTPUT:
        A queue stringbuilderbe minden task beleirja magat futaskor, ez a futtatasi sorrend
        A masik output a varakozasi idok, ehhez a befejezett taskok (vegso) listajat
        es az input listat hasznaljuk fel, ezutobbit nem modositjuk futtatas kozben.
        A beerkezesi idot az input listan beluli sorrend hatarozza meg, a befejezett taskok
        listajaban pedig a varakozasi idok osszege van, amit a taskok novelnek minden
        varakozas alatt.
         */
        System.out.println(queue.toString()); //futasi sorrend
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
        System.out.print(waitB.toString()); //varakozasi idok





    }
}
