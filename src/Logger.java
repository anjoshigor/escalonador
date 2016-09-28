/**
* @anjoshigor
* Logger class
*
* description:
* Potential class to be used as a simple debugging helper
*
**/


import java.util.*;

public class Logger {

  public Logger(){}

  //prints queue elements
  public void printQueue(Queue<Job> queue){
    System.out.println("\n\nPID : Arrived |   CPU   |   Processing   |   Waiting   |   First Answer ");
      for (Job job : queue ) {
        System.out.println("Job "+job.getPid()+":    "+job.getTimeArrived()+"   |    "+job.getTimeNeeded()+"    |        "
                            + job.getTimeOnCpu()+"       |    "+ job.getTimeOnQueue() + "     |  " + job.getTimeResponse());
      }
    System.out.println("\n\n");
  }

}
