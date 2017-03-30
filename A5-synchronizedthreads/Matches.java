import java.util.concurrent.*;

public class Matches extends Supplier{  
  public synchronized void grab() throws InterruptedException{
    if(this.grabMatches(this)){
      this.grabMatches(this);
    }
  }
}
