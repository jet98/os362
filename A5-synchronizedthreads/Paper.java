import java.util.concurrent.*;

public class Paper extends Supplier{  
  public synchronized void grab() throws InterruptedException{
    if(this.grabPaper(this)){
      this.grabPaper(this);
    }
  }
}
