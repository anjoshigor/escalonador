/**
* @anjoshigor
* Scheduler class
*
* attributes:
* _queueOfJobs -> A Queue to store the jobs that want to be processed (Jobs Queue)
* _finishedQueue -> A Queue for ended processes
* _algorithm -> algorithm used in scheduling
* logger -> An object of Logger Class
*
* methods:
* schedule -> a switch case function to determine wich algorithm will be used
* roundRobin -> an algorithm based on quantums that each job has to process data on CPU
* firstComeFirstService -> an algorithm that services the first that comes no matter how much
*                          time it will need to process data on CPU
* shortestJobFirst -> best algorithm in theory that gives priority to the fastest job
* updateQueueTime -> used to increment the waiting time on queue of the jobs waiting to be processed
* calcWaitingTime -> computes the average of waiting time
* calcProcessTime -> computes the average of processing time i.e time on jobs in CPU
* calcFirstResponseTime -> computes the average of time to the first answer of the jobs
* printResults -> prints the average time of each algorithm of each metric
**/


import java.util.*;

public class Scheduler {

  private Queue<Job> _queueOfJobs;
  private Queue<Job> _finishedQueue;
  private String _algorithm;

  public Logger logger;

  /**Constructor**/
  public Scheduler(String algorithm, Queue<Job> queueOfJobs){
    this._algorithm = algorithm;
    this._queueOfJobs = queueOfJobs;
    this._finishedQueue = new LinkedList<Job>();
    logger = new Logger();
  }

  /**Getters**/
  public String getAlgorithm(){
    return this._algorithm;
  }

  public Queue<Job> getQueueOfJobs(){
    return this._queueOfJobs;
  }

  public Queue<Job> getFinishedQueue(){
    return this._finishedQueue;
  }

  /**Switch for algorithms**/
  public void schedule(){
      switch (this._algorithm) {
        case "RR":
        roundRobin();
        break;

        case "FCFS":
        firstComeFirstService();
        break;

        default:
        case "SJF":
        shortestJobFirst();
        break;
      }
  }


    /*******************
    *****ALGORITHMS*****
    ********************/

  /**Round Robin with quantum = 2**/
  public void roundRobin(){
    Queue <Job> runningQueue = new LinkedList<Job>();

    int currentTime = 0;

    //adding jobs on time 0
    while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
      Job jobChangeQueue = this._queueOfJobs.poll();
      runningQueue.add(jobChangeQueue);
    }

    while (this._queueOfJobs.size() > 0 || runningQueue.size() > 0){

      int auxTime=-1;

      Job tempJob = runningQueue.poll();

      if(tempJob != null){
        //System.out.print("Rodando processo ["+ tempJob.getPid() + "] de [" + currentTime + "] ate [");
        if(tempJob.getFirstResponse()){
          tempJob.setTimeResponse(tempJob.getTimeOnQueue());
          tempJob.setFirstResponse(false);
        }
        tempJob.decTimeNeeded(2);
        tempJob.incTimeOnCpu(2);
        updateQueueTime(runningQueue,2, currentTime);
        auxTime = tempJob.getTimeNeeded();

      }
      currentTime += 2;

      while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
        Job jobChangeQueue = this._queueOfJobs.poll();
        runningQueue.add(jobChangeQueue);
      }

      if (auxTime > 0){
        runningQueue.add(tempJob);
      } else if (auxTime == -1) {
      /*null object*/
      } else {
        this._finishedQueue.add(tempJob);
      }

      //System.out.println(currentTime+"]");
    }
    //logger.printQueue(this._finishedQueue);
  }

  /**First Come First Service**/
  public void firstComeFirstService(){
    Queue <Job> runningQueue = new LinkedList<Job>();

    int currentTime = 0;

    //adding jobs on time 0
    while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
      Job jobChangeQueue = this._queueOfJobs.poll();
      runningQueue.add(jobChangeQueue);
    }

    while (this._queueOfJobs.size() > 0 || runningQueue.size() > 0){

      int quantum = 1;

      Job tempJob = runningQueue.poll();

      if(tempJob != null){
        //System.out.print("Rodando processo ["+ tempJob.getPid() + "] de [" + currentTime + "] ate [");

        if(tempJob.getFirstResponse()){
          tempJob.setTimeResponse(tempJob.getTimeOnQueue());
          tempJob.setFirstResponse(false);
        }

        quantum = tempJob.getTimeNeeded();
        tempJob.decTimeNeeded(quantum);
        tempJob.incTimeOnCpu(quantum);
        updateQueueTime(runningQueue,quantum, currentTime);

      }
      currentTime += quantum;

      while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
        Job jobChangeQueue = this._queueOfJobs.poll();
        runningQueue.add(jobChangeQueue);
      }

      if(tempJob != null)
        this._finishedQueue.add(tempJob);


      //System.out.println(currentTime+"]");
    }

    //logger.printQueue(this._finishedQueue);
  }

  /**Shortest Job First**/
  public void shortestJobFirst(){

    PriorityQueue<Job> runningQueue = new PriorityQueue<Job>(10, new Comparator<Job>() {
        public int compare(Job jobA, Job jobB) {

            if(jobA.getTimeNeeded() == jobB.getTimeNeeded())
              return 0;

            if (jobA.getTimeNeeded() < jobB.getTimeNeeded())
              return -1;
            else
              return 1;
        }
    });

    int currentTime = 0;

    //adding jobs on time 0
    while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
      Job jobChangeQueue = this._queueOfJobs.poll();
      runningQueue.add(jobChangeQueue);
    }

    while (this._queueOfJobs.size() > 0 || runningQueue.size() > 0){

      int quantum = 1;

      Job tempJob = runningQueue.poll();

      if(tempJob != null){
        //System.out.print("Rodando processo ["+ tempJob.getPid() + "] de [" + currentTime + "] ate [");

        if(tempJob.getFirstResponse()){
          tempJob.setTimeResponse(tempJob.getTimeOnQueue());
          tempJob.setFirstResponse(false);
        }

        quantum = tempJob.getTimeNeeded();
        tempJob.decTimeNeeded(quantum);
        tempJob.incTimeOnCpu(quantum);
        updateQueueTime(runningQueue,quantum, currentTime);

      }
      currentTime += quantum;

      while(this._queueOfJobs.peek() != null && this._queueOfJobs.peek().getTimeArrived() <= currentTime){
        Job jobChangeQueue = this._queueOfJobs.poll();
        runningQueue.add(jobChangeQueue);
      }

      if(tempJob !=null)
        this._finishedQueue.add(tempJob);

        //System.out.println(currentTime+"]");
    }

    //logger.printQueue(this._finishedQueue);
  }


      /********************************
      ****STATS AND HANDY FUNCTIONS****
      *********************************/


  /**Updates all other jobs on queue while one is processing**/
  public void updateQueueTime(Queue<Job> queue, int quantum, int currentTime){
    for (Job job : queue) {
      job.incTimeOnQueue(quantum);
    }

    for (Job job : this._queueOfJobs) {
      int offset = job.getTimeArrived() - currentTime;
      if(quantum > offset)
        job.incTimeOnQueue(quantum - offset);
    }



  }

  /**Computes the average time on queue**/
  public float calcWaitingTime(){
    float totalTime = 0;

    for (Job job: this._finishedQueue) {
      totalTime += job.getTimeOnQueue();
    }

    return totalTime / this._finishedQueue.size();
  }

  /**Computes the average time on CPU**/
  public float calcProcessTime(){
    float totalTime = 0;

    for (Job job: this._finishedQueue) {
      totalTime += job.getTimeOnCpu();
    }

    return totalTime / this._finishedQueue.size();
  }

  /**Computes the average time for the first response**/
  public float calcFirstResponseTime(){
    float totalTime = 0;

    for (Job job: this._finishedQueue) {
      totalTime += job.getTimeResponse();
    }

    return totalTime / this._finishedQueue.size();
  }

  /**Prints the results**/
  public void printResults(){
    float averageOnQueue = calcWaitingTime();
    float averageOnCpu = calcProcessTime();
    float averageFirstResponse = calcFirstResponseTime();
    System.out.println(this.getAlgorithm()+" "+String.format( "%.1f", (averageOnCpu+averageOnQueue))+" "+
                      String.format( "%.1f", (averageFirstResponse)) +" "+String.format( "%.1f", (averageOnQueue)));
  }
}
