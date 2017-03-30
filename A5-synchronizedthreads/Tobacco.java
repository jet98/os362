import java.util.concurrent.*;

public class Tobacco extends Supplier{
  public synchronized void grab() throws InterruptedException{
    if(this.grabTobacco(this)){
      this.grabTobacco(this);
    }
  }
}