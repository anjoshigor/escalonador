/**
* @anjoshigor
* Job class
*
* attributes:
* _timeOnCpu -> Time spent on CPU processing data (turnaround)
* _timeOnQueue -> Time spent on Queue waiting to be scehdule
* _timeResponse -> Time to answer for the first time
* _pid -> Job identifier
* _firstResponse -> Flag to identify wether it's Job's first time processing data
*
* methods:
* Simple to understand
*
**/

public class Job {

  private int _timeOnCpu, _timeOnQueue, _timeResponse;
  private int _timeNeeded, _timeArrived;
  private int _pid;
  private boolean _firstResponse;

  public Job(int timeArrived, int timeNeeded, int pid){
    this._timeNeeded = timeNeeded;
    this._timeArrived = timeArrived;
    this._firstResponse = true;
    this._pid = pid;
    this._timeOnCpu = this._timeOnQueue = 0;
  }

  public int getTimeOnCpu(){
    return this._timeOnCpu;
  }

  public int getTimeOnQueue(){
    return this._timeOnQueue;
  }

  public int getTimeResponse(){
    return this._timeResponse;
  }

  public int getTimeNeeded(){
    return this._timeNeeded;
  }

  public int getTimeArrived(){
    return this._timeArrived;
  }

  public int getPid(){
    return this._pid;
  }

  public boolean getFirstResponse(){
    return this._firstResponse;
  }

  public void decTimeNeeded(int quantum){
    if(this._timeNeeded > quantum)
      this._timeNeeded -= quantum;
    else
      this._timeNeeded = 0;
  }

  public void setTimeResponse(int timeElapsedOnCpu){
    this._timeResponse = timeElapsedOnCpu;
  }

  public void setFirstResponse(boolean changeStatus){
    this._firstResponse = changeStatus;
  }

  public void incTimeOnCpu(int quantum){
      this._timeOnCpu += quantum;
  }

  public void incTimeOnQueue(int quantum){
      this._timeOnQueue += quantum;
  }
}
