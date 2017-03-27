import java.util.*;
import java.util.concurrent.*;

public class Supplier{
    private final Semaphore item = new Semaphore(0, true);
    
    public synchronized boolean grabItem(Tobacco t, Paper p, Matches m){
    
    }
}