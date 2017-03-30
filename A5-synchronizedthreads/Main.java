import java.util.*;
import java.util.concurrent.*;

public class Main{
  public static void main(String args[]){
    new Main().go();
  }

  public void go(){
    Supplier s = new Supplier();
    List<Callable<Boolean>> threads = new ArrayList<Callable<Boolean>>();
    threads.add(new Smoker(s, "Fire Stick", null, new Paper(), new Tobacco()));
    threads.add(new Smoker(s, "Egyptian Papyrus", new Matches(), null, new Tobacco()));
    threads.add(new Smoker(s, "Dried Plant", new Matches(), new Paper(), null));
    
    ExecutorService executor = Executors.newFixedThreadPool(threads.size());
    try{
      List<Future<Boolean>> future = executor.invokeAll(threads);
    } 
    catch(InterruptedException ex){
      System.err.println("Something is wrong, don't ask me :{\n" + ex);
    }
    executor.shutdown();
  }
}
