import java.util.*;
import java.util.concurrent.*;

public class Supplier{
  private final Semaphore item = new Semaphore(2, true);
  
  public synchronized boolean grabTobacco(Tobacco t){
    if(this.getAvailable()){
      try{
        long s = ThreadLocalRandom.current().nextInt(500, 1000 + 1);
        Thread.sleep(s);
        item.acquire();
      } 
      catch (InterruptedException ex){
        System.err.println(ex);
      }
      return true;
    }
    else
      return false;   
  }
  
  public synchronized boolean grabMatches(Matches m){
    if(this.getAvailable()){
      try{
        long s = ThreadLocalRandom.current().nextInt(500, 1000 + 1);
        Thread.sleep(s);
        item.acquire();
      } 
      catch (InterruptedException ex){
        System.err.println(ex);
      }
      return true;
    }
    else
      return false;   
  }
  
  public synchronized boolean grabPaper(Paper p){
    if(this.getAvailable()){
      try{
        long s = ThreadLocalRandom.current().nextInt(500, 1000 + 1);
        Thread.sleep(s);
        item.acquire();
      } 
      catch (InterruptedException ex){
        System.err.println(ex);
      }
      return true;
    }
    else
      return false;     
  }
  
  public synchronized void releaseTobacco(Tobacco t) throws InterruptedException{
      item.release();
  }
  
  public synchronized void releaseMatches(Matches m) throws InterruptedException{
      item.release();
  }
  
  public synchronized void releasePaper(Paper p) throws InterruptedException{
      item.release();
  }
  
  public synchronized boolean getAvailable(){
    if(item.availablePermits() > 0 && item.availablePermits() < 3){
      // System.out.println(item.availablePermits());
      return true;
    }
    else{
      // System.out.println(item.availablePermits());
      return false;
    }
  }
}
