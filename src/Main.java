import java.util.*;
import java.io.*;

public class Main  {
  public static void main(String[] args) {

    Logger logger = new Logger();
    Queue<Job> queueFCFS = new LinkedList<Job>();
    Queue<Job> queueRR = new LinkedList<Job>();
    Queue<Job> queueSJF = new LinkedList<Job>();

    //Getting input from terminal
    try {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String x;
        int pid = 0;
          while( (x = input.readLine()) != null ) {
            String[] line = x.split(" ");
            Job job = new Job(Integer.parseInt(line[0]), Integer.parseInt(line[1]), pid);
            Job job2 = new Job(Integer.parseInt(line[0]), Integer.parseInt(line[1]), pid);
            Job job3 = new Job(Integer.parseInt(line[0]), Integer.parseInt(line[1]), pid);

            queueFCFS.add(job);
            queueRR.add(job2);
            queueSJF.add(job3);
            pid++;
          }
        } catch (IOException e) {
            e.getMessage();
        }

    Scheduler schedulerFCFS = new Scheduler("FCFS", queueFCFS);
    schedulerFCFS.schedule();
    schedulerFCFS.printResults();

    Scheduler schedulerSJF = new Scheduler("SJF", queueSJF);
    schedulerSJF.schedule();
    schedulerSJF.printResults();

    Scheduler schedulerRR = new Scheduler("RR", queueRR);
    schedulerRR.schedule();
    schedulerRR.printResults();


  }

}
