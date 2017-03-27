import java.util.*;
import java.util.concurrent.*;

public class Main{
    public static void main(String[] args){
    	(new Main()).go();
        
    }
    public void go(){
    	Fork f1 = new Fork();
        Fork f2 = new Fork();
        Philosopher p1 = new Philosopher(f1,f2,"Bob");
        Philosopher p2 = new Philosopher(f2,f1,"Greg");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        while (true){
		executor.submit(p1);
		executor.submit(p2);}
    }
    
    
}